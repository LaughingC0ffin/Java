type Bit = Boolean
val Zero = false
val One = true

//Muenzcode als Ringliste von Bits
class BitElem(var value : Bit = Zero, var next : BitElem = null)
var bit1=new BitElem(Zero,new BitElem(One,new BitElem(One,new BitElem(One,new BitElem(Zero,null)))))

bit1.next.next.next.next.next=bit1



def pruefe(b:BitElem):Unit={
  var c=b
  for(i<- 0 until 20){
    println(c.value)
    c = c.next}
}
pruefe(bit1)
/*
a) Implementieren Sie zun¨achst die Funktionen, die eine Zeichenkette in einen M¨unzcode (also in eine Ringliste)
umwandelt und umgekehrt einen M¨unzcode als String zur¨uckgibt. Sie d¨urfen davon ausgehen, dass die
Zeichenkette nur aus 0en und 1en besteht:
 */

def code2String(code : BitElem) : String = {
  def bit2String(bit : Bit) = if (bit) "1" else "0"

  var zeig=code
  //brauche ausgabe string
  var ausgabe=""
  do{
    ausgabe=ausgabe+bit2String(zeig.value)
    zeig=zeig.next
  }while(zeig!=code)
  return ausgabe
  }

def string2Code(code : String) : BitElem = {
   def char2Bit(c : Char) : Bit = if (c == '0') Zero else One
  var out = new BitElem()
  var verb=out
  for(i<-code){
    verb.next=new BitElem(char2Bit(i))
    verb=verb.next
  }
  out=out.next
  verb.next=out
  return out
  }
//code2String(bit1)
//var l = string2Code("1001")
//pruefe(l)

/*
b) Implementieren Sie eine Funktion equalsCoinCode, die ¨uberpr¨uft, ob zwei M¨unzcodes identisch sind.
Ob zwei M¨unzcodes spiegelverkehrt zueinander sind und/oder durch Drehung auseinander hervorgehen, soll
hierbei noch nicht betrachtet werden:
 */
def equalsCoinCode(code1 : BitElem, code2 : BitElem) : Boolean ={
  //man könnte auch einfach die elemente paarweise in einer while schleife
  //vergleichen und sobald eins anders ist => falls
  if(code2String(code1)==code2String(code2)) return true
  else return false
}
/*
c) Implementieren Sie nun eine Funktion, die zu einem ¨ubergebenenM¨unzcode den spiegelverkehrtenM¨unzcode
bestimmt:
 */
def revCoinCode(code : BitElem) : BitElem = {
  var a = code2String(code)
  a = a.reverse
  return string2Code(a)
}
/*
d) Die U¨ berpru¨fung auf Gleichheit zweier Mu¨nzcodes unter Beru¨cksichtigung von ”spiegelverkehrten“ sowie
aus Drehung hervorgehenden M¨unzcodes kann auf verschiedene Art und Weise realisiert werden. Hier sollen
zu vergleichende M¨unzcodes zun¨achst normiert werden. Eine geeignete Normierung ist die lexikographisch
kleinste Bitrepr¨asentation.
Beispiel: Der M¨unzcode 1001 normiert erg¨abe 0011, der M¨unzcode 100110 erg¨abe 001011 (hier spiegelverkehrt).
Implementieren sie dazu eine Funktion normCoinCode, die einenM¨unzcode auf dieser Grundlage
normiert.

Legen Sie dabei nur eine Kopie der originalen Liste an, wenn dies erforderlich ist.
Tipp: Implementieren Sie zun¨achst eine Hilfsfunktion, die lediglich die Drehung ber¨ucksichtigt. Im zweiten
Schritt vergleichen Sie, ob sich der M¨unzcode lexikographisch weiter verkleinern l¨asst, wenn die M¨unze umgedreht
wird.
 */
//kann sein das die eins immer vor die nächste eins geschoben wird?
//was soll mit drehen sein?
//drehen vielleicht indem man "einfach" ein element nach rechts springt?
//könnte man mit hilfszeiger machen der einfach nur durch springt
//spiegeln
//suche dann immer kleinstes
//entweder man vergleicht die codes oder strings
//scala kann auch strings vergleichen
def normCoinCode(code : BitElem) : BitElem ={
  var kleinstes= code2String(code)
  //durchläufer
  var durch= code
  //var akt = code2String(code)
  do{
    if(code2String(durch)<kleinstes)kleinstes = code2String(durch)
    if(code2String(revCoinCode(durch))<kleinstes) kleinstes= code2String(revCoinCode(durch))
    durch=durch.next
  }while(durch != code)
  return string2Code(kleinstes)
}
code2String(normCoinCode(string2Code("100110")))
/*
var bit2=new BitElem(One,new BitElem(One,new BitElem(Zero,new BitElem(One))))
bit2.next.next.next.next=bit2
pruefe(bit2)
var f= normCoinCode(bit2)
pruefe(f)
 */

class Coin(val code : BitElem, val value : Int)
//Menge von Muenzen als einfach verkettete Liste mit Dummy
class CoinElem(val coin : Coin = null, var next : CoinElem = null)

def createCoin(code : BitElem, value : Int) = new Coin(normCoinCode(code), value)

val coin10 = createCoin(string2Code("0111"), 10)
val coin20 = createCoin(string2Code("1001"), 20)
val coin30 = createCoin(string2Code("0000"), 30)

def emptyCoinSet = new CoinElem

/*
Implementieren Sie folgende Funktionen und Prozeduren. Dabei d¨urfen Sie annehmen, dass die M¨unzen mit
createCoin erstellt wurden, die M¨unzcodes also bereits normiert sind.
 */
//Muenzen in der Form "Muenze mit Wert von 10 (0111)" ausgeben
//unsicher
def printCoinSet(coinSet : CoinElem) : Unit={
  var durch = coinSet.next
  while(durch != null){
    println("Muenze mit wert "+durch.coin.value +"( "+ code2String(durch.coin.code) +" )"   )
    durch=durch.next
  }
}
//unsicher
//Einer Muenzmenge eine Muenze hinzufuegen
def addCoin(coinset : CoinElem, coin : Coin) : Unit = {

  if(coinset.next==null) {
    coinset.next=new CoinElem(coin,null)
  }else {
    var durch = coinset.next
    while (durch.next != null) {
      durch = durch.next
    }
    durch.next = new CoinElem(coin, null)
  }
}
 
//Wert anhand des gelesenen Codes einer eingeworfenen Muenze erkennen
def getValue(coinset : CoinElem, code : BitElem) : Int = {
  var durch = coinset.next
  var out = -1
  while(durch!= null){
    if(equalsCoinCode(normCoinCode(durch.coin.code),normCoinCode(code))) out = durch.coin.value
    durch=durch.next
  }
  return out
}

var a = new CoinElem()
addCoin(a,coin10)
addCoin(a,coin20)
addCoin(a,coin30)

getValue(a,string2Code("1001"))
/*
Hinweis: Da der Zeiger auf eine Münzmenge immer erhalten bleiben soll, ist hier ein Dummy-Element erforderlich.
Dieses Element ist bei sämtlichen Funktionen und Prozeduren zu berücksichtigen.
 */