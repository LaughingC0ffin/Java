/*
 create: Erzeugt eine neue leere Datenstruktur
 add: F¨ugt ein neues Element hinzu, sodass das gr¨oßte Element jeweils am Anfang der Liste steht
 get: Gibt den Wert des gr¨oßten Elementes in der Datenstruktur zur¨uck
 remove: Entfernt das gr¨oßte Element aus der Datenstruktur
 empty: Gibt an ob die Datenstruktur leer ist
 */
/*
Erstellen Sie die formale Signatur der oben beschriebenen Operationen. Verwenden Sie dazu E als Menge der
Elemente, H als Menge der HIFO-Datenstrukturen und B als Menge der Wahrheitswerte.
 */
/*
create: -> H
add: H x E -> H
get: H {create}-> E
partiell definiert  => geht auf create nicht
remove: H {create}-> H
partiell definiert
empty: H -> B
 */
/*
b) Erstellen Sie aus der Syntax der Operationen eine vollst¨andige algebraische Definition. Woran erkennen Sie,
dass Sie ausreichend aber nicht zu viele Axiome erstellt haben?
 */
/*
empty(create()) -> true
empty(add(e,h)) -> false
remove(add(e,h)) -> if(h == create) h
                    else {
                      if(e>get(h)) return h
                      else add(e,remove(h))
                      }
get(add(e,h))  ->  if(h == create) return e
                    else{
                    if(e>get(h)) return e
                    else get(h)
                    }
 */

abstract class HIFO // generisch
case class Empty() extends HIFO
case class Stk(a:Int , s:HIFO) extends HIFO

/*
 create: Erzeugt eine neue leere Datenstruktur
 add: F¨ugt ein neues Element hinzu, sodass das gr¨oßte Element jeweils am Anfang der Liste steht
 get: Gibt den Wert des gr¨oßten Elementes in der Datenstruktur zur¨uck
 remove: Entfernt das gr¨oßte Element aus der Datenstruktur
 empty: Gibt an ob die Datenstruktur leer ist
 */
def create():HIFO={
  Empty()
}
def get(stack:HIFO):Int={
  stack match{
    case Stk(a,s)=> return a
    case _ => throw new Exception("bennesch hat keine haare und keinen charakter")
  }
}
def add(stack:HIFO,element:Int):HIFO={
  stack match{
    case Empty()=>Stk(element,Empty())
    case Stk(e,hifo)=> {
      if(element < e) add(hifo,element)
      else Stk(element,hifo)
    }
  }
  return stack
}
var liste:Stk = Stk(55,Stk(50, Stk(23,Empty())))
add(liste, 45)

def write(list: HIFO): Unit = list match
{
  case Empty() => print("!")
  case Stk(a, s) => print(a +","); write(s)
}
//def remove(stack:HIFO):HIFO = stack match {
write(liste)
/*
(I1) ∀ (e1,n1), (e2,n2) ε HIFO :
  (n1 = n2) → (e1 = e2)
(I2) ∀ (e,n) ε HIFO : 0 ≤ n < |HIFO|
(I3) ∀ (e1,n1), (e2,n2) ε HIFO :
  (e1 > e2) → (n1 < n2)
 */
