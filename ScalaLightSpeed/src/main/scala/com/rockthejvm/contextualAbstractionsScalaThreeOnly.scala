package com.rockthejvm

object contextualAbstractionsScalaThreeOnly {

  /**
  * 1 - Context Parameters/arguments
  * */

  val aList = List(2,1,3,4)
  // when there is no given instances the sorted does (ordering)
  // but when there is a given instance ->mcontextual argument: (decendingOrdering)  because we have a given now.
  val anOrderedList = aList.sorted

  // Ordering - its a two element function which tells you which element is less than another
  // so the sorted method works because scala takes a 2 element ordering function and injects it into .sorted

  // to add our own ordering method to sorted we need to create a given instance - this will replace the normal sorted functionality
  given decendingOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _) // this is the shorthand lambda function (a,b) => a > b

  // analogous to an implicit val - the sorted value takes an implicit value of Ordering B

  /**
   * Combinator
   */

  trait Combinator[A]{ // mathematically its called a monoid
    def combine(x:A, y:A) : A
  }

  def combineAll[A](list: List[A])(using combinator: Combinator[A]): A = // using makes the combinator contextual
  list.reduce(combinator.combine)

  given intCombinator: Combinator[Int] = new Combinator[Int] {
    override def combine(x: Int, y: Int) = x+y
  }

  val theSum = combineAll(aList)

  /*
  * Given Places where the combinator will look for values to combine as if there isnt anything it will not compile
  * local scope - it will look for a type in the scope the method was created
  * imported scope - from packages
  * the companion object instances involved in the call
    - will look in the companion of List and Int sicen we called lists and int
  * */

  // context bounds
  // theres a short hand way to use contextual arguments if your not using a combinator instance

  def combineAll_v2[A](list: List[A])(using Combinator[A]) = ???// not using combinator instance directly but calling other methods that use combinator you dont need to name the instance
  def combineAll_v3[A: Combinator](list: List[A]): A = ???

  // these two methods above are identical one is a shorter way

  /*
   * where context args useful
    - dependency injection
    - context-dependent functionality
    - type level programming
   */

  /**
    2 - Extension Methods
    - you can add to a method after its been defined
   */

  case class Person(name:String){
    def greet(): String = s"Hi, my name is $name, and i love scala"
  }

  extension(String:String)
    def greet(): String = new Person(String).greet()

    // were able to call .greet to danielsGreting as .greet() is apart of the string "Daniel" because of the extension method
    // this works because it looks for all extension methods added to the string type its called type enrichment.

  val danielsGreeting = "Daniel".greet() // "type enrichment"

  // combining context parameters and extension methods

  extension[A] (list:List[A])
    def combineAllValues(using combinator: Combinator[A]): A = list.reduce(combinator.combine)

  val theSum_v2 = aList.combineAllValues // now it shows up as if its always apart of the list collection.

  def main(args: Array[String]): Unit = {
    println(anOrderedList)
  }

}
