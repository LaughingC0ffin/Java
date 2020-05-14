//Anzahl der Zeichen: 26
val cAlph = 'z' - 'a' + 1
//Alphabet als Array von Char ’a’, ..., ’z’, ’A’, ..., ’Z’
val alphabet = Array.tabulate(2 * cAlph)(i => if (i < cAlph) i + 'a' else i - cAlph + 'A').map(_.toChar)
//Zeichen als idx von Alphabet
def char2Idx(c : Char) : Int = {
    if (c >= 'a' && c <= 'z') c - 'a'
    else if (c >= 'A' && c <= 'Z') c - 'A' + cAlph
    else throw new IllegalArgumentException("'" + c + "' is invalid!")
}
def idx2Char(idx : Int) : Char = alphabet(idx)

class TrieNode(var sons : Array[TrieNode] = Array.fill(2 * cAlph)(null), var cWords : Int = 0)

def createTrie = new TrieNode

def addTrie(tr: TrieNode, str: String): Unit={
    var durch = tr
    for(buchstaben <- str){
        if(durch.sons(char2Idx(buchstaben))==null){
            durch.sons(char2Idx(buchstaben))=new TrieNode()
            durch = durch.sons(char2Idx(buchstaben))
        }
        else durch.sons(char2Idx(buchstaben))
    }
    durch.cWords+=1
}
def fillTrie(tr: TrieNode, str: String): Unit = {
    var a= str.split(" ")
    for (i<-a){
        addTrie(tr,i)
    }

}
