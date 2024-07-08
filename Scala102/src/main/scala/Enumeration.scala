import Enumeration.Weekday
import Enumeration.Weekday.{Monday, Sunday}

object Enumeration extends App{

  /**
   * Native Enumeration
   * Problems with Enumerations
   * Sealed case objects
   */


  // Enumerations are a feature that are useful for representing finite sets of entities
  // Scala provides a native way of achieving this

  object Weekday1 extends Enumeration{
    val Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday = Value
  }

  // Enums can be serialised
  val monday: String = Weekday1.Monday.toString
  println(monday) // will give you Monday as a String

  // Enums can also  be deserialized but can lead to very nasty exceptions
  val deserialisedMonday: Weekday1.Value = Weekday1.withName("Monday")
  println(deserialisedMonday) // will give you Monday as a Weekday.Value

  // But when de-serialising you must match the text as passing "Mondai"/"monday" instead of "Monday"(Case sensitive) then you will get NoSuchElement Error

  // you can give unique values to enums

  object Weekday extends Enumeration{
    val Monday: Value = Value("Mon")
    val Tuesday: Value = Value("Tue")
    val Wednesday: Value = Value("Wed")
    val Thursday: Value = Value("Thur")
    val Friday: Value = Value("Fri")
    val Saturday: Value = Value("Sat")
    val Sunday: Value = Value("Sun")
  }

  val thur = Weekday.Thursday.toString
  println(thur)

  //All values of an Enum type can be listed using .values
  println(Weekday.values)

  // Values are inherently ordered
  println(Weekday.Monday < Weekday.Tuesday) // will return true
  println(Weekday.Sunday > Weekday.Monday) // will return true
  println(Weekday.Monday == Weekday.Monday) // Will return true
  println(Weekday.Saturday > Weekday.Sunday) // Will return false

  // this is because they are ordered and monday comes before tuesday and saturday before sunday etc..

  // you can modify the orders

  object WeekdayModifiedOrder extends Enumeration{
    val Monday: Value = Value(6)
    val Tuesday: Value = Value(5)
    val Wednesday: Value = Value(4)
    val Thursday: Value = Value(3)
    val Friday: Value = Value(2)
    val Saturday: Value = Value(1)
    val Sunday: Value = Value(0)
  }

  val modifiedOrder = WeekdayModifiedOrder.values.toList.sorted
  println(modifiedOrder)
}

/**
 * Problems with Enums
 *
 */

// Scala allows non exhaustive pattern matching where the compliler wont tell you that not all cases have been matched which can lead to issues
// for example:
def nonExhaustive(day: Weekday.Value): Unit = {
  day match{
    case Monday => println("I hate Mondays")
    case Sunday => println("The weekend is over already :(")
  }
  // it doesnt provide any cases for the rest of the week and the compiler wont show that not all values are matched

  /**
   * Enums have the same type after erasure meaning because every enum is implemented using a class that extends a sealed trait called enumeration -> this means all instances of enum
   * share the same type at runtime even though each enum has its own value and is an instance of its own type.
   *
   * Scala uses type erasure in runtime meaning that generic type information is not retained at  runtime so with enums at runtime all instances such as weekend.mon, weekend.tue end up having a type weekend.value
   * this can cause problems as if you have multiple enums the compiler will think they all have the same type
   *
   * to counter this you can name methods differently when testing or using these enums (so not do method overloading)
   * or add  type ..... = Value before you define your enums to distinguish them
   */

  



}
