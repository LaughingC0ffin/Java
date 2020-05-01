class StackElem[T](var value : T, var next : StackElem[T] = null)
class Stack[T](var s : StackElem[T] = null)
def emptyStack[T] : Stack[T] = new Stack[T]
def isEmpty[T](stack : Stack[T]) : Boolean = stack.s == null

//alles versager
def push[T](stack : Stack[T], value : T) : Unit ={
  if(isEmpty(stack)) stack.s= new StackElem(value,null)
  else {
    stack.s = new StackElem(value,stack.s)
  }
}
def pop[T](stack : Stack[T]) : Unit = {
  //prüfen ob leer
  if(isEmpty(stack)) throw new Exception("spast")
  stack.s = stack.s.next
}
def top[T](stack : Stack[T]) : T = {
  if(isEmpty(stack)) throw new Exception("spast")
  return stack.s.value
}

def printstack[T](stack:Stack[T]):Unit={
  var durch = stack.s
  if(isEmpty(stack))println("leer spast")

  while(durch!= null){
    println(durch.value)
    durch = durch.next
  }
}
/*
c) In der nicht generischen Version wurde eine Prozedur getNumPos, welche bei gegebenen m maximalen
Stellen pro Schl¨ussel, an der Stelle pos, den Wert von num zur¨uckgibt, verwendet. Zum Beispiel wurde f¨ur
m = 3; pos = 2;num = 216 das Ergebnis 6 zur¨uckgegeben. Modifizieren Sie diese Prozedur so, dass sie
f¨ur einen beliebigen Typ T funktioniert. Dabei k¨onnen Sie darauf zur¨uckgreifen, dass jeder Typ eine Methode
.toString besitzt, die eine Repr¨asentation als Zeichenkette ausgibt. F¨ur die einzelnen Zeichen dieser
Zeichenkette k¨onnen Sie ¨uber .getNumericValue ein Int-Wert bekommen.
1 // Beispiele
2 scala> "123".toString()(2).getNumericValue
3 res1: Int = 3
45
scala> 123.toString()(2).getNumericValue
6 res2: Int = 3
*/


def getNumPos[T](m:Int,pos:Int,num:T):Int={
  if(pos >= m) throw new Exception("spast")
  var a = (num.toString).length
  var b = num.toString.toList
  while(a<m){
    b= List('0'):::b
    a+=1
  }
  //üngünstig weil numeric value von a die 10 ist
  //man könnte etwas rum spielen und folgendes benutzen
  //if(num.getClass=="abc".getClass)
  //und dann den wert um 10 verringern
  //weil halt das a bei 10 anfängt -_-
  return b(pos).getNumericValue
}
getNumPos(2,1,216)

//'b'.getNumericValue
//'a'.getNumericValue
"123".toString()(2).getNumericValue
'z'.getNumericValue

//es wäre möglich das kleinste element zu bestimmen und  bei getNumPos immer minus diesen wert subtrahieren
//damit ich zum bsp bei chars bei 0 und nicht bei 10 anfange
//wäre die frage ob es bei bool oder generell negative getNumericValues gibt
//antwort: ja das minus zeichen hat den value -1 :((

def radixsort[T](m:Int,keys:Array[T],k:Int):Array[T]={
  var keller= Array.fill(k)(new Stack[T]())
  for(pos<- m -1 to 0 by -1){
    for(i<- 0 until keys.length){
      push(keller(getNumPos(m,pos,keys(i))),keys(i))
    }
    var tmp = keys.length-1
    for(j <- k - 1 to 0 by -1){
      while(!isEmpty(keller(j))){
        keys(tmp)=top(keller(j))
        pop(keller(j))
        tmp-=1
      }
    }
    println(keys.mkString(" "))
  }
  return keys
}
var nochwas= Array("alice", "bob", "eve", "trent")
var iwas = Array(978, 100, 7, 391, 57, 831, 110, 470, 612, 217)
radixsort(3,iwas,10)
radixsort(5,nochwas,35)

//die teile sind noch falsch rum oder? ne