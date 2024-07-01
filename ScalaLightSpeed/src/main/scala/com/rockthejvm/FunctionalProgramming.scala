package com.rockthejvm

object FunctionalProgramming extends App{
  /*
  * Scala is Object Oriented
  * By having an apply method you can invoke objects as a function
  * functions can be declared to a val or passed to another function whereas methods are defined using def and dont get passed around
  * */

  class Person(name: String){
    def apply(age: Int) = println(s"i have aged $age years")
  }

  val bob = new Person("Bob")
  bob.apply(20)
  bob(30) // INVOKING bob as a function === bob.apply(20)

  /*
  * Scala runs on the JVM - It was built for java which is primarily OO so JVM understands objects but not functions as a first class citizen
  * we want to work with functions as first class elements of programming meaning:
   - we want to work with functions as we would with any other values:
      - compose functions
      - pass functions as arguments
      - return functions as results
  * Conclusion to this: FunctionX ==> The X denotes the number of arguments and you can pass a max of 22 so Function1,Function2, ..., Function22
  * */

  val simpleIncrementer = new Function1[Int,Int] { // This takes one argument so Function1[arg,returnType]
    override def apply(arg1: Int): Int = arg1 + 1
  }

  simpleIncrementer.apply(19)
  simpleIncrementer(19) // defined a function

  val stringConcatenator = new Function2[String,String,String] {
    override def apply(arg1: String, arg2: String): String = arg1 + arg2
  }

  print(stringConcatenator("I love", " Scala")) // will return I Love Scala

  //ToDo: Syntax Sugars

  // these are alternative syntax which can be used to replace the heavier boiler plate code like above

  /*
      new Function1[Int,Int] {
      override def apply(x: Int) = 2 * x
      }
    * */

  // this is equivalent to the example above but using (=>) we are able to replace override def apply(x: Int) = 2 * x
  val doublerFull: Function1[Int,Int] = (x: Int) => 2 * x

  // you can take this further and take out The Function1 aspect too:
  val doublerNoFunction1: Int => Int = (x: Int) => 2 * x

  // you can take this even further and take out the type all together as the compiler can infer it
  val doublerCondensed = (x: Int) => 2 * x

  // ToDo: high-order functions: take functions as args or return functions as results or both

  val aMappedList: List[Int] = List(1,2,3).map(x => x + 1) // the anonymous function x => x + 1 is passed to the method of .map so .map is a Higher Order Function (HOF)
  println(aMappedList) // aMappedList is a different List to the List(1,2,3) as Lists are immutable

  val aFlatMappedList = List(1,2,3).flatMap(x => List(x,2 * x))
  println(aFlatMappedList) // flatMap takes each element and creates a new list of (x,2*x) and then concatenates it all into 1 list

  // the alternative syntax to .flatMap(x => List(x,2 * x)) is using curly brackets:

  val aFlatMappedListAlt = List(2,3,4).flatMap{ x =>
    List(x, 2 * x)
  }

  //Filter takes a function from int to boolean and will return the values that are true
  val aFilteredList = List(1,2,3,4,5).filter(x => x<=3) // this will return from the list elements that are greater or equal to 3

  // Scala allows even shorter syntax:
  val aFilteredListShortened = List(1,2,3,4,5).filter(_<=3) // this is equivalent to .filer(x => x<=3) -> this way you dont have to repeat x twice

  // say we want to create all the pairs between the numbers 1,2,3 and the letters a,b,c so 1a,2b,3c

  /*
  * Example Below:
  * call the flatmap on the original list to return a new small list for every number
  * inside the small list and for every letter we are prepending that nunber to the letter
  * */

  val allpairs = List(1,2,3).flatMap(number => List('a','b','c').map(letter => s"$number -$letter"))
  println(allpairs) // we can iterate through collections without using for loops or any iterations just maps and filters which scala promotes

  // scala allows for human readable chains of maps as they can become quite complex in large codebases

  // ToDo: for comprehensions
  /*
  * for is a keyword in Scala and it doesnt mean for loops
  * this is useful with collections and parallel, distributed environments, spark etc..
  * */
  val aleternativePairs = for {
    number <- List(1,2,3)
    letter <- List('a','b','c')
  } yield s"$number-$letter" // with regards to the compiler this is identical to the allpairs chain

  /*
  * Collections
  * */

  // lists - lists have a head and tail and can be prepended and appended
  val aList = List(1,2,3,4,5)
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependedList = 0 :: aList // adds 0 to the front of the list
  val anExtendedList = 0 +: aList :+ 6 // adds 0 to the front of the list and 6 to the end

  // sequences
  /*
  * sequences are denoted by type Seq and it has a constructor Seq(), which has a companion object with an apply method.
  * so Seq() is actually Seq.apply() and it will return an instance deriving from the Seq trait ( it is an abstract type)
  * and the apply method will return a derived type from Sequence
  * the main benefit of Sequence is you can access an element from a given index
  * */
  val aSequence: Seq[Int] = Seq(1,2,3) // this is the same as Seq.apply(1,2,3)
  val accessedElement = aSequence(1) // this will give you the element at index 1: ==> 2

  // vectors - very fast for large data: very fast access time and has the same methods as lists and sequences
  val aVector = Vector(1,2,3,4,5)

  // sets are collections with no duplicates
  // the fundamental method of a set is to test weather a particular element is there and this is done using .contains()
  val aSet = Set(1,2,3,4,4) // this will have a Set(1,2,3,4)
  val setHas5 = aSet.contains(5) // false
  val setHas2 = aSet.contains(2)// true
  val anAddedSet = aSet + 5 // Set(1,2,3,4,5)
  val aRemovedSet = aSet - 3 //Set(1,2,4,5)

  // ranges - this is useful for "iteration" an we can use map and flatmap to work on ranges
  val aRange = 1 to 1000 // 1 to 1000 is a fictions collection that does not contain all the numbers from 1 to 1000 but it can be processed like it did
  val twoByTwo = aRange.map(x => 2 * x).toList // this will give you a list of 2 to 2000 the .toList is useful as it can be called on any other collection but you can do .toSet and so on

  // tuple = groups of values under the same value
  val aTuple = ("Bon Jovi", "Rock", 1982)

  // maps => association between keys and values, like dictionaries in python and maps in java

  val aPhonebook: Map[String, Int] = Map(
    ("Daniel", 12382),
    "Jane" -> 213123 // this is another way of doing a tuple ("Jane" -> 213123)
  )









}
