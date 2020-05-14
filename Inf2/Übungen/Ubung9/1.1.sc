abstract class HIFO
case class HiElem(var value:Int,var next:HIFO)extends HIFO
case class Empty() extends HIFO

/*
create: Erzeugt eine neue leere Datenstruktur
 add: F¨ugt ein neues Element hinzu, sodass das gr¨oßte Element jeweils am Anfang der Liste steht
 get: Gibt den Wert des gr¨oßten Elementes in der Datenstruktur zur¨uck
 remove: Entfernt das gr¨oßte Element aus der Datenstruktur
 empty: Gibt an ob die Datenstruktur leer ist
 */
def create():HIFO= Empty()

def add(value:Int,hifo:HIFO):HIFO={
    hifo match {
        case Empty()=>HiElem(value,Empty())
        case HiElem(wert,next)=>{
            if(value>wert)return HiElem(value,hifo)
            else HiElem(wert,add(value,next))
        }
    }
}

def get(hifo:HIFO):Int={
    hifo match {
        case Empty()=>throw new IllegalArgumentException("1")
        case HiElem(wert,next)=>return wert
    }
}

def remove(hifo:HIFO):HIFO={
    hifo match {
        case Empty()=>throw new IllegalArgumentException("2")
        case HiElem(_,next)=>return next
    }
}

def empty(hifo:HIFO):Boolean={
    hifo match {
        case Empty()=>true
        case HiElem(_,_)=> false
    }
}



//// Test der Axiome und der einzelnen F¨alle
// H1
empty(create()) == true //> res: Boolean = true

// H2
get(add(2, create())) == 2 //> res: Boolean = true
get(add(2, add(5, create()))) == 5 //> res: Boolean = true
get(add(5, add(2, create()))) == 5 //> res: Boolean = true

// H3
remove(add(2, create())) == create() //> res: Boolean = true
remove(add(2, add(5, create()))) == add(2, create())
//> res: Boolean = true
remove(add(5, add(2, create()))) == add(2, create())
//> res: Boolean = true

// H4
empty(add(4, create())) == false //> res: Boolean = true
