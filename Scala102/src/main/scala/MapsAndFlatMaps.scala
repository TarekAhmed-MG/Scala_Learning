object MapsAndFlatMaps extends App{

  /**
   * Maps,Flatmaps,Nested Collections
   */


  val double = List(1,2,3).map(num => num * 2) // goes through each element and multiplies by 2 and gives you a new list of List(2,4,6)

  val doubleUsingSpecialCharacter =  List(1,2,3).map(_ * 2) // same as above but uses _ operator which allows you to take off num => num

  val caseMatch = List(1,2,3).map{
    case 1 => 10
    case x => x * 2
  } // you can also case match so for 1 it will give you 10 and for anything else it will double so you get a new List(10,4,6)

  // slide 60 Scala 102
  val caseMatchMaps = Map("myKey" -> "myValue").map{
    case(key,value) => println(s"key:$key,value:$value")
  } // will return an  iterable[String] = List(key:myKey,value:MyValue)

  val anotherCaseMatchMaps = Map("myKey" -> "myValue").map{case(key,value) => value -> key} // will return a Map[String,String] = Map(myValue -> myKey) - so switching keys and values

  // we can use the map function to create nested collections like -> // List(List(2,4,6),List(4,8,12),List(6,12,18)), but we might not want nested collections
  // so we can use a flapmap to transform this nested collection into a single collection

  val flatmapNestedCollection = List(1,2,3).flatMap(x => List(x*2,x*4,x*6)) // this will give you a list of List(2,4,6,4,8,12,6,12,18) instead of List(List(2,4,6),List(4,8,12),List(6,12,18))

  /**
   *  Example of case matching specific numbers in a list: Flatmaps - Options
    */

  val caseMatchingSpecificNumbers = List(1,2,3).flatMap{
    case x@(1|3) => Some(x*2)
    case _  => None
  }
  /**
   * Breakdown of above code:
   *
   * List(1,2,3) contains the initial list
   *
   * flatmap applies the flatmap function to each element of the list and an option / another collection is returned and the flatmap function will flatten the results
   *
   * A partial function is passed to the flatmap  {case x@(1|3) => Some(x*2) case _  => None}
   * A partial function is a function that is applied to some of the elements of all the possible elements in the input.
   *
   * case x@(1|3) => Some(x*2):
   * This case matches when the elements are either 1 or 3
   * The @ in x@(1|3) is a pattern matching variable alias that allows you to blind match in this case 1 or 3 to the variable x
   *
   * case x@(1|3) => Some(x*2):
   * This case matches if the element x is 1 or 3 and if it is then the expression x*2 is applied to it and a Some(val) is returned. we use Some() for safety showing a value should be present
   * and prevents issues related to missing values.
   *
   * If you want to use None to filter out unnecessary values then you have to use Some() when values are present.
   * flatmaps needs options to function as a flatmap expects the function to return a Collection or Option
   *
   * case _ => None:
   * This case matches for whatever isnt 1 or 3 and returns None
   *
   *  Then Flatmap takes the results of applying the function to each element of the list and flattens them and Some values are included in the list while the Nones are excluded
   *
   *  Final Result: List(2,6)
   *
   */

  /**
   * Maps - Eithers
   * The Map function allows you to perform actions on the values of the right side of an either
   * If the value is a Right then a function is applied to the value
   * If the value is a Left then it is returned
   */


  // create a either
  def divide(x:Int, y:Int): Either[String,Int] = {
  if (y==0){
  Left("Cannot Divide by Zero")
    }
  else {
    Right(x / y)
    }
  }

  // example vals
  val res1 = divide(8,2) // should give Right(4)
  val res2 = divide(8,0) // should give Left("Cannot Divide by Zero")

  // example usage:

  val mappedRes1 = res1.map(result => result * 2) // Should give Right(8) as we passed a right in
  val mappedRes2 = res2.map(result => result * 2) // Should give Left("Division by zero") as we passed a left in

  // Display Results
  println(s"Should give a Right() Result: $mappedRes1")
  println(s"Should give a Left() Result: $mappedRes2")

  // so the right got multiplied by 2 but the left result remained unchanged.

  /**
   * Flatmap - Eithers
   */

  val r3 = Right[String, Int](3).flatMap(x => if(x == 3) Right(x * 2)  else Left[String, Int]("false")) // right(9)
  println(r3)

  val r2 = Right[String, Int](2).flatMap(x => if(x == 3) Right(x * 2)  else Left[String, Int]("false")) // Left(false) when flatmap is called on Right(2) the condition x==3 is false so it returns Left("false")
  println(r2)

  val l1 = Left[String, Int]("Hi").flatMap(x => if(x == 3) Right(x * 2)  else Left[String, Int]("false")) // Left(hi) because the Left does not apply to the function and will directly return the left value unchanged
  println(l1)

















}
