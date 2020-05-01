object Farbe extends Enumeration {
   type Farbe = Value
   val Schwarz, Rot, Gold = Value
   }
 import Farbe._

type Flagge = Array[Farbe]

def sortiereFlagge(flag : Flagge) : Unit = {
   val n = flag.length
  var tmp=Schwarz
   var s = -1; var r = s; var g = n
  while (r + 1 != g){
     //Verkleinere Indexbereich von r + 1 bis g - 1 durch
     //Vertauschen und Aktualisieren der Indizes
     if(flag(r+1)==Schwarz){
       tmp=flag(s+1)
       flag(s+1)=flag(r+1)
       flag(r+1)=tmp
       s+=1
     }
     else if(flag(r+1) == Rot){
      r+=1

    }
     else if (flag(r+1)== Gold){
      tmp=flag(g-1)
       flag(g-1) = flag(r+1)
       flag(r+1) = tmp
       g-=1

    }
     }
  println(flag.mkString(" "))
  }
var a = Array(Rot,Schwarz,Gold,Gold,Schwarz,Rot,Rot,Schwarz,Gold,Rot,Gold)
sortiereFlagge(a)