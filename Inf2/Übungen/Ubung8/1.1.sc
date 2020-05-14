class StackElem[T](var value : T, var next : StackElem[T] = null)

class Stack[T](var s : StackElem[T] = null)

def emptyStack[T] : Stack[T] = new Stack[T]

def isEmpty[T](stack : Stack[T]) : Boolean = stack.s == null

def printstack[T](stack:Stack[T]):Unit={
    var durch = stack.s
    if(isEmpty(stack))println("leer spast")

    while(durch!= null){
        println(durch.value)
        durch = durch.next
    }
}

//Element hinzufuegen
def push[T](stack : Stack[T], value : T) : Unit = {
    if(isEmpty(stack)) stack.s=new StackElem(value)
    else{
        stack.s= new StackElem(value,stack.s)
    }
}

//Oberstes Element entfernen
def pop[T](stack : Stack[T]) : Unit ={
    if(isEmpty(stack)) throw new IllegalArgumentException("3")
    else stack.s=stack.s.next
}

//Oberstes Element vom Stack liefern
def top[T](stack : Stack[T]) : T = {
    if(isEmpty(stack)) throw new IllegalArgumentException("2")
    else return stack.s.value
}

//m=3
// 007
// 076
//  216



def getNumPos(m:Int,num:Int,pos:Int):Int={
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
//m=5
//1
//Array("alice", "bob", "eve", "trent")
def getStringPos(m:Int,string : String,pos:Int):Int={
    if(pos>=string.length) return  0
    else string(pos).getNumericValue-10
}


def radixSort[T](k:Int,keys:Array[T],m:Int,getKeyPos: (Int, T, Int)=>Int):Array[T] = {
    //k Kellerfaecher anlegen

    var stack:Array[Stack[T]] = Array.fill(k)(new Stack())

    //Alle Positionen der Schluessel von hinten durchlaufen
    for (pos <- m - 1 to 0 by -1) {
        //1. Phase: Verteilen des Inhalts von keys auf die Kellerfaecher
        for (i <- 0 to keys.length - 1) {
            //push(stack : Stack, value : Int)
            push(stack(getKeyPos(m,keys(i),pos)),keys(i))
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

//m=5
var iwas2 = Array("alice", "bob", "eve", "trent","eve")
var iwas = Array(978, 100, 7, 391, 57, 831, 110, 470, 612, 217)
//radixSort[Int](10,iwas,3,getNumPos)
radixSort(26,iwas2,5,getStringPos)

