var A = Array.range(0, 10)

val n= 5
val k = 3

def programm1(A:Array[Int]):Array[Int]= {

    val n= 5
    val k = 3
    for (i <- 0 to (k - 1) / 2) {
    val h = A (i)
    A (i) = A (k - 1 - i)
    A (k - 1 - i) = h
}

    for (i <- 0 to (n - 1 - k) / 2) {
    val h = A (k + i)
    A (k + i) = A (n - 1 - i)
    A (n - 1 - i) = h
}


    for (i <- 0 to (n - 1) / 2) {
    val h = A (i)
    A (i) = A (n - 1 - i)
    A (n - 1 - i) = h
}
    return A;
}
//gesamt
//0, 1, 2, 3, 4,     5, 6, 7, 8, 9
//3 4 0 1 2       5 6 7 8 9



//Programmst¨uck (P2):
def programm2(A:Array[Int]):Array[Int]= {
    for (i <- 0 to (k - 1)) {
    val h = A(i)
    A(i) = A(k - 1 - i)
    A(k - 1 - i) = h
    }


for (i <- 0 to (n - 1 - k)) {
    val h = A(k + i)
    A(k + i) = A(n - 1 - i)
     A(n - 1 - i) = h
    }

for (i <- 0 to (n - 1)) {
    val h = A(i)
     A(i) = A(n - 1 - i)
     A(n - 1 - i) = h
     }
    return A
}

def programm3(A:Array[Int]):Array[Int]= {
    for (i <- 0 to k - 1) {
        val h = A(0)
        for (j <- 0 to n - 2) {
            A(j) = A(j + 1)
        }
        A(n - 1) = h
    }
    return A
}

def programm4(A:Array[Int]):Array[Int]= {
    for (i <- 0 to n - 1) {
    val h = A(0)
    for (j <- 0 to n - 2) {
        A(j) = A(j + 1)
         }
     A(n - 1) = h
    }
    return A
}

println(A.mkString(" "))
println("programm1:  "+programm1(A).mkString(", "))
println("programm2:  "+programm2(A).mkString(", "))
println("programm3:  "+programm3(A).mkString(", "))
println("programm4:  "+programm4(A).mkString(", "))
