//Anzahl der Zeichen: 26
val cAlph = 'z' - 'a' + 1
//Alphabet als Array von Char ’a’, ..., ’z’, ’A’, ..., ’Z’
val alphabet = Array.tabulate(2 * cAlph)(i =>
  if (i < cAlph) i + 'a'
  else i - cAlph + 'A').map(_.toChar)
//Zeichen als idx von Alphabet
def char2Idx(c : Char) : Int = {
  if (c >= 'a' && c <= 'z') c - 'a'
  else if (c >= 'A' && c <= 'Z') c - 'A' + cAlph
  else throw new IllegalArgumentException("'" + c + "' is invalid!")
}
def idx2Char(idx : Int) : Char = alphabet(idx)

class TrieNode(var sons : Array[TrieNode] = Array.fill(2 * cAlph)(null), var cWords : Int = 0)

def createTrie = new TrieNode



def addTrie(tr: TrieNode, str: String): Unit = {

}
def fillTrie(tr: TrieNode, str: String): Unit ={
  //split(" ") splitter zwischen leerzeichen

}
def search(tr:TrieNode,key: String): Boolean =  {

}

def printTrie(tr:TrieNode,pos:Int):Unit={

}
//"%sThis is  string".format(" "*6)
var k = new TrieNode()
fillTrie(k,"zauber zahl zahl zahn zauber auto")
printTrie(k,0)


