package exercises

abstract class MyListOOP[+A] {

  def head: A
  def tail: MyListOOP[A]
  def isEmpty: Boolean
  def add[B>:A](element: B): MyListOOP[B]
  def printElements: String
  // polymorphic call depending on if the list is empty or cons
  override def toString: String = s"[$printElements]" // print elements is used for a polymorphic approach in the empty class its "" whereas in cons it will have "..."
  // need to override toString as toString is a default method derived from AnyRef

  // function signatures

  // Applies the given transformer function to each element in the list, resulting in a new list with transformed elements
  def map[B] (transformer: MyTransformerOOP[A,B]): MyListOOP[B]

  // Applies the given transformer function to each element in the list,
  // which returns a list for each element. These lists are then concatenated into a single list.
  def flatMap[B](transformer: MyTransformerOOP[A,MyListOOP[B]]): MyListOOP[B] // for a flatmap we need a concatenation method
  def ++[B>: A](list: MyListOOP[B]): MyListOOP[B] // concatenation


  // Filters the elements of the list using the given predicate,
  // resulting in a new list that contains only the elements that satisfy the predicate.
  def filter(predicate: MyPredicateOOP[A]): MyListOOP[A]

}


// Nothing is the proper substitute of Any Type so

/**
 * empty should be a proper replacement of MyList of Anything
 */
case object EmptyOOP extends MyListOOP[Nothing] { // for the empty list

  def head: Nothing = throw new NoSuchElementException // throws return the type Nothing
  def tail: MyListOOP[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyListOOP[B] = new ConsOOP(element, EmptyOOP) // adds an element to the head
  def printElements: String = ""


  def map[B](transformer: MyTransformerOOP[Nothing, B]): MyListOOP[B] = EmptyOOP
  def filter(predicate: MyPredicateOOP[Nothing]): MyListOOP[Nothing] = EmptyOOP

  def flatMap[B](transformer: MyTransformerOOP[Nothing, MyListOOP[B]]): MyListOOP[B] = EmptyOOP
  def ++[B>: Nothing](list: MyListOOP[B]): MyListOOP[B] = list // anything concatenated to nothing will return that list

}


/**
 * link list composed of a head int and another list as the tail
 * @param h Head
 * @param t Tail
 * @tparam A Generic Type
 */
case class ConsOOP[+A](h:A, t:MyListOOP[A] = EmptyOOP) extends MyListOOP[A]{ // for the non empty list

  def head: A = h
  def tail: MyListOOP[A] = t
  def isEmpty: Boolean = false
  def add[B>:A](element: B): MyListOOP[B] = new ConsOOP(element,this) // this method adds the new element to the head while putting the current list as the tail
  def printElements: String = if(tail.isEmpty) s"$h" else s"$h ${t.printElements}" // call printElements recursively on the tail to get all elements

  /*
    We have a head and a tail and filtering with a predicate means filtering with a head then filtering the tail ( which is a singly linked List)
    first we need to see if the head satisfies the predicate which means if predicate.test(head) passes if it does we add it to the result
    Otherwise the head does not pass and you return t.filter(predicate)

   */
  /*
    [1,2,3].filter(n % 2 == 0) ==> when passing this to the predicate the head will fail as 1%2 will not be 0 so the filter is then applied to the tail
      [2,3].filter(n % 2 == 0) ==> this time predicate is applied to the head of this list 2 and it passes so its added like below:
      = new Cons(2, [3].filter(n % 2 == 0)) ==> now 3 is checked and returns false
      = new Cons(2, Empty.filter(n % 2 == 0)) // we now have an empty
      = new Cons(2, Empty) ==> so just 2 is returned
   */
  def filter(predicate: MyPredicateOOP[A]): MyListOOP[A] = if(predicate.test(h)) new ConsOOP(h,t.filter(predicate)) else t.filter(predicate)

  /*
    [1,2,3].map(n * 2) ==> the function applies a transformation for example double each element
      = new Cons(2, [2,3].map(n * 2)) ==> which does this -> creates a new Cons with the transformation applied to the head and then to the tail which is shown in the step below
      = new Cons(2, new Cons(4, [3].map(n * 2))) ==> the compiler creates a new cons with the new "head" being multiplied by two as the head and so on...
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2)))) ==> this continues to do the same as above and passes empty if there is no further numbers
      = new Cons(2, new Cons(4, new Cons(6, Empty))))

   */
  def map[B](transformer: MyTransformerOOP[A, B]): MyListOOP[B] = new ConsOOP(transformer.transform(h), t.map(transformer))

  /*
      1,2] ++ [3,4,5]
      = new Cons(1, [2] ++ [3,4,5])
      = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
      = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
   */
  def ++[B>: A](list: MyListOOP[B]): MyListOOP[B] = new ConsOOP(h,t ++ list)

  /*
      [1,2].flatMap(n => [n, n+1]) => if i have a list 1,2 and then gets a flatmap of n and n+1
      = [1,2] ++ [2].flatMap(n => [n, n+1]) // then i transformer.transform(h = 1) of list 1,2 and concatenated with tail [2].flatmap with the same transformer
      = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1]) // which is again 1,2 ++ 2,3 ++ empty
      = [1,2] ++ [2,3] ++ Empty
      = [1,2,2,3]
  */
  def flatMap[B](transformer: MyTransformerOOP[A,MyListOOP[B]]): MyListOOP[B] = transformer.transform(h) ++ t.flatMap(transformer)

}

/**
 * Predicate: A predicate is a function that takes a value and returns a boolean. It is used to test if a value satisfies a certain condition.
 *
 * Trait representing a predicate function that takes a value of type T and returns a boolean.
 * Used for testing if elements satisfy a certain condition.
 */
trait MyPredicateOOP[-T]{
  def test(elem:T): Boolean
}

/**
 * Transformer: A transformer is a function that takes a value of one type and transforms it into a value of another type.
 *
 * Trait representing a transformer function that takes a value of type A and transforms it into a value of type B.
 * Used for transforming elements from one type to another.
 */
trait MyTransformerOOP[-A, B]{
  def transform(elem: A) : B
}

object ListTestOOP extends App{
  val listOfIntegers: MyListOOP[Int] = new ConsOOP(1,new ConsOOP(2, new ConsOOP(3)))
  val cloneListOfIntegers: MyListOOP[Int] = new ConsOOP(1,new ConsOOP(2, new ConsOOP(3)))
  val listOfStrings: MyListOOP[String] =  new ConsOOP("Hello", new ConsOOP("Scala", EmptyOOP))



  println(listOfIntegers.toString)
  println(listOfStrings)
  println(listOfIntegers == cloneListOfIntegers) // will return true as its a case class otherwise would need to recursively compare each element

  println(listOfIntegers.map(new MyTransformerOOP[Int,Int]{
    override def transform(elem: Int): Int = elem * 2})).toString // the transform method apply a transformation on list elements kind of how you can do it using x => x * 2

  val anotherListOfIntegers: MyListOOP[Int] = new ConsOOP(4, new ConsOOP(5, EmptyOOP))

  println(listOfIntegers.map(_ * 2).toString)

  println(listOfIntegers.filter(_ % 2 == 0).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new MyTransformerOOP[Int,MyListOOP[Int]]{
    override def transform(elem: Int): MyListOOP[Int] = new ConsOOP(elem, new ConsOOP(elem+1,EmptyOOP))
  }).toString)

  // this is a complete covariant generic list

}
