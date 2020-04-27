package ScalaGo

import org.scalactic.source.Position

object ScalaGo {
  type Position = (Int, Int)
  object Color extends Enumeration {
    type Color = Value
    val Black, White, Nobody = Value
  }
  import Color._
  abstract class FieldContent
  case class Stone(color: Color) extends FieldContent
  //gleiche wie stone nur halt nicht da
  //dafür da, damit man nachvollziehen kann welcher raus genommen wurde
  case class RemovedStone(color: Color) extends FieldContent
//einfach leer
  case class Empty() extends FieldContent
//alles was zu weit raus geht
  case class OutOfBounds() extends FieldContent

  /**
    * The game state controls the progression of the game.
    *
    * The transitions array contains tuples of functions and target states.
    * If the function of a tuple evaluates to true the target state is activated.
    *
    * @param player who's turn it is
    * @param transitions array of a tuple of transition functions and next GameState
    * @param startAction function to execute when entering the state
    */
  case class GameState(player: Color, transitions: Array[(String => Boolean, GameState)], startAction: () => Unit)
  /**
    * Chain of stones
    *
    * @param color color of the stones
    * @param position positions of the stones
    * @param liberties positions of the chain's liberties   => freiheiten
    */
  case class Chain(color: Color, position: FakeList[Position], liberties: FakeList[Position])
  case class OutOfBoardException(msg: String) extends Exception
  case class InvalidBordsizeException(msg: String) extends Exception
  case class InvalidMoveException(msg: String = "This move is not possible!") extends Exception
  /**
    * Alias type for the game board
    */
    //type def für das ganze board
  type Goboard = Array[Array[FieldContent]]

  var board: Goboard = null // The current board
  var prevBoard: Goboard = null // The previous board (used for detecting repeating positions)
  var prevPrevBoard: Goboard = null // The board before the last board (used for detecting repeating positions)

  var gs: GameState = null // Starting GameState, this global variable is used throughout the implementation

  def main(args: Array[String]): Unit = {

    val (start, end) = buildStartToEndStates()

    gs = start

    REPL.repl(end)
    //calculateScore()

  }

  /**
    * Generate all necessary states with transitions for the game
    *
    * @return a tuple of the start and the end states
    */
  def buildStartToEndStates(): (GameState, GameState) = {
    var start= GameState(Nobody,new Array[(String => Boolean, GameState)](1), () => {})
    var blackturn = GameState(Black,new Array[(String => Boolean, GameState)](2), () => {})
    var whitepass = GameState(White,new Array[(String => Boolean, GameState)](2), () => {})
    var blackpass = GameState(Black,new Array[(String => Boolean, GameState)](2), () => {})
    var whiteturn = GameState(White,new Array[(String => Boolean, GameState)](2), () => {})
    var end=GameState(Nobody,Array(), () => {})

    start.transitions(0)=((x:String)=>buildBoardTransition(x),blackturn)
    blackturn.transitions(0)=((x:String)=>placeStoneTransition(x),whiteturn)
    blackturn.transitions(1)=((x:String)=>passTransition(x),whitepass)
    whiteturn.transitions(0)=((x:String)=>placeStoneTransition(x),blackturn)
    whiteturn.transitions(1)=((x:String)=>passTransition(x),blackpass)
    blackpass.transitions(0)=((x:String)=>placeStoneTransition(x),whiteturn)
    blackpass.transitions(1)=((x:String)=>passTransition(x),end)
    whitepass.transitions(0)=((x:String)=>placeStoneTransition(x),blackturn)
    whitepass.transitions(1)=((x:String)=>passTransition(x),end)
    return (start,end)
  }

  /**
    * Output the current board an player and afterwards remove old RemovedStone markers
    */
  def outputBoardAndPlayer(): Unit = {
    //erstmal nur output ohne highlights
    REPL.printBoard(board,prevBoard)
    REPL.printCurrentPlayer()
  }

