object Farbe extends Enumeration {
  type Farbe = Value
  val Schwarz, Rot, Gold = Value
}
import Farbe._

type Flagge = Array[Farbe]

def sortiereFlagge(flag : Flagge) : Unit = {
  val n = flag.length

  var s = -1;
  var r = s;
  var g = n

  while ( r + 1 != g) {
  while ( r + 1 < n && flag(r+1) == Rot) r += 1
  while (g-1 > 0 && flag(g-1) == Gold) g -= 1

    if( r+1 != g) {
      if ( flag(r+ 1) == Schwarz){
        flag(r+1) = Rot
        flag(s+1) = Schwarz
        s+=1
        r+=1
      }
      else if (flag(r+1) == Gold){
        flag(r+1) = flag(g-1)
        flag(g-1) = Gold
        g-=1
      }
    }
  }
}