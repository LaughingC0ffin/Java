

var x = 100

def incCounter : Int = {
      x += 1
      return x
      }

def a(u: => Int) = {
      for(i <- 1 to 10) {
           println(u)
           }
      }

 def b(u: Int) = {
     for(i <- 1 to 10) {
          println(u)
           }
      }

a(incCounter)
println()
 b(incCounter)
println(x)

// Legen Sie ein Array mit 10 Eintr¨agen vom Wert 1 an
var c = Array.fill(10)(1)
c(0)=5;
c(c.length-1)=c.length

def printArrayElements(a: Array[Int]): Unit = {
     for(i <- 0 until a.length) {
          println(a(i))
          }
     }

println()
for(i<- c){
     println(i)
}
def reverseArray(a: Array[Int]): Array[Int] = {
      var newArray = new Array[Int](a.length)
     var count= 0;
     for(i <- a.length-1 to 0 by -1) {
          newArray(count)=a(i)
          count +=1
          }
     return newArray
     }
//c.reverse
printArrayElements(c)

printArrayElements(reverseArray(c))
var d = Array.tabulate(10)(i=>i+1)
printArrayElements(d)


// Array der L¨ange 18 mit zuf¨allig gew¨ahlten Eintr¨agen
var test : Array[Int] = Array.tabulate(18)(i=> (Math.random()*100).toInt  )
// Ergebnis> test: Array[Int] = Array(51, 76, 0, 28, 59, 33, 77, 54,10, 33, 33, 8, 1, 32, 6, 96, 82, 63)

def createMatrix(a: Array[Int], maxLength: Int): Array[Array[Int]] = {
     // Anzahl der ben¨otigten Zeilen berechnen
     var rows : Int = Math.ceil(a.length.toDouble / maxLength).toInt

     // Zweidimensionales Array der Gr¨oße rows * maxLength anlegen
     // Alle Zellen sollen mit 0 initialisiert werden
      var erg : Array[Array[Int]] = Array.fill(rows,maxLength)(0)
     //new Array(rows)(new Array[Int](rows))

     for(i <- 0 until rows) {
          for(j <- 0 until maxLength) {
               if(i*maxLength + j < a.length) {
                    erg(i)(j) = a(i*maxLength+j) //i+j
                     }
                }
           }
      return erg
      }
createMatrix(test, 5)

var A = Array.range(0, 10)
printArrayElements(A)