  /**
    * Remove all RemovedStone markers from the board
    *
    * @param b board to remove from
    */
  def removeOldRemovedStones(b: Goboard): Unit = {

    for(i<-1 until b.length-1;j<-1 until b.length-1){
      b(i)(j) match {
        case RemovedStone(White)=> b(i)(j) = Empty()
        case RemovedStone(Black)=>b(i)(j)=Empty()
        case _ => ()
      }
    }
    //alle Removed stones zu empty machen
  }

  def removestones(chain:Chain,c:Color,b:Goboard):Unit={
    var verb = chain.position
    while(verb.next!=null){
      b(verb.entry._1)(verb.entry._2) =  RemovedStone(c)
      verb=verb.next
    }
    b(verb.entry._1)(verb.entry._2) = RemovedStone(c)
  }

  /**
    * Remove stones of each chain's positions where the chain has no liberties
    *
    * @param chains list of chains
    * @param c      only remove stones of this color
    * @param b      board to remove the stones from
    */
  def removeStonesOfZeroChains(chains: FakeList[Chain], c: Color, b: Goboard): Unit = {
    //println("remove zero chains")
    var verb = chains
    while(verb.next != null){
      if(verb.entry.color==c && verb.entry.liberties.entry==null){
        removestones(verb.entry,c,b)
      }
      verb=verb.next
    }
    if(verb.entry.color==c && verb.entry.liberties.isempty()){
      removestones(verb.entry,c,b)
    }
  }

  /**
    * Remove all stones of chains with zero liberties starting with the opposing players color and then remove remaining
    * chains with zero liberties of the own color
    *
    * @param b board to remove the stones from
    */
  def killChains(b: Goboard): Unit = {
    if(gs.player==White) {
      removeStonesOfZeroChains(findChains(b),Black ,b)
      removeStonesOfZeroChains(findChains(b),White,b)
    }
    else if(gs.player==Black){
      removeStonesOfZeroChains(findChains(b),White,b)
      removeStonesOfZeroChains(findChains(b), Black, b)
    }
    else println("#error kill chain")
  }

  /**
    * Tests if two boards are identical with regard to only Empty and Stones. Fields with RemovedStones are interpreted
    * as Empty
    *
    * @param b1 board one
    * @param b2 board two
    * @return true if both boards represent equal positions, false otherwise
    */
  def equalBoardPositions(b1: Goboard, b2: Goboard): Boolean = {
    var out = false
    for(i<-0 until b1.length;j<-0 until b1.length){
      //alternativ if(b1(i)(j) != b2(i)(j)) return false
     b1(i)(j) match{
       case Stone(Black) => b2(i)(j)match{
         case Stone(Black)=>out = true
         case _=> return false
       }
       case Stone(White) =>b2(i)(j)match{
         case Stone(White)=>out = true
         case _=> return false
       }
       case RemovedStone(_)=>b2(i)(j)match{
         case RemovedStone(_)=>out = true
         case Empty()=>out = true
         case _=> return false
       }
       case Empty()=>b2(i)(j)match{
         case RemovedStone(_)=>out =  true
         case Empty()=>out = true
         case _ =>return false
       }
       case OutOfBounds()=>b2(i)(j)match{
         case OutOfBounds()=>out = true
         case _ => return false
       }
     }
    }
    return out
  }

  /**
    * Create a real copy of the board
    *
    * @param b board to copy
    * @return an identical board at a different memory location
    */
  def copyBoard(b: Goboard): Goboard = {
    var neu :Goboard=null
    for(i<-0 until b.length;j<-0 until b.length){
      neu(i)(j)=b(i)(j)
    }
    return neu
  }

  /**
    * Set a position within a board. Thows an OutOfBoardException if the position is not within the board.
    *
    * @param b   the board to place the field in
    * @param pos the position
    * @param fc  the new content of the field at position
    */
  def setPosition(b: Goboard, pos: Position, fc: FieldContent): Unit = {
    if(pos._1>b.length-2 || pos._2>b.length-2||pos._1 <0||pos._2<0) throw new OutOfBoardException("ja außerhalb")
      b(pos._1+1)(pos._2+1)match{
        case Empty()=>b(pos._1 +1)(pos._2 +1)=fc
        case RemovedStone(White)=>b(pos._1 +1)(pos._2 +1)=fc
        case RemovedStone(Black)=>b(pos._1 +1)(pos._2 +1)=fc
        case _ => throw new InvalidMoveException("kann da nicht hin setzen ^^")
      }
  }

