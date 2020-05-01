type IBAN = Array[Char]
def verify(iban: IBAN): Boolean = {

  def rearrange(iban: IBAN): Array[Char] = {
    if (iban.length < 5) {
      throw new Exception("Das ist keine IBAN")
    }
    var rearranged = new Array[Char](iban.length) //Kopie des IBANArrays

    Array.copy(iban, 0, rearranged, rearranged.length - 4, 4)
    Array.copy(iban, 4, rearranged, 0, iban.length - 4)

    return rearranged
  }



  def convert2int(sa: Array[Char]):BigInt = {
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
    try {
      return BigInt(converted.mkString)
    }
    catch {
      case e: NumberFormatException =>
        throw new Exception("Da hat was nicht geklappt")
    }
  }


  try {
    if ((convert2int(rearrange(iban)) )% 97 == 1) return true
    else return false
       } catch {
    case e: Exception => throw new Exception("ungultige IBAN")
  }
}
//Array.copy(Quelle,qIndex,Ziel,zIndex,anz)


var iban1 : IBAN = Array[Char]('G', 'B', '8', '2', 'W', 'E', 'S', 'T', '1', '2', '3', '4', '5', '6', '9', '8', '7', '6', '5', '4', '3', '2')
var iban2 : IBAN = Array[Char]('D', 'E', '1', '9', '1', '2', '3', '4', '1', '2', '3', '4', '1', '2', '3', '4', '1', '2', '3', '4', '1', '2')
verify(iban1)
