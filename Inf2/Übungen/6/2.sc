def measure[A](f: => A, name: String = "f"): Long = {
  var t1 = System.nanoTime()
   f // f is passed as Call-by-Name and is run
     // here between the measurement points
  var t2 = System.nanoTime()
   var diff = (t2-t1)
   println(s"Execution of $name took ${(diff)/1000000000}s "+
     s"(${diff}ns).")
   return diff
   }

 def averageMeasure[A,B](prep: => A, test: A => B, c: Int): Long = {
   var sum = 0L
   for(i <- 1 to c) {
     var a = prep
     sum += measure(test(a))
     }
   println(s"Execution of $c runs took ${sum / c}ns on average")
   return sum / c
   }

def genRandArray(c: Int): Array[Int] = {
   var r = new scala.util.Random
   var min = 0
   var max = 100
   return Array.tabulate(c)( _ => min + r.nextInt((max - min) + 1))
   }