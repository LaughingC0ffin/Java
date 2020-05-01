class StackElem(var value : Int, var next : StackElem = null)
class Stack(var s : StackElem = null)

def emptyStack : Stack = new Stack

//Pruefen ob Stack leer
def isEmpty(stack : Stack) : Boolean = stack.s == null
//Implementieren Sie folgende Funktionen und Prozeduren. L¨osen Sie, wo geboten, eine IllegalArgument
//  Exception mit geeignetem Fehlertext aus!

//Element hinzufuegen
def push(stack : Stack, value : Int) : Unit ={
  //hinten dran hängen
  //vielleicht empty fall extra
  if(isEmpty(stack)) stack.s= new StackElem(value)
  else{
    var durch = stack.s
    while(durch.next!=null){
      durch =durch.next
    }
    durch.next=new StackElem(value)
  }
}

//ich füge neue elemente VORNE ein

//Oberstes Element entfernen
def pop(stack : Stack) : Unit = {
  //prüfen ob leer
  if(isEmpty(stack)) throw new Exception("spast")
  stack.s = stack.s.next

}

//Oberstes Element vom Stack liefern
def top(stack : Stack) : Int = {
  //prüfen ob leer
  //DAS VERFICKTE ERSTE ELEMENT
  //nicht das letze
  //glaube ich
  //kein plan
  if(isEmpty(stack)) throw new Exception("spast")
  return stack.s.value
}


def printstack(stack:Stack):Unit={
  var durch = stack.s
  if(isEmpty(stack))println("leer spast")

  while(durch!= null){
    println(durch.value)
    durch = durch.next
  }
}


var s = new Stack(new StackElem(10,new StackElem(30,new StackElem(110,null))))
var stack2 = emptyStack

push(stack2,10)
push(stack2,20)
push(stack2,33)
push(stack2,44)
printstack(stack2)
top(stack2)
pop(stack2)
printstack(stack2)

/*
d) Definieren Sie eine Prozedur getNumPos, welche bei gegebenen m maximalen Stellen pro Schl¨ussel, an der
Stelle pos, den Wert von num zur¨uckgibt. Zum Beispiel soll f¨ur m = 3; pos = 2;num = 216 das Ergebnis 6
sein.
*/
def getNumPos(m:Int,pos:Int,num:Int):Int={
  //m ist maximale anzahl an stellen der schlüssel
  //an der stelle pos
  //von num
  //216 an der pos 2 ergibt also 6

  return -1
}

/*
e) Definieren Sie nun eine Prozedur radixsort, welche einen Array keys : Array[Int] nach dem Radixsort-
Verfahren sortiert. U¨ berlegen Sie sich vorab, welche Parameter dieser Prozedur zu u¨bergeben sind.
 */

//KANN sein das die fächer über ein array mit den stacks ist