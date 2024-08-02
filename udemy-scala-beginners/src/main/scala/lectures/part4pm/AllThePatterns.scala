package lectures.part4pm

import exercises._

object AllThePatterns{

  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ => ""
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) => ""// they can be nested for example you can match v which is inside a tuple inside a tuple
  }
  // PMs can be NESTED!

  // 4 - case classes - constructor pattern
  // PMs can be nested with CCs as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead, subtail)) => // case classes can also be matched when nested.
  }

  // 5 - list patterns - Very powerful tool
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => ""// extractor - advanced
    case List(1, _*) => ""// list of arbitrary length - advanced
    case 1 :: List(_) => ""// infix pattern
    case List(1, 2, _) :+ 42 => "lala" // infix pattern
  }

  // 6 - type specifiers - this is used to lock pattern matching to a type as it usually gets the type where all patterns fall into (eg the parent type)
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => "" // explicit type specifier
    case _ =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmptyList@Cons(_, _) => ""// name binding => use the name later(here)
    case Cons(1, rest@Cons(2, _)) => ""// name binding inside nested patterns
  }

  // 8 - multi-patterns
  val multipattern = aList match {
    case Empty | Cons(0, _) => "" // compound pattern (multi-pattern) - you can chain as many patterns as you want if you want to retun the same expression for multiple patterns
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => ""// the if guard will filter out the pattern using the predicate specialElement % 2 == 0
  }

  val anything: Any = ???
  anything match {
    case _: RuntimeException | _: NullPointerException => ""
  }

  // ALL.

  /*
    Question.
   */

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch)
  /*

  JVM trick question - it will show up as List[String] instead of List[Int] because generics was introduced in java5 so for backwards
  compatibility JVM erases all generic types after type checking which is why scala also suffers from this, since it uses the JVM

  so after type checking the pattern match looks like this:

  val numbersMatch = numbers match {
    case listOfStrings: List => "a list of strings"
    case listOfNumbers: List => "a list of numbers"
    case _ => ""
  }

  which is why you see the first output of "a list of strings" as pattern match cases go in order and cannot differentiate between the two cases.

   */


}
