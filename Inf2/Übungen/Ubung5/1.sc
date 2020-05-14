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

def mkString(list : Elem, l : String, m : String, r: String) : String = {
    var out = "";
    var durch = list
    if(durch.rest==null) return l + durch.value+r
    out=l+out
    while(durch!=null){
        if(durch.rest == null) {
            out = out+durch.value
            durch = durch.rest
        }
        else {
            out = out + durch.value + m
            durch = durch.rest
        }
    }
    out = out+r
    return out
}
mkString(elem1,"( ",", "," )")

/*
b) Implementieren Sie eine Funktion intersect, welche zu zwei gegebenen aufsteigend sortierten Listen,
die ”Schnittliste“ der gemeinsamen Elemente (ebenfalls aufsteigend sortiert) zur¨uckliefert. Die ¨ubergebenen
Listen sollen unver¨andert bleiben.
 */
def intersect(eins:Elem,zwei: Elem): Elem ={
    var list1 = kopie(eins)
    var list2 = kopie(zwei)
    var out  =new Elem()
    while(list1!=null||list2!=null){
    if(list1.value==list2.value){
        out = append(out,new Elem(list1.value))
        list1 = list1.rest
        list2= list2.rest
    }
    else if(list1.value>list2.value){
        list2 = list2.rest
    }
        else{ list1=list1.rest}
    }
    return out
}
var a = intersect(elem4,elem5)
mkString(a,""," ","")


/*
c) Implementieren Sie eine Funktion, die die Schnittliste dreier Listen bestimmt, ohne die Funktion aus der
vorherigen Teilaufgabe zu verwenden.
 */
def intersect2(eins:Elem,zwei: Elem,drei:Elem): Elem ={
    var out = new Elem()
    var list1= kopie(eins)
    var list2 = kopie(zwei)
    var list3 = kopie(drei)

    while( list1!=null && list2!=null && list3!=null){
        if(list1.value==list2.value && list2.value==list3.value){
            out = append(out,new Elem(list1.value))
            list1 = list1.rest
            list2= list2.rest
            list3=list3.rest
        }
        else if(list1.value<list2.value || list1.value< list3.value){
            list1 = list1.rest
        }
        else if(list2.value<list1.value || list2.value< list3.value){
            list2 = list2.rest
        }
        else{
            list3 = list3.rest
        }
    }
    return out
}

var b = intersect2(elem4,elem5,elem6)
mkString(b,""," ","")

/*
head, tail, init, last, length, take, drop
Sehen Sie bei ung¨ultig ¨ubergebenen Argumenten das Werfen entsprechender Exceptions vor.
 */

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
