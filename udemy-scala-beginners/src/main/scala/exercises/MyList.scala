package exercises

abstract class MyList {
  /*
  head = the first element of the list
  tail = remainder of the list
  isEmpty = is this list empty
  add(int) => new list with the element added
  toString => a string representation of the list
   */

  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList
  def printElements: String
  // polymorphic call depending on if the list is empty or cons
  override def toString: String = s"[$printElements]" // print elements is used for a polymorphic approach in the empty class its "" whereas in cons it will have "..."
  // need to override toString as toString is a default method derived from AnyRef
}

// we want an empty list subclass and a non empty list subclass

object Empty extends MyList{ // for the empty list

  def head: Int = throw new NoSuchElementException // throws return the type Nothing
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): MyList = new Cons(element,Empty) // adds an element to the head
  def printElements: String = ""
}

// link list composed of a head int and another list as the tail
class Cons(h:Int, t:MyList = Empty) extends MyList{ // for the non empty list

  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(element: Int): MyList = new Cons(element,this) // this method adds the new element to the head while putting the current list as the tail
  def printElements: String = if(tail.isEmpty) s"$h" else s"$h ${t.printElements}" // call printElements recursively on the tail to get all elements

}

object ListTest extends App {

  val list = new Cons(1,new Cons(2,new Cons(3)))
  println(list.head) // should give 1
  println(list.tail.head) // should give 2
  println(list.add(4).head) // should print 4 because adding an element adds it to the head
  println(list.toString)
  
}