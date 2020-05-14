class StackElem(var value : Int, var next : StackElem = null)

class Stack(var s : StackElem = null)

def emptyStack : Stack = new Stack

def isEmpty(stack : Stack) : Boolean = stack.s == null

def printstack(stack:Stack):Unit={
    var durch = stack.s
    if(isEmpty(stack))println("leer spast")

    while(durch!= null){
        println(durch.value)
        durch = durch.next
    }
}

//Element hinzufuegen
def push(stack : Stack, value : Int) : Unit = {
    if(isEmpty(stack)) stack.s=new StackElem(value)
    else{
        stack.s= new StackElem(value,stack.s)
    }
}

//Oberstes Element entfernen
def pop(stack : Stack) : Unit ={
    if(isEmpty(stack)) throw new IllegalArgumentException("3")
    else stack.s=stack.s.next
}

//Oberstes Element vom Stack liefern
def top(stack : Stack) : Int = {
    if(isEmpty(stack)) throw new IllegalArgumentException("2")
    else return stack.s.value
}

//m=3
// 007
// 076
//  216



def getNumPos(m:Int,pos:Int,num:Int):Int={
    if(pos>=m|| pos <0) throw new IllegalArgumentException("1")
    var tmp= num.toString
    var len = tmp.length()
    //println(tmp)
    for(i<- len until m){
        tmp="0"+tmp
      //  println(tmp)
    }
    return tmp(pos).getNumericValue
}

def radixSort(keys:Array[Int],m:Int):Array[Int] = {
    //k Kellerfaecher anlegen
    var k = 10
    var stack:Array[Stack] = Array.fill(10)(new Stack())

    //Alle Positionen der Schluessel von hinten durchlaufen
    for (pos <- m - 1 to 0 by -1) {
        //1. Phase: Verteilen des Inhalts von keys auf die Kellerfaecher
        for (i <- 0 to keys.length - 1) {
            //push(stack : Stack, value : Int)
            push(stack(getNumPos(m,pos,keys(i))),keys(i))
            //Ablegen von keys(i) auf das Kellerfach mit dem Index,
            //den man keys(i) an der Stelle pos entnimmt
        }

        //2. Phase: Leeren der Kellerfaecher und Schreiben nach keys
        var tmp = keys.length-1
        for (j <- k - 1 to 0 by -1) {
                while(!isEmpty(stack(j))){
                    keys(tmp) = top(stack(j))
                    pop(stack(j))
                    tmp -= 1
                }
        }
        println(keys.mkString(" "))
    }
    return keys
}
var iwas = Array(978, 100, 7, 391, 57, 831, 110, 470, 612, 217)
radixSort(iwas,3)
