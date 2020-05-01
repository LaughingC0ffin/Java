class StackElem[T](var value : T, var next : StackElem[T]= null)
class Stack[T](var s : StackElem[T]= null)

def emptyStack[T] : Stack[T]= new Stack[T]
def isEmpty[T](stack: Stack[T]) : Boolean = stack.s == null

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
d) Definieren Sie eine Prozedur getNumPos, welche bei gegebenen m maximalen Stellen pro Schl¨ussel, an der
Stelle pos, den Wegrt von num zur¨uckgibt. Zum Beispiel soll f¨ur m = 3; pos = 2;num = 216 das Ergebnis 6
sein.
*/
def getNumPos[T](m:Int,pos:Int,num:T):Int={
  var r = 0
  if(num.isInstanceOf[String]) r = 10
  if(pos >= m) throw new Exception("spast")
  var tmp = num.toString
  while(m != tmp.length){
    tmp = "0" + tmp
  }
  if(tmp(pos).getNumericValue == 0) return tmp(pos).getNumericValue
  else return tmp(pos).getNumericValue - r
}

getNumPos(3,2,"a")
/*
e) Definieren Sie nun eine Prozedur radixsort, welche einen Array keys : Array[Int] nach dem Radixsort-
Verfahren sortiert. U¨ berlegen Sie sich vorab, welche Parameter dieser Prozedur zu u¨bergeben sind.
 */
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
var iwas = Array(978, 100, 7, 391, 57, 831, 110, 470, 612, 217)
radixsort(3,iwas,10)

//die teile sind noch falsch rum oder? ne

var g = Array("alice", "bob", "eve", "trent")

radixsort(5,g,26)
radixsort(3,iwas,10)