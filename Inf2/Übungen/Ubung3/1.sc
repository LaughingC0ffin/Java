type IBAN = Array[Char]


def verify(iban: IBAN): Boolean = {
  def rearrange(iban: IBAN): Array[Char] = {
    if (iban.length < 5) {
      throw new Exception("Dies ist keine IBAN ?!?!")
    }
    var rearranged = new Array[Char](iban.length)
    Array.copy(iban, 0, rearranged, rearranged.length - 4, 4)
    Array.copy(iban, 4, rearranged, 0, iban.length - 4)
    //Array.copy(Quelle,qIndex,Ziel,zIndex,anz)
    return rearranged
  }

  def convert2int(sa: Array[Char]): BigInt = {
    var lc = 0 //letter count
    for (i <- 0 until sa.length) if (sa(i).isLetter) lc += 1
    var converted = new Array[Char](sa.length + lc)
    //wahrscheinlich besser mit while schleife
    //muss bei buchstaben stelle i und i+1 besetzen
    //while(counter<converted.lenght)
    //bei buchstaben den counter um 2 erhöhen
    for (i <- 0 until sa.length) {
      if (sa(i).isDigit) converted(i) = sa(i)
      if (sa(i).isLetter) {

        converted(i)= (sa(i).getNumericValue+10).toChar}
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

      if (convert2int(rearrange(iban)) % 97 == 1) return true
      else return false
    }
    catch {
      case e: Exception => return false
    }
  }





var iban1 : IBAN = Array[Char]('G', 'B', '8', '2', 'W', 'E', 'S', 'T', '1', '2', '3', '4', '5', '6', '9', '8', '7', '6', '5', '4', '3', '2')
var iban2 : IBAN = Array[Char]('D', 'E', '1', '9', '1', '2', '3', '4', '1', '2', '3', '4', '1', '2', '3', '4', '1', '2', '3', '4', '1', '2')

verify(iban1)
verify(iban2)


