/*
//for
//while
//if
//variablen

var zaehler:Int = 0
def erhohe: Int = {zaehler+=1;zaehler}

def ausgabe(p: => Int):Unit={
  println(p+","+p)
}
ausgabe(erhohe)     //1,2
ausgabe(erhohe)    //3,4

// p wird jedes mal neu ausgewertet mit erhohe
//also bei jedem mal wird zu erhohe gsprungen und p +1
*/
//iwas gibt hier immer einen fehler aus ?!
//________________________________________________________________________________
// INITIALISIERUNG
var R: Array[Double]= new Array[Double](7)
R(0)= 6.0
println(R(0))     // Ausgabe : 6.0
var S = Array("es","geht","auch","so")
var V = Array.ofDim [(Int,Int)] (4)
//ARRAY OF DIMENSION => 4
//Arrays werden über ihre Indizes angesprochen   0-n   wobei n die initialiserungs länge-1 ist
//var R: Array[Double]= new Array[Double](7)
//kann ich also über R(0) bis R(6) ansprechen  => 7 Boxen
//________________________________________________________________________________
//initiativ werte sind automatisch belegt
//0 bei Int
//0.0  bei Double
//false bei booleon
//________________________________________________________________________________
//bei zusammengesetzen brauche ich explizite Initialisierung
//zum beispiel bei V weil ich hier ein array von (Int,Int) Tupeln habe
//var V = Array.ofDim [(Int,Int)] (4)
//hilfe bekommt man z.b mit Array.fill

var R1 = Array.fill (5) ("mein wert mit dem ich fülle")
//das array hat jetzt 5 einträge

var R2 = Array.range(0,5)
println(R2)
//  R2: Array[Int] = Array(0, 1, 2, 3, 4)

//Wertefunktion als Parameter
var R3 = Array.tabulate(5) ((i) => i)
var R4 = Array.tabulate(5) ((i)=>4-i)
//R3: Array[Int] = Array(0, 1, 2, 3, 4)
//R4: Array[Int] = Array(4, 3, 2, 1, 0)
// i kann man sich als durchzähl variable bei der initialisierung vorstellen
//also wenn der Rechner die erste Box erstellt ist i=0
//bei der zweiten dann i = 1
//und so weiter
//________________________________________________________________________________
//Mehrdimensionale Arrays

//Array kann wieder element von Array sein
//zum beispiel wenn man sich matrix anschaut (2 dimensional)
//Beispiel   R: Array [Array[Int]]
//R hat eine bestimmte Länge. also bestimmte anzahl an Boxen.
//und jede Box hat wieder mehrer Boxen drinnen => Array
//Länge der einzelnen Arrays IM Array können variieren

var funfmalsechs = Array.fill(5,6) (1)
// 5x6 "Matrix" gefüllt mit einsen

var Quadrat = Array.tabulate(5,5) ((i,j)=>i-j+5)

//zugriff über "zeilen und spalten index"
//var wert = R(zeile)(spalte)
//________________________________________________________________________________

math.random()
//pseudo zufällige double wert zwischen 0 und 1
(math.random*100).toInt
//erzeugt eine "zufällige Int zahl zwischen 1 und 100
//Array.tabulate(12) (i => (math.random*100).toInt)
//jedes mal ein anderes Array

//zufallszahl zwischen -a und n-a
//(math.random*n).toInt -a
//________________________________________________________________________________
//Arrays in zufälliger Kammform
var K = Array.tabulate(5)(i=>Array.tabulate((math.random*4).toInt+1)(j=>(math.random*100).toInt))
//z.b
//K: Array[Array[Int]] = Array(Array(99, 19, 31, 90), Array(66), Array(19, 65, 76, 97), Array(24), Array(25, 21, 98))
//erzeugt jedes mal ein anderes array
//Array.tabulate(5) erzeugt ein array mit 5 "Boxen"
//die Boxen werden wiederum mit zufällig langen arrays geüllt
//und diese mit zufälligen werten
//________________________________________________________________________________
//Verarbeiten von arrays

