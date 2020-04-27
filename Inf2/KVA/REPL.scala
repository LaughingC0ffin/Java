package ScalaGo
import ScalaGo._
import ScalaGo.Color._
import scala.io.StdIn
object REPL {
  /**
    * This function runs and evaluates game states and transitions
    * until the global variable gs reaches the passed end state
    *
    * @param endState run until this state is reached
    */
  def repl(endState: GameState): Unit = {
      var breaker =true
      var lenn=0
      while(breaker){
        print("Bitte größe eingeben: ")
        val inputgr = scala.io.StdIn.readLine()
        try{
          if(gs.transitions(0)._1(inputgr)) {
            gs = gs.transitions(0)._2
            lenn=inputgr.length
            breaker=false
          }
        }catch {
          case e :Throwable=> println("zu groß oder zu klein #repl")
        }
  }
      outputBoardAndPlayer()
    while (gs != endState) {
      print("Zug eingeben:  ")
      var input = scala.io.StdIn.readLine()
      try{
        if(gs.transitions(0)._1(input)) {
          var tmp=true
          while(tmp){
            try{
              setPosition(board,stringtoPos(input),Stone(gs.player))
              tmp=false
            }catch {
              case e:Throwable => {
                print("probiere es nochmal: ")
                input = scala.io.StdIn.readLine()
              }
            }
          }
          ScalaGo.killChains(board)
          gs=gs.transitions(0)._2
          outputBoardAndPlayer()
          removeOldRemovedStones(board)
        }
      } catch{
        case _: Throwable => print("nope #place")
      }
        try {
          if (gs.transitions(1)._1(input)) {
            gs = gs.transitions(1)._2
            println("passed")
            outputBoardAndPlayer()
          }
        } catch {
          case _: Throwable => return print("nope #pass")
        }

    }
      /*
            for(i<- 0 until gs.transitions.length){
        try{
          if(gs.transitions(i)._1(input)) {
            gs=gs.transitions(i)._2
            println(gs.player)
          }
        } catch{
          case _: Throwable => print("nope")
        }
      }
       */


    printFinalScore(1,1,1,1)
    println("gewonnen hat: _____")
  }

  def stringtoPos(string:String):Position={
    var a = string
    var b = a.indexOf(",")
    var firsthalf=""
    var secondhalf=""
    for(i<-0 until  a.indexOf(",")) {
      try {
        Integer.parseInt(a(i).toString)
        firsthalf=firsthalf+a(i)
      }
      catch {
        case ex: Exception =>println("iwas lief schief bei string to pos")
      }
    }
    for(i<- a.indexOf(",")+1 until a.length) {
      try {
        Integer.parseInt(a(i).toString)
        secondhalf=secondhalf+a(i)
      }
      catch {
        case ex: Exception => println("iwas lief schief bei string to pos")


      }
    }
    return (firsthalf.toInt,secondhalf.toInt)
  }
  /**
    * Return a String depicting the content of the field
    *
    * @param c the FieldContent to depict
    * @return String depicting the FieldContent
    */
  def getFieldContentString(c: FieldContent): String = {
    c match {
      case Stone(Black)=>return "●"
      case Stone(White)=>return "○"
      case RemovedStone(White) =>return "◌"
      case RemovedStone(Black)=>return "◍"
      case Empty()=>return " "
      case OutOfBounds()=> ""
    }
  }

  /**
    * Return a String displaying the current state of the board. If the previous board is supplied it is used to
    * determine differences between the boards to highlight them.
    *
    * @param b board to return as string
    * @param prev previous board to highlight differences
    * @return String representation of the current board
    */
  def getFieldString(b: Goboard, prev: Goboard): String = {
    var boardBuilder = StringBuilder.newBuilder
    var n = b.length-2
    def first(n:Int):Unit= {
      boardBuilder.append("┌")
      for (i <- 0 until n - 1) {
        boardBuilder.append("───┬")
      }
      boardBuilder.append("───┐")
    }
    def last(n:Int):Unit= {
      boardBuilder.append("\n")
      boardBuilder.append("└")
      for (i <- 0 until n - 1) {
        boardBuilder.append("───┴")
      }
      boardBuilder.append("───┘")
      boardBuilder.append("\n")
    }
    def middle(n:Int):Unit={
      boardBuilder.append("\n")
      boardBuilder.append("├")
      for (i <- 0 until n - 1) {
        boardBuilder.append("───┼")
      }
      boardBuilder.append("───┤")
    }

    first(n)
    for(i<-0 until n){
      boardBuilder.append("\n")
      boardBuilder.append("│ ")
      for(j<-0 until n){
        boardBuilder.append(getFieldContentString(b(i+1)(j+1))+ " "+ "│ ")
      }
      boardBuilder.append(i)
      if(i!=n-1) middle(n)
    }
    last(n)
    boardBuilder.append("  "+"0")
    for(a<-1 until n){
      boardBuilder.append("   "+a)
    }
    return boardBuilder.toString()
  }

  /**
    * Print the board to the standard output
    *
    * @param b current board
    * @param pB previous board, may be used to detect and higlight differences
    */
  def printBoard(b: Goboard, pB: Goboard): Unit = {
    println(getFieldString(b, pB))
  }

  /**
    * Print which player's turn it is to standard output
    */
  def printCurrentPlayer(): Unit = {
    println(gs.player)
  }

  /**
    * Print the players' score to standard output
    *
    * @param stonesWhite number of white stones on the board
    * @param fieldsWhite number of fields enclosed by white stones
    * @param stonesBlack number of black stones on the board
    * @param fieldsBlack number of fields enclosed by black stones
    */
  def printFinalScore(stonesWhite: Int, fieldsWhite: Int, stonesBlack: Int, fieldsBlack: Int): Unit = {
    println("stoneswhite: "+stonesWhite )
    println("stonesblack: "+stonesBlack)
    println("fieldswhite: "+fieldsWhite)
    println("fieldsblack: "+fieldsBlack)
  }
/*
  def main(args: Array[String]):Unit={
    //var board : Goboard = Array(Array(Empty(), Stone(Black), Stone(White)), Array(Stone(Black), RemovedStone(White), Stone(Black)), Array(RemovedStone(Black), Stone(Black), Empty()))
    var n = 4
    var board:Goboard=Array.tabulate(n+2,n+2)((r,c)=> if(r==0||c==0||r==n+1||c==n+1)OutOfBounds()
    else Empty())
    board(1)(1)=Stone(Black)
    board(2)(2)=Stone(White)

    REPL.printBoard(board,null)
  }
  */
}
