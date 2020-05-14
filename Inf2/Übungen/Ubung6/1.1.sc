type Bit = Boolean
val Zero = false
val One = true

//Muenzcode als Ringliste von Bits
class BitElem(var value : Bit = Zero, var next : BitElem = null)
var bit1=new BitElem(One,new BitElem(One,new BitElem(One,new BitElem(Zero,new BitElem(Zero,null)))))

bit1.next.next.next.next.next=bit1

def pruefe(b:BitElem):Unit={
    var c=b
    for(i<- 0 until 20){
        println(c.value)
        c = c.next}
}
//pruefe(bit1)

def code2String(code : BitElem) : String = {
    def bit2String(bit: Bit) = if (bit) "1" else "0"
    var out = ""
    var durch = code
    do {
        out = out + bit2String(durch.value)
        durch = durch.next
    }while (durch != code)
    return out
}
//println(code2String(bit1))




def string2Code(code : String) : BitElem = {
    def char2Bit(c : Char) : Bit = if (c == '0') Zero else One
    var out = new BitElem()
    var durch=out
    for(i<- code){
        durch.next=new BitElem(char2Bit(i))
        durch = durch.next
    }
    durch.next=out.next
    return out.next
    }
//pruefe(string2Code("0110"))


def equalsCoinCode(code1 : BitElem, code2 : BitElem) : Boolean ={
    return code2String(code1)==code2String(code2)
}

def revCoinCode(code : BitElem) : BitElem = {
    return string2Code(code2String(code).reverse)
}

//pruefe(revCoinCode(bit1))

def normCoinCode(code : BitElem) : BitElem ={
    var kleinstes = code2String(code)
    var durch = code
    do{
        if(code2String(durch)<kleinstes)kleinstes=code2String(durch)
        if(code2String(revCoinCode(string2Code(kleinstes)))<kleinstes) kleinstes=code2String(revCoinCode(string2Code(kleinstes)))
        durch=durch.next
    }while(durch!=code)
    return string2Code(kleinstes)
}
pruefe(normCoinCode(string2Code("100110")))
//100110 erg¨abe 001011
class Coin(val code : BitElem, val value : Int)

def createCoin(code : BitElem, value : Int) = new Coin(normCoinCode(code), value)

val coin10 = createCoin(string2Code("0111"), 10)
val coin20 = createCoin(string2Code("1001"), 20)
val coin30 = createCoin(string2Code("0000"), 30)

//Menge von Muenzen als einfach verkettete Liste mit Dummy

class CoinElem(val coin : Coin = null, var next : CoinElem = null)

def emptyCoinSet = new CoinElem

//Muenzen in der Form "Muenze mit Wert von 10 (0111)" ausgeben
def printCoinSet(coinSet : CoinElem) : Unit= {
    var durch = coinSet.next
    while(durch!=null){
        println("Muenzen mit Wert "+durch.coin.value +" ("+code2String(durch.coin.code)+" )")
        durch = durch.next
    }
}

//Einer Muenzmenge eine Muenze hinzufuegen
def addCoin(coinset : CoinElem, coin : Coin) : Unit ={
    if(coinset.next==null) {
        coinset.next=new CoinElem(coin,null)
    }else {
        var durch = coinset.next
        while(durch.next!=null){
            durch =durch.next
        }
        durch.next=new CoinElem(coin,null)
    }
}

//Wert anhand des gelesenen Codes einer eingeworfenen Muenze erkennen
def getValue(coinset : CoinElem, code : BitElem) : Int ={
    var durch = coinset.next
    while (durch!=null){
        if(equalsCoinCode(durch.coin.code,normCoinCode(code))) return durch.coin.value
        durch =durch.next
    }
    return -1
}

var a = new CoinElem()
addCoin(a,coin10)
addCoin(a,coin20)
addCoin(a,coin30)
printCoinSet(a)