package lectures.part3fp

object WhatIsAFunction extends App{

  // DREAM: use functions as first class elements
  // problem: oop

  trait MyFunction[A, B] {
    def apply(element: A): B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1.  a function which takes 2 strings and concatenates them
    2.  transform the MyPredicate and MyTransformer into function types
    3.  define a function which takes an int and returns another function which takes an int and returns an int
        - what's the type of this function
        - how to do it
   */

  def concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }

  println(concatenator("Hello ", "Scala"))

  // Function1[Int, Function1[Int, Int]]
  val specialFunction: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] { // takes an int returns a function
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] { // takes a function inside the first function
      override def apply(y: Int): Int = x + y // implementation is add so the main function will return back x added with y
    }
  }

  val addFunction = specialFunction(3)
  println(addFunction(4))
  println(specialFunction(3)(4)) // curried function - these functions have the property that they can be called with multiple parameter list

}
