class StackElem(var value : Int, var next : StackElem = null)
class Stack(var s : StackElem = null)
def emptyStack : Stack = new Stack
def isEmpty(stack : Stack) : Boolean = stack.s == null

//alles versager
def push(stack : Stack, value : Int) : Unit ={
  if(isEmpty(stack)) stack.s= new StackElem(value,null)
  else {
    stack.s = new StackElem(value,stack.s)
  }
}
def pop(stack : Stack) : Unit = {
  //prüfen ob leer
  if(isEmpty(stack)) throw new Exception("spast")
  stack.s = stack.s.next
}
def top(stack : Stack) : Int = {
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
/*
d) Definieren Sie eine Prozedur getNumPos, welche bei gegebenen m maximalen Stellen pro Schl¨ussel, an der
Stelle pos, den Wegrt von num zur¨uckgibt. Zum Beispiel soll f¨ur m = 3; pos = 2;num = 216 das Ergebnis 6
sein.
*/
def getNumPos(m:Int,pos:Int,num:Int):Int={
  if(pos >= m) throw new Exception("spast")
  //nullen anhängen -_-
  var a = (num.toString).length
  var b = num.toString.map(_.toString.toInt).toList
  while(a<m){
    b= List(0):::b
    a+=1
  }
  return b(pos)
}
/*
e) Definieren Sie nun eine Prozedur radixsort, welche einen Array keys : Array[Int] nach dem Radixsort-
Verfahren sortiert. U¨ berlegen Sie sich vorab, welche Parameter dieser Prozedur zu u¨bergeben sind.
 */
def radixsort(m:Int,keys:Array[Int],k:Int):Array[Int]={
  var keller= Array.fill(k)(new Stack())
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