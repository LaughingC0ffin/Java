type IBAN = Array[Char]
def convert2int(sa: Array[Char]): IBAN = {
  var lc = 0 //letter count


  for (i <- 0 until sa.length) if (sa(i).isLetter) lc += 1
  println(lc)
  var converted = new Array[Char](sa.length + lc)
println(converted.length)
  println(sa.length)
  var tmp = 0
  var tmp2 =0
  while (tmp2 <= sa.length-1) {

    if (sa(tmp2).isDigit) {
      converted(tmp) = sa(tmp2)
      tmp += 1
      tmp2 +=1
      println(tmp,tmp2)
    }
    else if (sa(tmp2).isLetter) {
      var t = ""
      t = (sa(tmp2).getNumericValue).toString
      println(t)
      converted(tmp) = t(0)
      converted(tmp + 1) = t(1)
      tmp += 2
      tmp2 +=1
      println(tmp,tmp2)
    }
  }



return converted
}

var iban1 : IBAN = Array[Char]('G', 'B', '8', '2', 'W', 'E', 'S', 'T', '1', '2', '3', '4', '5', '6', '9', '8', '7', '6', '5', '4', '3', '2')
convert2int(iban1)