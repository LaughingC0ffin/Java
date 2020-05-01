/*
In dieser Aufgabe soll ein Scala-Programm entwickelt werden, welches die Koordinaten aller von weißen T¨urmen
bedrohten Felder auf einem Schachbrett ausgibt und zu jedem dieser Felder angibt, ob es leer ist oder welche
gegnerische Figur es enth¨alt.
Folgender Pseudocode skizziert einen m¨oglichen L¨osungsansatz:

 */

//Folgender Pseudocode skizziert einen m¨oglichen L¨osungsansatz:
  //Bestimme die Positionen aller weissen Tuerme auf einem Schachbrett


//F¨ur die Implementierung sind folgende Definitionen vorgegeben:

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
a) Implementieren Sie die Bestimmung der Positionen aller weißen T¨urme (Typ: Rook). Zur weiteren Verwendung
sind die Positionen in geeigneter Weise in einer Variablen zu speichern.
 */
def turmposbestim(chessb:Chessboard):Array[Position]={
  var turmpos=new Array[Position](2)
  var count=0
  for(x<-0 until 9;y<-0 until 9){
    if(chessb(x)(y)==Chessman(Rook,White)) {
      turmpos(count)=Coord(x,y)
    count+=1
    }
    }
  turmpos
  }

/*
b) Implementieren Sie die Methode nextField, die zu einer gegebenen Position und einer angegebenen Richtung
die n¨achstliegende Position bestimmt. Ist die Position dabei außerhalb des zul¨assigen Bereichs, ist Undef()
zur¨uckzugeben. Nutzen Sie folgende Definitionen:
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
  //pattern matching?
  //das problem ist das ich nicht weiß wie ich die zahlen in Coord ansprechen kann
// also wenn ich
  p match{
    case Coord(a,b)=> {
      if(a+dir._1<9 && b+dir._2<9) return Coord(a+dir._1,b+dir._2)
      else return Undef()
    }
    case Undef()=> return Undef()
  }
}


/*
c) Implementieren Sie eine Hilfsprozedur, die zu einem ¨ubergebenem Schachbrett, einer angegebenen Position
und einer gegebenen Richtung alle bedrohten Positionen und den jeweiligen Inhalt ausgibt.
 */
//soll alle bedrohnten felder ausgeben
def dangered(chessb:Chessboard,p:Position,dir:Direction):Unit= {
  p match{
    case Coord(a,b)=>{
      if(a<8 &&b<8){
        //hier nochmal auf ein figur prüfen weil dann ruhe
        printField(a,b,chessb(a)(b))
        dangered(chessb,nextField(Coord(a,b),dir),dir)
      }
      else{
        Undef()
      }
    }
    case Undef()=> Undef()
  }
}
dangered(emptyChessboard,Coord(1,1),RIGHT)
/*
d) Implementieren Sie die Prozedur printThreatenedFields, die alle von weißen T¨urmen bedrohten Felder
ausgibt. Nutzen Sie die Teilergebnisse aus den vorherigen Teilaufgaben.
1 def printThreatenedFields(cb : Chessboard) : Unit = ...
*/





/*
e) U¨ berlegen Sie sich, wie sich der durch den Pseudocode beschriebene Ansatz erweitern la¨sst, sodass er fu¨r
alle ”Linienfiguren“ (Turm, La¨ufer, Dame) anwendbar ist. Welche A¨ nderungen sind in der Implementierung
notwendig?


Riichtungen müssen quasi bestandteil der figuren sein
jede figur bekommt seine möglichen richtungen in die sie schaut
 */


