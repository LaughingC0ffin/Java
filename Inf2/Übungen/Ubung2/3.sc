val P = Array(7, 3, 0, 2, 5, 1, 6, 4)
val Q = Array(5, 2, 4, 6, 0, 3, 1, 7)
type Perm = Array[Int]
type Cycles = List[List[Int]]

def spiegle(p: Perm): Perm = {
    var res = Array.fill(8)(0)
    for (i <- 0 to 7) res(p(i)) = i
    return res
    }

//p<q
def lexKleiner(p : Perm, q : Perm) : Boolean = {
    var i = 0;
    while(p(i)==q(i)){
        i+=1
    }
    return p(i)<q(i)

}
println(lexKleiner(P,Q))

def drehe(p: Perm): Perm = {
    var Erg = Array.fill(8)(0)
    for(i <- (0 to 7)) Erg(p(i)) = p.length - 1 - i
    return Erg
}

/*
Eine L¨osung P des Damenpromblems gelte als ”neu“, wenn sie sich nicht durch Spiegelungen und/oder
Drehungen in eine lexikographisch kleinere Permutation transformieren l¨asst. Vervollst¨andigen Sie in diesem
Sinn die folgende Definition!
 */
def istNeu(p : Perm) : Boolean = {
    var tmp = p;
    for(i<- 0 until 3){
        if(lexKleiner(tmp, p) || lexKleiner(spiegle(tmp), p)) return false
    }
    return true;
}