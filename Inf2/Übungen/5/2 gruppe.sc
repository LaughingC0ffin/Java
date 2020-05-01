
/*
 depth(N) ist die L¨ange des direkten Kantenzugs von R nach N (angegeben in Anzahl der Kanten).
 height(N) ist die H¨ohe des Teilbaums B mit derWurzel N. Die H¨ohe eines Bin¨arbaums ist die L¨ange des
l¨angsten direkten Kantenzugs von seiner Wurzel zu einem seiner Bl¨atter (ebenfalls in Anzahl der Kanten
gerechnet).

a) In der folgenden Abbildung finden Sie einen Bin¨arbaum, dessen Knoten dieser Definition entsprechen (die
einzelnen Felder sind durch ihre Anfangsbuchstaben identifiziert).
Tragen sie dort in jedem Knoten seine H¨ohe h und Tiefe d ein.
Verbinden Sie zus¨atzlich die Knoten N, die der Isoklinenbedingung
1 height(N) = depth(N)
gen¨ugen, ¨uber die Felder iso von rechts nach links zu einer linearen Liste. Der Zeiger iso soll auf den ersten
Knoten dieser Liste zeigen.


b) Erg¨anzen Sie die fehlenden Teile der unten skizzierten Scala-Prozedur updateTree so, dass der Aufruf
updateTree(R,0) in allen Knoten die H¨ohen und Tiefen eintr¨agt, sowie die Knoten die der Isoklinenbedingung
gen¨ugen zu einer Liste verbindet (wie in Teilaufgabe (a)). Der Zeiger iso : Tree befindet sich im
selben Block wie die Prozedur.

 */
//Isoklinenliste

//höhe ist maximale anzahl an kindern
//tiefe ist enternung von oberster wurzel zu aktuellem baum
class Tree(var height : Int = 0, var depth : Int = 0, var left : Tree = null, var right : Tree = null, var iso : Tree = null)

var iso : Tree = null

def updateTree (node : Tree, depth : Int) : Unit = {
  //brauche ich hier noch zeiger?
  //node auf null pruefen
  if(node.right==null && node.left==null) return

  //Tiefe von node eintragen
  node.depth=depth
  //rekursive Aufrufe

  //Hoehe von node berechnen und eintragen

  //node ggfs. in Isoklinenliste eintragen
  if(node.depth==node.height) iso=
}
//updateTree(R,0)