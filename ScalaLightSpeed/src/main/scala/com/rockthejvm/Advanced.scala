package com.rockthejvm

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global


object Advanced extends App{

  /**
  * Lazy Evaluation:
  * - A Lazy evaluation means an expression will not be evaluated until its first use
  * */

  // if we assign the number 4 to the aLazyValue variable it still wont be defined by 4 until its been used and we can see this using a side effect.
  lazy val aLazyValue = 4
  lazy val aLazyValueWithSideEffect = { // as we remember a code block is an expression where the value is the last value of the code block (return statement)
    println("I am so very Lazy")
    50
  }

  val notALazyValue = { // as we remember a code block is an expression where the value is the last value of the code block (return statement)
    println("I am not Lazy")
    50
  }

  // as we can see when you run the program for the variable aLazyValueWithSideEffect wouldn't print anything whereas the variable notALazyValue will print

  val eagerValue = aLazyValueWithSideEffect + 1
  println(eagerValue) // now when we print eagerValue we can see that it will print I am so very Lazy because its being used now so it is being evaluated
  //this is usefull in inifite collections

  /**
  * pseudo-collections:
  * - Options, Try
  * - they are useful in unsafe methods
  * */

  // can defend agaisnt nulls
  def methodWhichCanReturnNull(): String = "hello, Scala"
  // the method above can return null so to defend agaist it we need to create an if statement and write some defensive code against it
  if (methodWhichCanReturnNull() == null){
    // defensive code against null
    // option is a collection will return either a Some(value) or a singleton value called None which is the same as null but it is a value
    val anOption = Option(methodWhichCanReturnNull()) // if the method returns a value then it will return Some("Hello, Scala") as thats what its defined in the example

    // we can use option and pattern matching to carry out a function depending on the output of the option
    val stringProssessing = anOption match {
      case Some(string) => s"I have obtained a valid string: $string"
      case None => "I obtained nothing"
    }

    // map,flatMap, filter

    // try catches guard against methods that throw exceptions
    def methodWhichCanTrhowExeption(): String = throw new RuntimeException
    // a try is an exception with a value if the code went well or an exception if the code threw one and you can use pattern matching here too.
    val aTry = Try(methodWhichCanTrhowExeption()) // can wrap something that can throw exeption

    val antherStringProcessing = aTry match {
      case Success(validValue) => s"I have obtained a valid string: $validValue"
      case Failure(exception) => s"I have obtained an exception: $exception"
    }
  }

  /*
  * Evaluating something on another thread
  * (asynchronous programming)
  * */
  // a global value is the equivalent of a thread pull
  val aFuture = Future{ //future.apply() so we can omit the parenthesis of Future({...}) to Future{}
    println("loading...")
    Thread.sleep(1)
    println("I have computed a value.")
    67
  }

  // future is a collection which contains a value when its evaluated
  // but a future is composable with map, flatmap and future
  // the future, try and option types are called monads

  /**
  * Implicits basics
  * */

  //#1: implicit arguments
  def aMethodEithImplicitArgs(implicit arg: Int) = arg + 1
  implicit val myImplictInt: Int = 46
  println(aMethodEithImplicitArgs) // we dont have to pass in parameters into the method -> the method looks for an same type value to put in

  // #2: implicit conversions
  // we use to this to add methods to existing types where we dont have control over the code

  implicit class MyRichInteger(n:Int){
    def isEven() = n % 2 == 0
  }
  
  println(23.isEven()) // So this looks for the suitable wrapper where the compiler does MyRichInteger(23).isEven()
  // use this with care can cause a lot of issues
}
