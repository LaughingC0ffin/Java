
var perm =Array(0,3,2 ,1 ,5 ,8 ,7 ,6 ,4)

def isPerm(p : Array[Int]) : Boolean = {
    val n = p.length
    var cfm = Array.fill(n)(false)
    for(i<- 0 until n){
        cfm(p(i))=true;
    }
    //...
    return cfm.foldLeft(true)(_&_)
    }

isPerm(perm)

//b) Geben Sie die lexikographisch gr¨oßte Permutation der Zahlen 0 bis 8 an.
// 8 7 6 5 4 3 2 1 0

/*
0 1 2 3 4 5 6 7 8
0 3 5 1 8 4 7 6 2 und


0 1 2 3 4 5 6 7 8
0 3 5 1 8 6 2 4 7
 */

/*
Geben Sie zu

0 1 2 3 4 5 6 7 8
0 3 2 1 5 8 7 6 4

0 1 2 3 4 5 6 7 8
0 3 2 1 6 4 5 7 8


die lexikographisch n¨achste Permutatio
 */

/*
e) Entwickeln Sie ein Scala-Programmst¨uck, welches zu einer im Array P gegebenen Permutation im Array Q
die n¨achstgr¨oßere Permutation herstellt.
 */

def nachstePerm(array:Array[Int]):Array[Int]={

    //von rechts schauen
    //=> stelle die wir tauschen müssen

    //tauschen wir
    //rest nach größe sortieren

    def sucheIndex(a:Array[Int]):Int={
        var index= 0;
        for(i<- a.length-1 to 0 by -1){
            if(i==0)return 0;
            if(a(i)> a(i-1))return  i-1;
        }
        return index;
    }




    var index = sucheIndex(array);

    //println("array an der stelle index "+array(index))

    var bigger = array.indexOf(array.max)
    //var biggerindex=0;

    //println("bigger " + bigger)

    for(i<-index+1 until array.length){
        if( array(index)<array(i) && array(i)< array(bigger) ) bigger=i;
    }

    //println("array an der stelle bigger "+array(bigger));

    //tauschen
    val zwisch = array(index);
    array(index)=array(bigger);
    array(bigger)=zwisch;

    //von index bis lenght sortieren
    //Array.copy (Quelle, qIndex, Ziel, zIndex, anz)
    var f = new Array[Int](array.length)
    //println(f.length)

    Array.copy(array,index,f,0,array.length-index)
    println("perm dazwischen "+f.mkString(" "))
    for(i<- index until array.length){
        f(f.length-index)=array(i);
    }


    return f;
}
println("perm davor"+perm.mkString(" "))
print("perm danach"+nachstePerm(perm).mkString(" "))
//println("perm max "+ perm.max);