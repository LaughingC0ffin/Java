
def getNumPos(m:Int,pos:Int,num:Int):Int={

  if(pos >= m) throw new Exception("spast")
  //nullen anhängen -_-
  var a = (num.toString).length
  var b = num.toString
  while(a<m){
    b= "0"+b
    a+=1
  }

  return b(pos)
}

println(getNumPos(3,1,6))


