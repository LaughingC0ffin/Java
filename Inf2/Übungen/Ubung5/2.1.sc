class Tree(var height : Int = 0, var depth : Int = 0, var left : Tree = null, var right : Tree = null, var iso : Tree = null)

var iso : Tree = null

def updateTree (node : Tree, depth : Int) : Unit = {
     //node auf null pruefen
    if(node == null)return
     //Tiefe von node eintragen
    node.depth=depth
     //rekursive Aufrufe
    updateTree(node.left,depth+1)
    updateTree(node.right,depth+1)
    if(node.left!=null)    node.height=node.left.height+1
    if(node.right!=null && node.right.height>node.height+1)    node.height=node.right.height+1
     //Hoehe von node berechnen und eintragen
     //node ggfs. in Isoklinenliste eintragen

    if (node.height == depth) {
        node.iso = iso
        iso = node
    }

}
