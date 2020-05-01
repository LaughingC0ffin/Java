type Bit = Boolean
val Zero = false
val One = true

//Muenzcode als Ringliste von Bits
class BitElem(var value : Bit = Zero, var next : BitElem = null)
var bit1=new BitElem(Zero,new BitElem(One,new BitElem(One,new BitElem(One,new BitElem(Zero,null)))))
//das ist jetzt letztes dings
// println(bit1.next.next.next.next.value)
//println(bit1.next.next.next.next.next.value)
bit1.next.next.next.next.next=bit1
println(bit1.next.next.next.next.next.next.value)
//das sollte jetzt ring liste sein
//var c = bit1
//for(i<- 0 until 20){ println(c.value);c = c.next}

def pruefe(b:BitElem):Unit={
  var c=b
  for(i<- 0 until 20){ println(c.value);c = c.next}
}

/*
a) Implementieren Sie zun¨achst die Funktionen, die eine Zeichenkette in einen M¨unzcode (also in eine Ringliste)
umwandelt und umgekehrt einen M¨unzcode als String zur¨uckgibt. Sie d¨urfen davon ausgehen, dass die
Zeichenkette nur aus 0en und 1en besteht:

 */
/*
var elem1 = new Elem(7,new Elem(1,new Elem(9,null)))
//setzen des letzen zeigers auf erstes element
elem1.rest.rest.rest=elem1
println(elem1.rest.rest.value)
 */
//sinnvolle konstrukte wären length funktion
//folgende probleme mit ring listen
//ich muss das erste element festhalten
//meine durchlauf dings muss ich aber am anfang schon verschieben
//also das erste element manuell?
//oder do while
def code2String(code : BitElem) : String = {
  def bit2String(bit : Bit) = if (bit) "1" else "0"
  //LEERER FALL NICHT ABGEDECKT
  //man könnte nen try catch machen und ne exception raus werfen
  //ring liste => letzes element zeigt auf erstes
  //denke mal ersten zeiger fest halten
  //könnte auch reichen wenn ich mit code abgleiche?
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
  //soll mir ne ringliste erstellen
  //muss iwie am ende den zeiger zum ersten setzen
  //könnte einen zeiger mitlaufen lassen der am ende aufs letze element zeigt
  //ein zeiger als stütze
  //einer der drin rum wurschtelt
  //brauche ne (ring) liste
  //entweder ich klatsche den ersten hier schon rein und iteriere mit index von 1 bis code.lenght
  //oder ich lösche die Zero am ende einfach raus
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