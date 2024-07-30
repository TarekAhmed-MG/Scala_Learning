package lectures.part2oop

object Exceptions extends App{

  val x: String = null
  //  println(x.length)
  //  this ^^ will crash with a NPE

  // 1. throwing exceptions
  //  val aWeirdValue: String = throw new NullPointerException // also crashes

  // throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subtypes

  /*
    Exceptions denotes things that went wrong with the program - such as the NullPointerException
    Errors denotes things that went wrong with the system - example the stack overflow error with recursion as jvm crashes as the program exceeds the memory of the JVM Stack
   */

  // 2.how to catch exceptions

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might throw
    getInt(false)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }

  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception

  val exception = new MyException

  // Exercise:

  class OverflowException extends RuntimeException

  class UnderflowException extends RuntimeException

  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException // if you add two positives and you get a negative number it means the stack has been overflown
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException // if two negative values are added together and your result is positive then its a underflow
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException // two negatives should multiply to make a positive if it doesnt then its an overflow
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }

  }

  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))

}
