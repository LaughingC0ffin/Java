class Elem(var value : Int = 0, var rest : Elem = null)
val elem1 = new Elem(7,new Elem(1,new Elem(9,null)))
val elem2 = new Elem()
val elem3 = new Elem(0,new Elem(1,new Elem(2,null)))
//val elem4 = new Elem(3,new Elem(4,new Elem(7,new Elem(9,new Elem(17,null)))))
//val elem5 = new Elem(4,new Elem(7,new Elem(9,new Elem(11,new Elem(17,null)))))
def kopie (e:Elem) : Elem = {
  if (e == null)
    return null
  else
    return new Elem(e.value, kopie(e.rest))
}
//var k = kopie (meineListe)
def append (l1:Elem, l2:Elem) : Elem = {
  var p1 : Elem = kopie(l1)
  var p2 : Elem = kopie(l2)
  var h : Elem = p1
  if (h == null) return p2 // p1 ist leer, p2 zurück
  while (h.rest != null) h = h.rest // Ende von p1 suchen
  h.rest = p2 // ganz p2 hinten anhängen
  return p1 // Anfang der Gesamtliste zurückgeben
}
def cons (x:Int, l:Elem) : Elem = {
  return new Elem (x, kopie(l))
}
/*
Des Weiteren konnen die Funktionen cons und append aus der Vorlesung (vgl. K3-28) als gegeben betrachtet
werden. Zudem ist die Funktion copy gegeben, die eine Kopie einer Liste erstellt.

a) Implementieren Sie zunächst eine Funktion mkString, die eine Liste in einen String umwandelt. In Anlehnung
an die gleichnamige Methode f¨ur Arrays und Scala-Listen soll sie folgender Definition entsprechen:

Zur¨uckgegeben werden soll ein String, der mit l beginnt, mit r endet und zwischen je zwei Elementen ein m
vorsieht.
Zur Erinnerung: List(1, 2, 3).mkString("(", ", ", ")") liefert "(1, 2, 3)".
 */

//a)
def mkString(list : Elem, l : String, m : String, r: String) : String ={

var out:List[String] = List()
  out= List(l):::out
  var q= append(list,new Elem())
  while(q.rest!=null){
    out=out:::List(q.value.toString):::List(m)
    q=q.rest
  }

  out = out.init
  out=out:::List(r)
  return out.mkString("")
}
println(mkString(elem1,"( ","m"," )"))
/*
b) Implementieren Sie eine Funktion intersect, welche zu zwei gegebenen aufsteigend sortierten Listen,
die ”Schnittliste“ der gemeinsamen Elemente (ebenfalls aufsteigend sortiert) zur¨uckliefert. Die ¨ubergebenen
Listen sollen unver¨andert bleiben.
wäre relevant welche list quasi den kleinsten wert hat
einfache lösung wäre einfach alle gemeinsamen dings raus zu holen mit index of und dann zu sortieren
aber es gibt bestimmt elegantere variante
 */
def intersect ( list1:Elem,list2:Elem):Elem={
  var l1=kopie(list1)
  var l2 = kopie(list2)
  var out = new Elem()
  while(l1.rest!=null && l2.rest!=null){
    if(l1.value==l2.value){
      out=append(out,new Elem(l1.value))
      l1=l1.rest
      l2=l2.rest
    }
    else if(l1.value> l2.value){
      l2=l2.rest
    }
    else if(l1.value< l2.value){
      l1=l1.rest
    }
  }
  return out.rest
}

val elem4 = new Elem(3,new Elem(4,new Elem(7,new Elem(9,new Elem(17,null)))))
val elem5 = new Elem(4,new Elem(7,new Elem(9,new Elem(11,new Elem(17,null)))))
mkString(intersect(elem4,elem5),""," ","")

//c
//gleiche mit 3 listen aber ohne b zu verwenden
def intersect2(list1:Elem,list2:Elem,list3:Elem):Elem={
  //es fehlt dummy am ende
  var l1=kopie(list1)
  var l2=kopie(list2)
  var l3=kopie(list3)
  var out = new Elem()
  while(l1.rest != null && l2.rest != null && l3!=null) {
    if (l1.rest.value == l2.rest.value && l2.rest.value== l3.rest.value) {
      out=append(out,new Elem(l1.rest.value))
      l1=l1.rest
      l2=l2.rest
      l3=l3.rest
    }
    else if (l1.rest.value < l2.rest.value ||l1.rest.value < l3.rest.value) {
      l1=l1.rest
    }
    else if (l2.rest.value < l1.rest.value ||l2.rest.value < l3.rest.value) {
      l2=l2.rest
    }
    else if (l3.rest.value < l2.rest.value ||l3.rest.value < l1.rest.value) {
      l3=l3.rest
    }
  }
  return out.rest
}

//d
//head, tail, init, last, length, take, drop

def head(list:Elem):Int={
return list.value
}
def tail(list:Elem):Elem={
return list.rest
}
//Returns the list without its last element.

def init(list:Elem):Elem= {
  var liste = kopie(list)
  var k: Elem = new Elem()
  //liste=append(liste,new Elem(0,null))

  while (liste.rest != null) {
    k = append(k, new Elem(liste.value))
    liste = liste.rest
  }
  return k.rest
}

//Returns the last element of this list.
def last(list:Elem):Elem={
  var p = list
  while(list.rest!=null) p=p.rest
  return p
}
def length(list:Elem):Int={
  var akt : Elem = list
  var anz = 0
  while (akt != null) { anz += 1; akt = akt.rest }
  return anz
}
//Returns the n first elements of this list, or else the whole list, if it has less than n elements
def take(list:Elem,n:Int):Elem={
  var a  = 0
  var k = kopie(list)
  var ne = new Elem()
  if(length(list)<n) return list
  while(a<n) {
    ne = append(ne,new Elem(k.value,null))
    a+=1
    k = k.rest
  }
  return ne.rest
}
//Returns the list without its n first elements. If this list has less than n elements, the empty list is returned
def drop(list:Elem,n:Int):Elem={
  var a  = 0
  var k = kopie(list)
  if(length(list)<n) return new Elem()
  while(a<n){
    k=k.rest
    a+=1
  }
  return k
}


//var l = init(elem1)



