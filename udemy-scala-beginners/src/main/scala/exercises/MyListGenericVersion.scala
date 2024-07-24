package exercises

abstract class MyListGenericVersion[+A] {

  def head: A
  def tail: MyListGenericVersion[A]
  def isEmpty: Boolean
  def add[B>:A](element: B): MyListGenericVersion[B]
  def printElements: String
  // polymorphic call depending on if the list is empty or cons
  override def toString: String = s"[$printElements]" // print elements is used for a polymorphic approach in the empty class its "" whereas in cons it will have "..."
  // need to override toString as toString is a default method derived from AnyRef
}

// empty should be a proper replacement of MyList of Anything
// Nothing is the proper substitute of Any Type so
object EmptyGenericVersion extends MyListGenericVersion[Nothing] { // for the empty list

  def head: Nothing = throw new NoSuchElementException // throws return the type Nothing
  def tail: MyListGenericVersion[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyListGenericVersion[B] = new ConsGenericVersion(element, EmptyGenericVersion) // adds an element to the head
  def printElements: String = ""
}

// link list composed of a head int and another list as the tail
class ConsGenericVersion[+A](h:A, t:MyListGenericVersion[A] = EmptyGenericVersion) extends MyListGenericVersion[A]{ // for the non empty list

  def head: A = h
  def tail: MyListGenericVersion[A] = t
  def isEmpty: Boolean = false
  def add[B>:A](element: B): MyListGenericVersion[B] = new ConsGenericVersion(element,this) // this method adds the new element to the head while putting the current list as the tail
  def printElements: String = if(tail.isEmpty) s"$h" else s"$h ${t.printElements}" // call printElements recursively on the tail to get all elements
}

object ListTestGenericVersion extends App{
  val listOfIntegers: MyListGenericVersion[Int] = new ConsGenericVersion(1,new ConsGenericVersion(2, new ConsGenericVersion(3)))
  val listOfStrings: MyListGenericVersion[String] =  new ConsGenericVersion("Hello", new ConsGenericVersion("Scala", EmptyGenericVersion))
  
  println(listOfIntegers.toString)
  println(listOfStrings)
}









