package exercises

abstract class MyListBaseInt {
  /*
  head = the first element of the list
  tail = remainder of the list
  isEmpty = is this list empty
  add(int) => new list with the element added
  toString => a string representation of the list
   */

  def head: Int
  def tail: MyListBaseInt
  def isEmpty: Boolean
  def add(element: Int): MyListBaseInt
  def printElements: String
  // polymorphic call depending on if the list is empty or cons
  override def toString: String = s"[$printElements]" // print elements is used for a polymorphic approach in the empty class its "" whereas in cons it will have "..."
  // need to override toString as toString is a default method derived from AnyRef
}

// we want an empty list subclass and a non empty list subclass

object EmptyBaseIntVersion extends MyListBaseInt{ // for the empty list

  def head: Int = throw new NoSuchElementException // throws return the type Nothing
  def tail: MyListBaseInt = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): MyListBaseInt = new ConsBaseIntVersion(element,EmptyBaseIntVersion) // adds an element to the head
  def printElements: String = ""
}

// link list composed of a head int and another list as the tail
class ConsBaseIntVersion(h:Int, t:MyListBaseInt = EmptyBaseIntVersion) extends MyListBaseInt{ // for the non empty list

  def head: Int = h
  def tail: MyListBaseInt = t
  def isEmpty: Boolean = false
  def add(element: Int): MyListBaseInt = new ConsBaseIntVersion(element,this) // this method adds the new element to the head while putting the current list as the tail
  def printElements: String = if(tail.isEmpty) s"$h" else s"$h ${t.printElements}" // call printElements recursively on the tail to get all elements

}

object ListTestBase extends App {

  val list = new ConsBaseIntVersion(1,new ConsBaseIntVersion(2,new ConsBaseIntVersion(3)))
  println(list.head) // should give 1
  println(list.tail.head) // should give 2
  println(list.add(4).head) // should print 4 because adding an element adds it to the head
  println(list.toString)
  
}