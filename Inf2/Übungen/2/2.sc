

def isPerm(p : Array[Int]) : Boolean = {
  val n = p.length
  var cfm = Array.fill(n)(false)
for(i<- 0 until n){
  if(p(i)<n) cfm(p(i))=true
}
  if(cfm sameElements Array.fill(n)(true)) return true
  else return false
}



def nachstePerm(p:Array[Int]):Unit={

var index:Int= -1
def sucheIndex(p:Array[Int]):Unit={
  for(z<- 0 until p.length-1) if(p(z)<p(z+1)) index=z
}
  sucheIndex(p)
  println(index)

  var klzaind=0
  var diff=p(index)-p.length
println(diff)
  def nachste(p:Array[Int]):Unit={

    for(z<- index+1 until p.length){
      //abs?
      if(p(z)-p(index)<diff &&p(z)>p(index)) klzaind=z;diff=p(z)-p(index)

    }
  }
nachste(p)
  println(klzaind)


/*
def sortiere(index:Int,p:Array[Int]):Array[Int]={
  }

  var nachstelem= p(index)

def suchenachste(p:Array[Int],in:Int):Unit={

  for(z<-in+1 to p.length-1){
    //wenn ich nach links schaue und größere element finde
    //dann nehme ich das als mein pot nächstes element
    //was wenn ich kleineres größtes finde
    if(p(index)<p(z)   &&   nachstelem>p(z)) nachstelem=p(z)
  }
}

suchenachste(p,index)
println(nachstelem)
 */
}

var e = Array(0,3,2 ,1 ,5 ,8 ,7 ,6 ,4)
nachstePerm(e)