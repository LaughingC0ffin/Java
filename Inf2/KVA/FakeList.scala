package ScalaGo

/**
  * Implements some List functionality explicitly via a linked list.
  *
  * @param entry entry of the list element, 'null' if not provided
  * @param next next element of the list, 'null' if not provided
  * @tparam A type of the list's entries
  */
class FakeList[A](var entry: A = null, var next: FakeList[A] = null) {


  /**
    * Tests if the FakeList contains a given element.
    *
    * Tests for equality are done via '=='.
    *
    * @param item search for this item
    * @return true if the FakeList contains the item, false if not
    */
  def contains(item: A): Boolean = {
    var p: FakeList[A] = this // Get a reference to this FakeList
    if(p==null) return false
    while(p!=null){
      if(p.entry==item)return true
      p=p.next
    }

    false
  }



  def kopie(): FakeList[A] = {
    var e = this
    if(e == null)
    return null
    else {
      if(e.next == null)
      return new FakeList(e.entry)
       else
       return new FakeList(e.entry, e.next.kopie)
       }
     }
  /**
    * Append an item to the FakeList
    *
    * @param item append this item
    */
    //VORNE ODER HINTEN
  def append(item: A): Unit = {
    if (this.entry == null) {
      this.entry = item
    }
    else if(!this.contains(item)) {
      var p: FakeList[A] = this
      while(p.next!=null){
        p=p.next
      }
      p.next=new FakeList[A](item,null)
    }
  }

  /**
    * Determines the length of the FakeList.
    *
    * @return the length of the FakeList
    */
  def length(): Int = {
    var p: FakeList[A] = this
    var tmp = 0
    if(p.next==null) return tmp
    while(p!=null){
      tmp+=1
      p=p.next
    }
    return tmp
  }

  def printlist():Unit={
    var p = this
    while(p!=null){
      println(p.entry)
      p=p.next
    }
  }
  def isempty():Boolean={
    if(this==null)return true
    else false
  }

}