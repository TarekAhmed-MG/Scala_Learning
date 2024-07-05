object Options {

  /**
   * Optionals:
   * like how its used in springboot you can use optionals to return a value if its there
   * this means we can replace exceptions that can be thrown.
   * Keywords: Option[A], Some(), None
   * Some() represents a value,
   * None represents absence of Value
   */

  // create some chocolates

  trait Chocolate{
    val colour:String
    val filling:Option[String]
  }

  case class Bounty(colour:String,filling:Option[String]) extends Chocolate
  case class DairyMilk(colour:String,filling:Option[String]) extends Chocolate
  case class Caramel(colour:String,filling:Option[String]) extends Chocolate


  val bounty: Bounty = Bounty("brown",Some("coconut"))
  val whiteDiary: DairyMilk = DairyMilk("brown",None)
  val darkDairy: DairyMilk = DairyMilk("dark brown",None)
  val carameeel: Caramel = Caramel("brown",Some("caramel"))

  // Optional Matching

  def whatsInTheChocolate(chocolate: Chocolate): String = {
    val whatsInsideTheChocolate = chocolate.filling match {
      case Some("caramel") => "its Caramel"
      case Some(other) => s"No caramel, but $other"
      case None => "Its chocolate all the way down"
    }
    whatsInsideTheChocolate
  }

  // you can use .get to access the contents of an option
  val filledOption: Option[Int] = Some(2)
  val two: Int = filledOption.get // its bad practice to use .get like this as calling it on an empty option would cause problems. The code would throw a NoSuchElementException: None.get

  //the most common way to get around this is getOrElse(<default>):
  val emptyOption: Option[Int] = None
  val number:Int = emptyOption.getOrElse(2) // this will return a default value if none is present















}
