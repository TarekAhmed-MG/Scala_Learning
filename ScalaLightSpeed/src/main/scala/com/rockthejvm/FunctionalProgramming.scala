package com.rockthejvm

object FunctionalProgramming extends App{
  /*
  * Scala is Object Oriented
  * By having an apply method you can invoke objects as a function
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







}
