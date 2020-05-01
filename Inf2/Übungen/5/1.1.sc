class Elem(var value : Int = 0, var rest : Elem = null)

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
def mkString(list : Elem, l : String, m : String, r: String) : String ={
  var k=kopie(list)
  k=append(k,new Elem(0,null))
  var out: List[String] = List()
  out = l::m::out
  while(k.rest != null){
    out= out:::List(k.value.toString):::List(m)
    k=k.rest
  }
  out = out:::List(r)
  return out.mkString(" ")
}

/*
b) Implementieren Sie eine Funktion intersect, welche zu zwei gegebenen aufsteigend sortierten Listen,
die ”Schnittliste“ der gemeinsamen Elemente (ebenfalls aufsteigend sortiert) zur¨uckliefert. Die ¨ubergebenen
Listen sollen unver¨andert bleiben.
 */



val elem1 = new Elem(3,new Elem(4,new Elem(7,new Elem(9,new Elem(17,null)))))
val elem2 = new Elem(4,new Elem(7,new Elem(9,new Elem(11,new Elem(17,null)))))