//variante 1
//gut wenn die indizes zur verarbeitung nicht benötigt werden
//also wenn ich nicht direkt die "Boxen" ansprechen will

//for (zeile <- R;element<-zeile) verarbeite(element)
//die einzelnen Boxen werden also in "zeile" abgebildet
//und die boxen haben ja wieder Boxen
//und diese werden auf element abgebildet

//variante 2
//verwendet wenn man die einzelnne Indizes braucht zum verarbeiten
//also die Boxen Nummern
//for(z<- (0 until R.lenght); s<- (0 until R(z).lenght)) verarbeite(R(z)(s))
//z gibt quasi die nummer der großen Boxen; also des äußeren Arrays
// in s werden dann die Nummern der inneren arrays angesprochen
//also der Boxen in der Box

//________________________________________________________________________________

//Beispiele für verarbeitung von Kamm Array

//summe
var summe:Int = 0
for(z<- K;el<-z) summe+= el

//kleinster wert
var min:Int= K(0)(0)
for(z<-K;el<-z) if(el<min) min=el

//größter wert nalog mit >

//spiegelung an der Hauptdiagonalen

for(z<-(1 until Quadrat.length);s<-(0 until z)){
  var tmp = Quadrat (z)(s)
  Quadrat(z)(s)= Quadrat(s)(z)
  Quadrat(s)(z)=tmp
}
//den ersten teil sollte man sich merken
//so läuft es eigentlich immer wenn man die indizes ansprechen will
//im zweiten teil werden die einzelnen elemente vertauscht
//das ganze mit einer Hilfsvariablen tmp
//sonst würde ja ein wert beim tauschen verloren gehen

//EIGENE ANMERKUNG: wenn ich alle elemente durchlaufe. tausche ich dann nicht zweimal durch?
//________________________________________________________________________________
//Sortieren

//sortieren Int Array
//grundidee
//durchlaufen vom vorletzten bis zum ersten
//das aktuelle element wird heraus genommen
//das führt zu einer lücke
//nun wird von rechts solange aufgefüllt bis das das auffüll Element kleiner als das entnommene ist
//das entnommene element in die Lücke packen
//umsetzung
var A= Array(0,4,2,7,10,20,4)

//ich fange beim vorletzen element an => also A.lenght-2
//bis zu meinem ersten element => to 0
//und da ich fast ganz hinten anfange gehe ich immer einen schritt zurück => -1
for (index<- (A.length-2 to 0 by -1)){
  var entnommen = A(index)    //Wert den ich einsortiere
  var luckeposition = index    //Index meiner Lücke also wo ich entnommen habe
  //das hier sind einfach die bedingungen die erfüllt sein müssen
  //die erste bedingung stellt sicher das wir nicht über das array hinaus laufen
  //also dass das größte element ganz hinten landet
  //die zweite bedingung schaut ob wir das element weiter verrücken müssen oder nicht
  while ((luckeposition+1<=A.length-1)&& (A(luckeposition+1)<entnommen)){

    A(luckeposition)=A(luckeposition+1)   //von recht auffüllen
    luckeposition+=1                    //lücke rutscht nach recht weiter
  }
  A(luckeposition)=entnommen
}
/*
Beispiel anhand array A
(0,4,2,7,10,20,4)
nimmt sich die 20 raus
(0,4,2,7,10,[],4)
schaut ob rechts kleiner
und ob nicht am ende des arrays
beides trifft zu
also verschiebt er die lücke nach rechts
(0,4,2,7,10,4,[])
jetzt schaut er wieder
sieht das am ende des arrays
also fügt er dort die 20 ein
(0,4,2,7,10,4,20)
geht jetzt zum vor vor letzen element (siehe for )
nimmt sich also hier die 10 raus
(0,4,2,7,[],4,20)
schaut wieder ob am ende und ob rechts kleiner also meine 10
das ist der fall
also verschiebung nach rechts der lücke
(0,4,2,7,4,[],20)
schaut wieder ob am ende oder rechts kleiner
diesmal ist rechts nicht kleiner
also fügt er dort meine 10 ein
(0,4,2,7,4,10,20)
uuuuuund so weiter
 */
