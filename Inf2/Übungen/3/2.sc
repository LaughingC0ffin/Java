type Sudoku = Array[Array[Int]]
/*
def gleich(s1: Sudoku, s2: Sudoku): Boolean ={
  if(s1 sameElements s2) true
  else false


}

gleich(eins,zwei)
*/
var eins:Sudoku= Array.fill(9,9)(1)
var zwei:Sudoku= Array.fill(9,9)(1)
def gleich2(s1: Sudoku, s2: Sudoku): Boolean ={

for(a<-0 until s1.length;b<-0 until s1.length) {
  if (s1(a)(b) != s2(a)(b)) return false
}
  true
}
gleich2(eins,zwei)


type Perm=Array[Int]
//noch unsicher
def wendeAuf(p:Perm,s:Sudoku):Sudoku={
  /*for(i<-0 until s.length;a<-0 until s.length) s(i)(a)=p(s(i)(a))
  return s*/
  Array.tabulate(9,9)((breite,hoehe)=> p(s(breite)(hoehe)))
}
//return Array.tabulate((breite,hoehe)=> p(s(breite)(hoehe)))

val p:Perm=Array(0,4,2,3,4,5,6,7,8)
wendeAuf(p,eins)