  /**
    * Test if a field is only surrounded by stones of one color. This search may span multiple Empty fields.
    *
    * @param pos start position
    * @param c   color to match
    * @param b   board to search
    * @return true if the field is only surrounded by stones of the given color, false otherwise
    */
  def isSurroundedByOnly(pos: Position, c: Color, b: Goboard): Boolean = {
    //man kännte dann in diese richtung weiter laufen für calc score
    var dir= (1,0)
    def drehe (pos:Position):Position= (pos._2,-1*pos._1)
    for(i<- 0 until 4){
     if(b(pos._1+dir._1)(pos._2+dir._2)!=Stone(c))return false
      dir = drehe(dir)
    }
    true
  }
  def isSurroundedByOnly2(pos: Position, c: Color, b: Goboard): Boolean = {
    //man kännte dann in diese richtung weiter laufen für calc score
    var dir= (1,0)
    def drehe (pos:Position):Position= (pos._2,-1*pos._1)

    for(i<- 0 until 4){
      var tmp = (pos._1+dir._1,pos._2+dir._2)
      var out = false
      while(tmp._1<b.length||tmp._2<b.length||tmp._1>0||tmp._2>0){
        if(b(pos._1+dir._1)(pos._2+dir._2)==Stone(c)) out=true
        tmp= (tmp._1+dir._1,tmp._2+dir._2)
      }
      if(out== false)return false
      dir = drehe(dir)
    }
    true
  }
  /**
    * Calculate the score according to the area covered by the players
    *
    */
  def calculateScore(): Unit = {
    //mit sourround auf diee andere farbe testen
    //jedes feld was nur von gegner sourr ist zählt dann da rein
    //sonderfall für die ecken wo ein indize 1 ist
    //da würden dann schon zwei reichen
    var whitesour=0
    var blacksurr=0
    for(i<- 1 until board.length-1;j<- 0 until board.length-1){
      if(isSurroundedByOnly2((i,j),White,board)) whitesour+=1
    }
    for(i<- 1 until board.length-1;j<- 0 until board.length-1){
      if(isSurroundedByOnly2((i,j),Black,board)) blacksurr+=1
    }
    println(whitesour + "  "+blacksurr)
  }
  /**
    * Generate a board of size*size dimensions.
    *
    * @param size the horizontal and vertical dimension of the board
    * @return the board initialized with Empty() fields
    */
  def buildBoardTransition(size: String): Boolean = {
    var n = size.toInt
    if(n<5 || n>19) {
      println("zu klein oder zu groß (5 bis 19) #buildboardtrans")
      return false
    }
    board=Array.tabulate(n+2,n+2)((r,c)=> if(r==0||c==0||r==n+1||c==n+1)OutOfBounds()
    else Empty())
      true
  }

  /**
    * Parse a player's input to detect a stone placement command and place a stone
    *
    * @param command input from the player
    * @return true if the command could be understood, false otherwise
    */
  def placeStoneTransition(command: String): Boolean = {
    var a = command
    var b = a.indexOf(",")
    for(i<-0 until  a.indexOf(",")) {
      try {
        Integer.parseInt(a(i).toString)
      }
      catch {
        case ex: Exception =>
          return false
      }
    }
    for(i<- a.indexOf(",")+1 until a.length) {
      try {
        Integer.parseInt(a(i).toString)
      }
      catch {
        case ex: Exception =>
          return false
      }
    }
    true
  }

  /**
    * Parse a player's input to detect the 'pass' command
    *
    * @param command input from the player
    * @return true if the command could be understood, false otherwise
    */
  def passTransition(command: String): Boolean = {
    if (command.equals("pass")) return true
    else return false
  }

  /**
    * Get the contents of a field by row,column indices.
    *
    * This is a convenience function that directly calls getFieldConten(p: Position, b: Goboard)
    *
    * @param row    row index
    * @param column column index
    * @param b      the board
    * @return the FieldContent which may be OutOfBounds
    */
  def getFieldContent(row: Int, column: Int, b: Goboard): FieldContent = getFieldContent((row, column), b)

