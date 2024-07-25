package exercises

abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B>:A](element: B): MyList[B]
  def printElements: String
  // polymorphic call depending on if the list is empty or cons
  override def toString: String = s"[$printElements]" // print elements is used for a polymorphic approach in the empty class its "" whereas in cons it will have "..."
  // need to override toString as toString is a default method derived from AnyRef

  // function signatures

  // Applies the given transformer function to each element in the list, resulting in a new list with transformed elements
  def map[B] (transformer: MyTransformer[A,B]): MyList[B]

  // Applies the given transformer function to each element in the list,
  // which returns a list for each element. These lists are then concatenated into a single list.
  def flatMap[B](transformer: MyTransformer[A,MyList[B]]): MyList[B]

  // Filters the elements of the list using the given predicate,
  // resulting in a new list that contains only the elements that satisfy the predicate.
  def filter(predicate: MyPredicate[A]): MyList[A]

}


// Nothing is the proper substitute of Any Type so

/**
 * empty should be a proper replacement of MyList of Anything
 */
object Empty extends MyList[Nothing] { // for the empty list

  def head: Nothing = throw new NoSuchElementException // throws return the type Nothing
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty) // adds an element to the head
  def printElements: String = ""


  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

}


/**
 * link list composed of a head int and another list as the tail
 * @param h Head
 * @param t Tail
 * @tparam A Generic Type
 */
class Cons[+A](h:A, t:MyList[A] = Empty) extends MyList[A]{ // for the non empty list

  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B>:A](element: B): MyList[B] = new Cons(element,this) // this method adds the new element to the head while putting the current list as the tail
  def printElements: String = if(tail.isEmpty) s"$h" else s"$h ${t.printElements}" // call printElements recursively on the tail to get all elements

  /*
    We have a head and a tail and filtering with a predicate means filtering with a head then filtering the tail ( which is a singly linked List)
    first we need to see if the head satisfies the predicate which means if predicate.test(head) passes if it does we add it to the result
    Otherwise the head does not pass and you return t.filter(predicate)

14:31
   */
  def filter(predicate: MyPredicate[A]): MyList[A] = if(predicate.test(h)) new Cons(h,t.filter(predicate)) else t.filter(predicate)

  /*

   */
  def map[B](transformer: MyTransformer[A, B]): MyList[B] = new Cons(transformer.transform(h), t.map(transformer))


}

/**
 * Predicate: A predicate is a function that takes a value and returns a boolean. It is used to test if a value satisfies a certain condition.
 *
 * Trait representing a predicate function that takes a value of type T and returns a boolean.
 * Used for testing if elements satisfy a certain condition.
 */
trait MyPredicate[-T]{
  def test(elem:T): Boolean
}

/**
 * Transformer: A transformer is a function that takes a value of one type and transforms it into a value of another type.
 *
 * Trait representing a transformer function that takes a value of type A and transforms it into a value of type B.
 * Used for transforming elements from one type to another.
 */
trait MyTransformer[-A, B]{
  def transform(elem: A) : B
}

object ListTestGenericVersion extends App{
  val listOfIntegers: MyList[Int] = new Cons(1,new Cons(2, new Cons(3)))
  val listOfStrings: MyList[String] =  new Cons("Hello", new Cons("Scala", Empty))
  
  println(listOfIntegers.toString)
  println(listOfStrings)

  println(listOfIntegers.map(new MyTransformer[Int,Int]{
    override def transform(elem: Int): Int = elem * 2})).toString // the transform method applys a transformation on list elements kind of how you can do it using x => x * 2

}









