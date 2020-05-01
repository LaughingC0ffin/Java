object Farbe extends Enumeration {
   type Farbe = Value
   val Schwarz, Rot, Gold = Value
  }
 import Farbe._
type Flagge = Array[Farbe]
//”s-r-u-g“
//schwarz und rot füge ich vorne ein
//gold fange ich an hinten an einzusortieren

def sortiereFlagge(flag : Flagge) : Unit = {
  val n = flag.length
  var s = -1
  var r = s
  var g = n
var tmp=Schwarz
  while (r + 1 != g) {
    if(flag(r+1)==Schwarz){
      tmp=flag(r+1)
      flag(r+1)=flag(s+1)
      flag(s+1)=tmp
      s+=1
    }
    else if(flag(r+1)==Rot){
      r+=1
    }
    else if(flag(r+1)==Gold){
      tmp=flag(r+1)
        flag(r+1)=flag(g-1)
      flag(g-1)=tmp
      g-=1
    }
    println(flag.mkString(" "))
  println(s+1,r+1,g-1)
  }
}
var a = Array(Rot,Schwarz,Gold,Gold,Schwarz,Rot,Rot,Schwarz,Gold,Rot,Gold)
sortiereFlagge(a)
println(a.mkString(" "))
/*
”s-r-u-g“vor.
*/