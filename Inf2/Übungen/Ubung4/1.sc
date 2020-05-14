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

val u:FieldContent= Chessman(Rook,Black)

def printField(row : Int, col : Int, fc : FieldContent) : Unit = {
    println("(" + row + ", " + col + "): " + fc)
}

abstract class Position
case class Coord(row : Int, col : Int) extends Position
case class Undef() extends Position

type Chessboard = Array[Array[FieldContent]]

def emptyChessboard : Chessboard = Array.fill(8, 8)(Empty())

/*
a) Implementieren Sie die Bestimmung der Positionen aller weißen T¨urme (Typ: Rook) als Methode. Zur weiteren
Verwendung sind die Positionen in geeigneter Weise in einer Variablen zu speichern.
 */

def turmfinden(cb:Chessboard):Array[Position]={

    var out:Array[Position] = Array.fill(10)(Undef())

    var index = 0;

    for(i<-0 until 8; j<-0 until 8){
        cb(i)(j) match {
            case Chessman(Rook,White)=> {
                out(index)=new Coord(i,j)
                index+=1
            }
            case _ =>
        }
    }
    return out
}

/*
b) Implementieren Sie die Methode nextField, die zu einer gegebenen Position und einer angegebenen Richtung
die n¨achstliegende Position bestimmt. Ist die Position dabei außerhalb des zul¨assigen Bereichs, ist Undef
() zur¨uckzugeben. Nutzen Sie folgende Definitionen:
1 type Direction = (Int, Int)
2 val LEFT = (0, -1)
3 val RIGHT = (0, 1)
4 val UP = (-1, 0)
5 val DOWN = (1, 0)
67
def nextField(p : Position, dir : Direction) : Position = ...
 */
type Direction = (Int, Int)
val LEFT = (0, -1)
val RIGHT = (0, 1)
val UP = (-1, 0)
val DOWN = (1, 0)
def nextField(p : Position, dir : Direction) : Position ={
    p match {
        case Coord(row,col)=>{
            val tmp = (dir._1+row,dir._2+col)
            if(tmp._1<0|| tmp._1>7 || tmp._2 <0|| tmp._2>7) return Undef()
            else new Coord(tmp._1,tmp._2)
        }
        case Undef()=> return Undef()
    }


}

/*
c) Implementieren Sie eine Hilfsprozedur, die zu einem ¨ubergebenem Schachbrett, einer angegebenen Position
und einer gegebenen Richtung alle bedrohten Positionen und den jeweiligen Inhalt ausgibt.
 */
//(row : Int, col : Int, fc : FieldContent)
//cb(row)(col) => FiledContent
def hilfe(cb:Chessboard,p:Position,dir:Direction):Unit={
    var pos = p
    //var vorlpos = nextField(p,dir)
    var tmp = nextField(pos,dir)
    while(tmp!=Undef()){
        // var tmp = nextField(pos,dir)
        tmp match {
            case Coord(row,col)=> printField(row,col,cb(row)(col))
            case Undef()=> println("schief gelaufen")
                pos = tmp
        }
        tmp = nextField(tmp,dir)
    }
}
/*
d) Implementieren Sie die Prozedur printThreatenedFields, die alle von weißen T¨urmen bedrohten Felder
ausgibt. Nutzen Sie die Teilergebnisse aus den vorherigen Teilaufgaben.
def printThreatenedFields(cb : Chessboard) : Unit = ...
 */

def printThreatenedFields(cb : Chessboard) : Unit ={

    for(pos<- turmfinden(cb);if pos != Undef()){
        for(i<- List(RIGHT,LEFT,UP,DOWN)) {
            hilfe(cb, pos,i)
        }
    }
}

var cb:Chessboard = Array.fill(8, 8)(Empty())
cb(0)(0)=new Chessman(Rook,White)
cb(0)(7) =new Chessman(Rook,Black)

printThreatenedFields(cb)
/*
e) U¨ berlegen Sie sich, wie sich der durch den Pseudocode beschriebene Ansatz erweitern la¨sst, sodass er fu¨r
alle ”Linienfiguren“ (Turm, La¨ufer, Dame) anwendbar ist. Welche A¨ nderungen sind in der Implementierung
notwendig?
2
 */