
object EnumerationSealedCaseObjects extends App{

  /**
   * Sealed Case Objects:
   * These are another way of defining finite sets and is the preferred way.
   */

  sealed trait Weekday

  case object Monday extends Weekday
  case object Tuesday extends Weekday
  case object Wednesday extends Weekday
  case object Thursday extends Weekday
  case object Friday extends Weekday
  case object Saturday extends Weekday
  case object Sunday extends Weekday

  // with this method pattern matching is exhaustive

  def test(weekday: Weekday) = {
    weekday match
      case Monday => println("I hate Mondays")
      case Sunday => println("THe weekend is over :(")
  }

  // it will tell you that it isn't exhaustive

  test(Monday)

  // we can supply extra fields instead of just an index and name

  abstract class error(val status: String, val message: String )

  case object InternalServer extends error("SERVER_ERROR","An internal server error occurred")
  case object NotFound extends error("NOT_FOUND", "Matching resource was not found")

  /**
   * Problems with sealed case objects:
   * No simple way to retrieve all Enumeration values
   * No default serialise or deserialize methods
   * No default ordering on enumeration values
   * but they can be added if they are needed
   */

//  abstract class errors(val status: String, val message: String ){
//    def compare(that:Error) = this.order - that.order
//  }
//  case object InternalServer extends Error("SERVER_ERROR", "An internal server error occurred", 0)
//  case object NotFound extends Error("NOT_FOUND", "Matching resource was not found", 1)

// then you can add the values of the order to each case object

}