//Erg 0 2 4 4 7 10 20
//________________________________________________________
//Array Funktionen

//ausgabe
//ein dim
println(A.mkString(" "))
//zwei dim
for (z<- funfmalsechs) println(z.mkString(" "))

//Array zusammenführen  (es gehen auch mehr als 2)
//Array.concat(R1,R2)
//gibt fehler aus?

//Array.copy(Quelle,qIndex,Ziel,zIndex,anz)
//Aus dem Array Quelle nimmt man sich ab dem Index qIndex genau anz viele Elemente
//und fügt sich im Arry Ziel ab dem dem Index zIndex ein
//überschreibt auch
//gleiche arrays dürfen vorkommen   (Quelle gleich Ziel)

//________________________________________________________
//Multimengen
//{{1,1,2,1,2}} hat 5 elemente    //elememte könne öfter vorkommen
//{1,1,2,1,2} hat 2 elemente      //hier nicht; doppelte werden als eins gezählt
//bsp
//CF für charakteristische funktion
val n:Int = 3   //bsp  das n steht für den parameter der Grundmenge. also bei G3 ist n= 3
var CFm= Array.fill(n) (false)//M=={}     mit boolschen wert merken ob ein wert vorkommt
var CFmm= Array.fill(n)(0)  //MM=={{}}    hier ist relevant wie viele elemente drin vorkommen
//Grundmenge enthält nur werte von 0 bis n-1
//zum beispiel G3 hat werte 0,1,2
//in bezug auf {1,1,2,1,2} wäre das false,true,true
//weil die null ist ja nicht drinnen
//________________________________________________________
//Permutation
//entstehen durch umordnen von werten einer folge
//sind alle auf einem abschntt der natürlichen zahlen bijektive funktionen P
//P(i) gibt die stelle an wohin das ursprüngliche i-te Folgenelement beim umordnen bewegt wird
//verschiebene der elemente im Index
//zu n gibt es n! Pemutationen
//kleinste permutation ist die indentitätsfunktion  (also auf sich selbst)
//permutationne kann man hintereinander ausgeführt werden
//dadurch das bijektiv gibt es auch inverse
//zwei permutationen sind invers zueinander wenn die eine auf die andere angewendet die identitätspermutatuin ergibt

//Arrays der Länge n haben den Indexbereich Gn
//als beispiel. ich habe ein Array der Länge 3 .  dann ist der indexbereich (0 bis 2) die Grundmenge G3 welche ja auch 0 bis 2 ist

type Perm = Array[Int]

//die kleinste permuation(identität) liefer für gebebenes n
//var Id = Array.range(0,n)
//bsp
//für n= 3
var Id = Array.range(0,3)
//Id: Array[Int] = Array(0, 1, 2)

//eine neue permutation kann durch anwenden der Permutation p auf die Permutation q erzeugt werden
//beide Permutationen bewegen sich auf der selben Grundmenge Gn
//implementierung aus vorlesung
def wendenAn(p:Perm,q:Perm):Perm= Array.tabulate(p.length)((i)=> p(q(i)))
val p:Perm=Array(1,2,0)
val q:Perm=Array(2,0,1)
wendenAn(p,q)

//res11: Perm = Array(0, 1, 2)

//prinzip noch näher erklären?
def invers(p:Perm):Perm={
  var erg = new Array[Int] (p.length)
  for(i<- (0 to p.length-1)) erg(p(i))=i
  return erg
}
var P = Array(2,1,4,3,0)
invers(P)            //Array(4,1,0,3,2)
//________________________________________________________
//________________________________________________________
//________________________________________________________
//________________________________________________________