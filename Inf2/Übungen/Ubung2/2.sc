type Perm = Array[Int]
type Cycles = List[List[Int]]

def cyclesOf(p : Perm) : Cycles = {
    val n = p.length
    var cs : Cycles = List()
    var cfm = Array.fill(n)(false)
    for (i <- 0 to n - 1) {
        if (!cfm(i)) {
            cfm(i) = true
            var cycle = List(i)
            var j = p(i)
            while (j != i) {
                 cfm(j) = true
                 cycle = cycle ::: List(j)
                 j = p(j)
                 }
             cs = cs ::: List(cycle)
            }
         }
    return cs
    }

def printCycles(p : Perm) : Unit = {
    for (c <- cyclesOf(p)) println(c.mkString("(", ", ", ")"))
    }

var perm1 = Array(0,3, 5, 1, 8, 4, 7, 6, 2)
var perm2 = Array(0, 3, 5, 1, 8, 6, 2, 4, 7)
var perm3 = Array(0, 3, 2, 1, 5, 8, 7, 6, 4)
println(perm1.mkString(" "))
printCycles(perm1)
println()
println(perm2.mkString(" "))
printCycles(perm2)
println()
println(perm3.mkString(" "))
printCycles(perm3)


def permByCycles(cs : Cycles) : Perm = {
    //groessten enthaltenen Zahlenwert in cs bestimmen
    def maxEl(cs: Cycles): Int = {
        //...
        var max = 0;
        //type Cycles = List[List[Int]]
        max = cs.max.max
        return max;
        }


    var p = Array.fill(maxEl(cs) + 1)(0)

    for (c <- cs) {
        //erster Cyclus
         var i = c.head
        //gehen sie die liste durch (Cyclus)
         for (j <- c.tail) {
             //fance umtauschungen
            p(i) = j
             i = j
             }
         p(i) = c.head
        }

    return p
     }

