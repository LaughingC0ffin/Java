object Farbe extends Enumeration {
    type Farbe = Value
    val Schwarz, Rot, Gold = Value
}
import Farbe._
type Flagge = Array[Farbe]

def sortiereFlagge(flag : Flagge) : Unit = {
    val n = flag.length

    // Initialisierung von Indizes f¨ur die (sortierten) Bereiche
    // _s_chwarz, _r_ot und _g_old
    var s = -1 // Ende des Bereichs schwarz
    var r = s // Ende des Bereichs rot
    var g = n // Anfang des Bereichs gold

    // "unsortierter" Bereich ist zwischen dem Ende von rot und Anfang
    // von gold -> solange Vertauschen bis der Bereich leer ist
    while (r + 1 != g){
        //Verkleinere Indexbereich von r + 1 bis g - 1 durch
        //Vertauschen und Aktualisieren der Indizes
        if(flag(r+1) == Rot){
            r +=1
        }
        else if (flag(r+1)==Schwarz ){
            flag(r+1)=flag(s+1)
            flag(s+1)=Schwarz
            s+=1
            //vorbehalt
            r+=1

        }
        else if(flag(r+1) == Gold){
            flag(r+1)=flag(g-1)
            flag(g-1)=Gold
            g-=1
        }
        else println("schief gelaufen")

        }
        println(flag.mkString(" "))
    }

var a = Array(Rot,Schwarz,Gold,Gold,Schwarz,Rot,Rot,Schwarz,Gold,Rot,Gold)
sortiereFlagge(a)
