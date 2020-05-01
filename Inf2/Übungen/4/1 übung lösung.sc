
// Typ der Figur
object ChessmanType extends Enumeration {
  type ChessmanType = Value
  val King, Queen, Rook, Bishop, Knight, Pawn = Value
}
//rook = Turm
//welche farbe
object Color extends Enumeration {
  type Color = Value
  val White, Black = Value
}
import ChessmanType._
import Color._


abstract class FieldContent
case class Chessman(cmt : ChessmanType, color : Color) extends
  FieldContent
case class Empty() extends FieldContent

//val u:FieldContent= Chessman(Rook,Black)

def printField(row : Int, col : Int, fc : FieldContent) : Unit = {
  println("(" + row + ", " + col + "): " + fc)
}

abstract class Position
case class Coord(row : Int, col : Int) extends Position
case class Undef() extends Position

type Chessboard = Array[Array[FieldContent]]

def emptyChessboard : Chessboard = Array.fill(8, 8)(Empty())

//10 Felder da 8 Bauern und 2 Türme
def sucheTurm(Chess: Chessboard):Array[Position]={
  var turmPos: Array[Position] = Array.fill(10)(Undef())
  var counter = 0
  //for(a <- 0 until 8; b <- 0 until 8){
    //if(Chess(a)(b) == Chessman(Rook, White)) {
    //  turmPos(pos) = Coord(a, b)
      //pos += 1
for (x <- 0 to 7){
  for ( y <- 0 to 7 ){
    if(cb(x)(y) == Chessman( Rook, White)) {
      turmPos(counter) = Coord(x,y)
      counter +=1
      }
    }
  }
  return turmPos
}

type Direction = (Int, Int)
val LEFT = (0, -1)
val RIGHT = (0, 1)
val UP = (-1, 0)
val DOWN = (1, 0)

def nextField(p : Position, dir : Direction) : Position ={
   p match{
     case Undef()=> return Undef()
     case Coord(a,b)=> {
       val neu: (Int,Int) = (a + dir._1, b + dir._2)
       if(neu._1 < 0 || neu._1 > 8 || neu._2 < 0 || neu._2 > 8) return Undef()
       else Coord(neu._1, neu._2)
     }
   }
}



def dangered(chessb:Chessboard,p:Position,dir:Direction):Unit= {
  p match {
    case Coord(a, b) => {
      //if(a<8 &&b<8){
      //printField(a,b,chessb(a)(b))
      //dangered(chessb,nextField(Coord(a,b),dir),dir)
      if (a + dir._1 > 7 || a + dir._1 < 0 || b + dir._2 > 7 || b + dir._2 < 0) return Undef
      else return Coord(a + dir._1, b + dir._2)

      case Undef()
      => return Undef()
    }
  }
  dangered(emptyChessboard, Coord(1, 1), RIGHT)


  def bedroht(pos: Position, cb: Chessboard, dir: Direction): Unit = {
   // var a = sucheTurm(cb)
   // var b = Array(LEFT, RIGHT, UP, DOWN)
   // for (i <- a) {
    //  for (v <- b) {
      //  dangered(cb, i, v)

    var pe = nextField(pos, dir)
    while ( pe != Undef()) {
      val Coord(row, col) = pe
    val field : FieldContent =  cb( row, col)
    if ( field == Empty() ) {
      printField(row, col, field)
      pe = nextField(pe, dir)
    }
    else {
      val Chessman( _, clr) = field
      if ( clr == Black ) printField( row, col, field)
      pe = Undef()
      }
    }
  }
}


def printThreatendFields(cb : Chessboard) : Unit = {
  for ( a<- sucheTurm(cb)){
    for ( dir <- Array(LEFT, RIGHT, UP, DOWN)) {
      dangered(cb,a, dir)
    }
  }
}