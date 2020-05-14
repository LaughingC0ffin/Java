class Elem(var value : Int = 0, var rest : Elem = null)



val elem1 = new Elem(7,new Elem(1,new Elem(9,null)))
val elem2 = new Elem()
val elem3 = new Elem(0,new Elem(1,new Elem(2,null)))
val elem4 = new Elem(3,new Elem(4,new Elem(7,new Elem(9,new Elem(17,null)))))
val elem5 = new Elem(4,new Elem(7,new Elem(9,new Elem(11,new Elem(17,null)))))
val elem6 = new Elem(7,new Elem(8,new Elem(17,new Elem(20,null))))



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
a) Implementieren Sie zun¨achst eine Funktion mkString, die eine Liste in einen String umwandelt. In Anlehnung
an die gleichnamige Methode f¨ur Arrays und Scala-Listen soll sie folgender Definition entsprechen:
1 def mkString(list : Elem, l : String,
2 m : String, r: String) : String = ...
Zur¨uckgegeben werden soll ein String, der mit l beginnt, mit r endet und zwischen je zwei Elementen ein m
vorsieht.
Zur Erinnerung: List(1, 2, 3).mkString("(", ", ", ")") liefert "(1, 2, 3)".
 */

def mkString(list : Elem, l : String, m : String, r: String) : String = {
    var durch = list
    var out = l
    if(durch.rest==null) return l+durch.value+r

    while (durch!=null){
        if(durch.rest==null){
            out=out +durch.value + r
            durch=durch.rest
        }else {
            out = out +durch.value + m
            durch = durch.rest
        }
    }
    return out
}

mkString(elem1,"( ",", "," )")

def intersect(eins:Elem,zwei:Elem):Elem={
    var list1 = kopie(eins)
    var list2=kopie(zwei)
   var out = new Elem()

    while(list1!=null&&list2!=null){

        if(list1.value==list2.value){
            out = append(out,new Elem(list1.value))
            list1=list1.rest
            list2=list2.rest
        }
        else if(list1.value<list2.value){
            list1=list1.rest
        }
        else{
            list2=list2.rest
        }
    }

    return out.rest
}
var test1 = intersect(elem6,elem5)
mkString(test1,"(",", ",")")

def intersect2(eins:Elem,zwei:Elem,drei:Elem):Elem={
    return intersect(eins,intersect(zwei,drei))
}

//head, tail, init, last, length, take, drop

def head(elem:Elem):Int={
    return elem.value
}

def tail(elem:Elem):Elem={
    if(elem.rest==null)throw new  IllegalArgumentException("___")
    return elem.rest
}

def init(elem:Elem):Elem={
    if(elem.rest==null) throw new IllegalArgumentException("___")
    var kopiee= kopie(elem)

    var p = new Elem()

    while (kopiee.rest!=null){
        p=append(p,new Elem(kopiee.value))
        kopiee=kopiee.rest

    }
    return p.rest
}

def last(elem:Elem):Int={
    var durch = elem
    while(durch!=null){
        durch=durch.rest
    }
    return durch.value
}

def length(elem:Elem):Int={
    var out = 0;
    var durch = elem
    while(durch!=null){
        out+=1
        durch=durch.rest
    }
    return out
}

//n elemente von vorne raus nehmen
def take(elem:Elem,n:Int):Elem={
    var out = new Elem()
    var durch = elem
    var count = 0;
    while(count<n){
        out = append(out,new Elem(durch.value))
            count+=1
        durch=durch.rest
    }
    return out.rest
}
mkString(elem4,"(",", ",")")
var test3 = take(elem4,3)
mkString(test3,"(",", ",")")

def drop(elem:Elem,n:Int):Elem={
    return take(elem,length(elem)-n)
}

mkString(elem4,"(",", ",")")
var test4 = drop(elem4,3)
mkString(test4,"(",", ",")")