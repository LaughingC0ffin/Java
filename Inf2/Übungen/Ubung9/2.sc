//Zeichenkette s wird der Hash-Code h (vom Typ Int) wie
def hash(s:String):Int= {
    var h = 0
    for (c <- s) {
        h = 31 * h + c
    }
    return h
}
//println(hash("luckas"))
//println("luckas".hashCode)
def hashArray2(array: Array[String],m:Int):Unit={
    for(i<- array){
        println(i+":  "  +Math.abs(hash(i))%m)
    }
}

def hashArray(array: Array[String]):Unit={
    for(i<- array){
        println(i+":  "  +hash(i))
    }
}

def hashhash(array:Array[String],start:Int,end:Int):Unit={
    for(i<-start to end){
        println("__________")
        println("Fuer m = "+i )
        hashArray2(array,i)

    }
}
var iwas = Array("Anna", "Bob", "Dennis", "Julia", "Karl", "Lena", "Maike", "Markus" , "Sarah")
//hashArray2(iwas,30)
//Markus?!
hashhash(iwas,30,37)
