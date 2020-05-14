def matrix(n:Int):Array[Array[Int]]= {
  //Initialisiere Matrix
  //Setze Position auf (1, 1)
  // Initialisiere Richtung "nach rechts"
  var pos=(1,1)
  var richt=(0,1)
  var vorlpos=pos
  var zwieb = Array.fill(n + 2, n + 2)(0)
  def init(n: Int): Array[Array[Int]] = {

    //var zwieb2 = Array.tabulate(n+2,n+2)((i,j)=>if(i==0 ||j==0)=>-1)
    //gefällt mir nicht
    //geht safe auch eleganter
    //hätte gerne ne if abfrage beim initialisieren des arrays

    /*
    def init(n : Int) = Array.tabulate(n + 2, n + 2)
2 ((r, c) => if (r == 0 || c == 0 || r == n + 1 || c == n + 1) -1 else 0)
     */


    for (i <- 0 until n + 2) zwieb(0)(i) = -1
    for (i <- 0 until n + 2) zwieb(n + 1)(i) = -1
    for (i <- 0 until n + 2) zwieb(i)(0) = -1
    for (i <- 0 until n + 2) zwieb(i)(n + 1) = -1

    return zwieb
  }
  init(n)
  // 1 bis 9
  for (i <- 1 to n * n) {
    //Besetze Position mit i
    zwieb(pos._1)(pos._2)=i
    //Bestimme mithilfe der Richtung vorlaeufige neue Position
    vorlpos=(pos._1+richt._1,pos._2+richt._2)
    //println(vorlpos)

    //vorlaeufige Position auf Rand oder bereits besetzt
    if (zwieb(vorlpos._1)(vorlpos._2)== -1 || zwieb(vorlpos._1)(vorlpos._2)!=0) {
      //Aendere Richtung (rechts um)
       //Bestimme mithilfe der Richtung neue Position
      //(a,b) => (b,-a)
      richt=(richt._2,-1*richt._1)
      pos=(pos._1+richt._1,pos._2+richt._2)
      //println(richt)
      //println(pos)
     }
      //vorl pos  == 0
    else {
      //Uebernimm vorlaeufige Position als neue Position
      pos=vorlpos
      }
    }
return zwieb
}
var zwiebel = matrix(5)

for(i<- zwiebel){
  println(i.mkString(" "))
}
