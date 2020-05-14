abstract class HIFO()
case class Empty() extends HIFO
case class HiElem(var value:Int = 0,var next:HIFO = Empty())extends HIFO
/*
create: Erzeugt eine neue leere Datenstruktur
 add: F¨ugt ein neues Element hinzu, sodass das gr¨oßte Element jeweils am Anfang der Liste steht
 get: Gibt den Wert des gr¨oßten Elementes in der Datenstruktur zur¨uck
 remove: Entfernt das gr¨oßte Element aus der Datenstruktur
 empty: Gibt an ob die Datenstruktur leer ist
 */
def create():HIFO={
    return Empty()
}

def add(value:Int,hifo:HIFO):HIFO={
    hifo match {
        case Empty()=>return HiElem(value,Empty())
        case HiElem(valu,next)=>{
            if(value>valu) return HiElem(value,hifo)
            else HiElem(valu,add(value,next))
        }
    }
}

def get(hifo:HIFO):Int={
    hifo match {
        case Empty()=> throw new IllegalArgumentException("LUCKAS")
        case HiElem(value,next)=> return value
    }
}

def remove(hifo:HIFO):HIFO={
    hifo match{
        case Empty() => throw new IllegalArgumentException("LUCKAS 2")
        case HiElem(value,next)=> return next
    }
}

def empty(hifo:HIFO):Boolean={
    hifo match {
        case Empty()=> return true
        case HiElem(_,_)=> return false
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
