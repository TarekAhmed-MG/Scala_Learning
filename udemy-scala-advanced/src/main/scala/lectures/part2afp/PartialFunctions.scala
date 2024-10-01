package lectures.part2afp

object PartialFunctions extends App{

  private class FunctionNotApplicableException extends RuntimeException

  val aFunction = (x:Int) => x + 1 // Function1[Int,Int] === Int => Int
  val aFussyFunction = (x: Int) => // this is a very clunky function and can be done more simply using pattern matching
    if(x == 1) 42
    else if (x == 2) 53
    else if (x == 5) 994
    else throw new FunctionNotApplicableException

  val aNicerFussyFunction = (x:Int) => x match
    case 1 => 42
    case 2 => 53
    case 5 => 994

    // this function has the domain {1,2,5} => Int and because 1,2,5 is a subset of Int this aNicerFussyFunction is called
    // a partial Function.
    // scala supports partial functions

    val aPartialFunction: PartialFunction[Int,Int] = {
      case 1 => 42
      case 2 => 53
      case 5 => 994
    } // anything between the {} is a partial function value and is equivalent to above but its a shorthand notation

    // partial functions can be used as a normal function as it has an apply method and is invoked when called
    println(aPartialFunction(2)) // will give you 53
    // but if you give a number that isnt in the case it will crash with a match error as partial functions are based of
    // pattern matching.

    // aNicerFussyFunction is a total function and cannot be applied to a partial function.

    // PF Utilities
    println(aPartialFunction.isDefinedAt(67)) // this will give you a false as there is no 67 in the match
  
}