  /**
    * Get the contents of a field by Position.
    *
    * @param p the position
    * @param b the board
    * @return the FieldContent which may be OutOfBounds
    */
  def getFieldContent(p: Position, b: Goboard): FieldContent = {
    return b(p._1 )(p._2)
  }

  /**
    * Used with the exploreChain function to add stones' position to the chain
    *
    * @param chain chain to extend
    * @param pos position to check
    * @param b board to use
    */
    //adjacent  == benachbart
    //position wird als realwert übergeben

  def addAdjacentStones(chain: Chain, pos: Position, b: Goboard): Unit = {
      b(pos._1)(pos._2) match{
        case Stone(chain.color)=> chain.position.append(pos)
        case _ => ()
      }
    }

  /**
    * Explore all positions of a chain and apply passed function to all fields surrounding the chain's positions
    *
    * @param chain chain to explore
    * @param apply apply this function to all fields surrounding the chain's positions
    * @param b the board to use while exploring the chain
    */
  def exploreChain(chain: Chain, apply: (Chain, Position, Goboard) => Unit, b: Goboard): Unit = {
    var verb = chain.position
    var dir = (1, 0)
    def drehe(dir: Position): Position = (dir._2, -1 * dir._1)
    while (verb != null) {
      for (i <- 0 until 4) {
        var tmp= (verb.entry._1+dir._1,verb.entry._2+dir._2)
        if(!chain.position.contains(tmp)) apply(chain,tmp,b)
        dir = drehe(dir)
      }
      verb = verb.next
    }
  }
  /**
    * Find all chains on the board
    *
    * @param b board to search for chains
    * @return list of chains
    */
  def findChains(b: Goboard): FakeList[Chain] = {
    var out=new FakeList[Chain](null,null)
    var verb=out
      for (i <- 1 until b.length - 1; j <- 1 until b.length - 1) {
        b(i)(j) match {
          case Stone(c)=>{
            if(!isPositionInChains((i,j),out)){
             verb.next=new FakeList[Chain](new Chain(c,new FakeList[(Int, Int)](),new FakeList[(Int, Int)]()) )
              verb.next.entry.position.append((i,j))
              var len = verb.next.entry.position.length()
              do{
                len = verb.next.entry.position.length()
                exploreChain(verb.next.entry,addAdjacentStones,b)
              }while(len < verb.next.entry.position.length())
              verb=verb.next
            }
          }
          case _=> ()
        }
      }
        updateLiberties(out.next,b)
      return out.next
  }

  /**
    * Used with the exploreChain function to add the positions of liberties to the chain
    *
    * @param chain chain to add the liberties to
    * @param pos position to search for a libertie
    * @param b board to use
    */
  def addLiberties(chain: Chain, pos: Position, b: Goboard): Unit = {
    b(pos._1)(pos._2)match{
      case Empty()=>{
        chain.liberties.append(pos)
      }
      case RemovedStone(_)=>chain.liberties.append(pos)
      case _ => ()
    }
  }
  /**
    * Find all positions that are liberties along a chain
    *
    * @param chains chain to search for libertie positions
    * @param b board to use
    */
  def updateLiberties(chains: FakeList[Chain], b: Goboard): Unit = {
    var verb = chains
      while (verb.next != null) {
        exploreChain(verb.entry, addLiberties, b: Goboard)
        verb = verb.next
      }
    exploreChain(verb.entry, addLiberties, b: Goboard)
  }
  /**
    * Check if the position is already a position of the chain
    *
    * @param pos position to check
    * @param chains chain to search
    * @return true if the position is already in the chain's position list
    */
  def isPositionInChains(pos: Position, chains: FakeList[Chain]): Boolean = {
    var verb = chains
    if(verb.next==null) return false
    while(verb.next!=null){
      if(verb.next.entry.position!=null){
        if(verb.next.entry.position.contains(pos)) return true
      }
      verb=verb.next
    }
    false
  }
}


