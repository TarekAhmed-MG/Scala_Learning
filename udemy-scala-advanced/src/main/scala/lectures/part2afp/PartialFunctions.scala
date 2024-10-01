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

    // PFs can be lifted to total functions that return options
    val lifted = aPartialFunction.lift // Int => Option[Int]
    println(lifted(2)) // Some(2)
    println(lifted(92)) // None
    // You can used lifted to stop your partial function from failing when it cannot match a given case

    // PFs can be chained using orElse
    val pfChain = aPartialFunction.orElse{
      case 7 => 45
    }
    println(pfChain(2)) // 53 from the previous Partial Function
    println(pfChain(7)) // 45 from the new Partial Function

    // PFs extend normal functions so they are a subtype of total functions
    val aTotalFunction: Int => Int = {
      case 1 => 10
      case 2 => 15
    }

    // HOFs accept partial functions
    val aMappedList = List(1,2,3).map{
      case 1 => 2
      case 2 => 3
      case 3 => 4
    }
    println(aMappedList)

  // PFs can only have One Parameter Type

  /*
  Exercise:
   */

  // The implementation below is a manual method in creating a partial function.

  val aManualFussyFunction = new PartialFunction[Int,Int]{
    // these two methods are the characteristic methods of a partial function and you need to define them in order,
    // to create a manual partial function.

    override def apply(x: Int): Int = x match
      case 1 => 42
      case 2 => 53
      case 5 => 994

    override def isDefinedAt(x:Int): Boolean = x == 1 || x == 2 || x == 5
  }

  val chatBot: PartialFunction[String,String] = {
    case "Hello" => "Hi, How can I help you Today"
    case "Bye" => "GoodBye"
    case "How are you today" => "Im fine thank you How are you"
    case _ => "I didnt understand that please say something else"
  }

//  scala.io.Source.stdin.getLines().foreach(line => println("chatbot: " + chatBot(line)))
  scala.io.Source.stdin.getLines().map(chatBot).foreach(println) // this is a shorter way of above


}
