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
//Zum Hinzufugen eines neuen Wortes in einen Trie
//vielleicht auch rekursiv?
//kann sein das mir das statement nach dem if alles zerhaut
def addTrie(tr: TrieNode, str: String): Unit = {
   var index= 0
   var fcktrees = tr
   for(i<- 0 until str.length){
      index= char2Idx(str(i))
      if(fcktrees.sons(index)==null) fcktrees.sons(index)= new TrieNode()
      fcktrees = fcktrees.sons(index)
   }
fcktrees.cWords+=1
}
def fillTrie(tr: TrieNode, str: String): Unit ={
//split(" ") splitter zwischen leerzeichen
   var a = str.split(" ")
   for(i<- a ){
      addTrie(tr,i)
   }
}
def search(tr:TrieNode,key: String): Boolean =  {
var level: Int = 0
val length: Int = key.length
var index: Int = 0
var fcktrees: TrieNode = tr
level = 0
while (level < length)  {
   index = char2Idx(key(level))
if (fcktrees.sons(index) == null)return false
   fcktrees = fcktrees.sons(index)
level += 1
}
return (fcktrees != null && fcktrees.cWords>0)
}
//es fehlt noch einrücken mithilfe von pos
//und das printen der endenen wörter
def printTrie(tr:TrieNode,pos:Int):Unit={
   for(i<- 0 until tr.sons.length){
      //wenn einer true ist
      if(tr.sons(i) != null){
         //hier lönnte if mit ausgabe erstellt werden
         //vielleicht mit hifsfunktion?
         if(tr.sons(i).cWords!=0)println(":"+" "*pos +idx2Char(i) + " ("+tr.sons(i).cWords+" )")
         else println(":"+" "*pos +idx2Char(i))


         printTrie(tr.sons(i),pos+1)
      }

   }
}
//"%sThis is  string".format(" "*6)
var k = new TrieNode()
fillTrie(k,"zauber zahl zahl zahn zauber auto")
printTrie(k,0)


