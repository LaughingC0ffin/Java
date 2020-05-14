//Map Als Suchbaum
class MapEntry[K, V] (var key : K, var value : V, var left : MapEntry[K, V] = null, var right : MapEntry[K, V] = null)

class TreeMap[K, V] (var entries : MapEntry[K, V] = null)

//leere map erstellen
def createMap[K, V]: TreeMap[K, V] = new TreeMap[K,V]()

//Anzahl der Elemente ermitteln
def size(map: TreeMap[_, _]): Int = {
    if(map==null)return 0
    def help(mapent:MapEntry[_,_]):Int={
        if(mapent==null)return 0
        else 1+ help(mapent.left) + help(mapent.right)
    }
    return help(map.entries)
}

//Element fuer Schluessel key zurueckgeben sofern vorhanden
def get[K, V](less: (K, K) => Boolean)(m: TreeMap[K, V], key: K) : V = {
    if(m.entries==null) throw new IllegalArgumentException("1")
    var durch = m.entries
    while(durch!= null){
        if(durch.key==key)return durch.value
        else{
            if(less(key,durch.key))durch = durch.left
            else durch = durch.right
        }
    }
    throw new IllegalArgumentException("not found")
}

//Prueft, ob Schluessel key in m enthalten ist
def contains[K](less: (K, K) => Boolean)(m: TreeMap[K, _], key: K) : Boolean={
    try{
        get(less)(m,key)
        return true
    }catch{
        case _: Throwable => return false
    }
}

def printTreeMap(m: TreeMap[_, _]) : Unit = {
    def helper(mapEntry: MapEntry[_,_],string:String):Unit={
        if(mapEntry!=null){
            println(string+mapEntry.key+" -> "+mapEntry.value)
            helper(mapEntry.left,string+"_")
            helper(mapEntry.right,string+"_")
        }
    }
    helper(m.entries,"")
}



def put[K, V](less: (K, K) => Boolean)(m: TreeMap[K, V], key: K, value : V) : Unit = {
     //Rekursive Hilfsfunktion
    def p(entry : MapEntry[K, V], key : K, value : V) : MapEntry[K, V] = {
        if (entry == null) return new MapEntry[K, V](key, value)
        else if (less(key, entry.key)) {
            entry.left = p(entry.left, key, value)
            }
        else if (less(entry.key, key)) {
            entry.right = p(entry.right, key, value)
            }
        else entry.value = value

        return entry
        }
    m.entries = p(m.entries, key, value)
         }
/*
Hinweis: Es bietet sich an, vorab eine geeignete Form von put durch Curryisierung zu definieren, bei der die
Funktion less bereits ber¨ucksichtigt ist.
F¨ur Postleitzahlen l¨asst sich der Typ Int, f¨ur St¨adtenamen sinnvollerweise der Typ String verwenden.
Die n¨otige Funktion iless sowie die Hilfsfunktionen iput und iget lassen sich dann wie folgt
      definieren:

 */
def iless : (Int, Int) => Boolean = _ < _

def iput = put[Int, String](iless) _
def iget = get[Int, String](iless) _
val mc = createMap[Int, String]
iput(mc, 85521, "Ottobrunn")
iput(mc, 85579, "Neubiberg")
iput(mc, 82256, "Fuerstenfeldbruck")
iput(mc, 89073, "Ulm")
iput(mc, 99089, "Erfurt")
iput(mc, 44309, "Dortmund")
iput(mc, 20095, "Hamburg")
iput(mc, 14467, "Potsdam")
iput(mc, 24103, "Kiel")
iput(mc, 28777, "Bremen")
printTreeMap(mc)


def putAll[K, V](less: (K, K) => Boolean)(m1 : TreeMap[K, V], m2 : TreeMap[K, V]) : Unit = {
    def pa(e : MapEntry[K, V]) : Unit = {
        if (e != null) {
            put[K, V](less)(m1, e.key, e.value)
            pa(e.left)
            pa(e.right)
             }
         }
    return pa(m2.entries)
    }

def measure(action: => Unit): Unit = {
    println("Starting to measure time")
    val startTime = System.nanoTime
    action
    val endTime = System.nanoTime
    println("Operation took "+(endTime-startTime)+" ns")
     }

//Liste von Schluesseln
class KeyElem[K](var key : K, var next : KeyElem[K] = null)
class KeyList[K](var elems : KeyElem[K] = null)


//ganz wild
//funktionen aus anderen Listen implementierungen holen usw
def getKeys[K](map : TreeMap[K, _]) : KeyList[K] = {
    def gk(e : MapEntry[K, _]) : (KeyList[K], KeyElem[K]) = {
        if (e == null) return (null, null)
        else {
            val (left, leftLast) = gk(e.left)
            val (right, rightLast) = gk(e.right)

            val rtn = new KeyList[K]
            val inner = new KeyElem[K](e.key)

            if (left != null) {
                 rtn.elems = left.elems
                 leftLast.next = inner
                 }
            else rtn.elems = inner

            var last = inner
             if (right != null) {
                 last.next = right.elems
                last = rightLast
                }
            return (rtn, last)
             }
        }
    return gk(map.entries)._1
    }

