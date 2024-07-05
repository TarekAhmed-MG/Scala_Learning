object PatternMatching extends App{

  /**
   * We are familiar with if else statements
   */

  val weatherIfElse:String = "Cold"

  if(weatherIfElse == "Cold"){
    println("Take a coat")
  }else if(weatherIfElse  == "Raining"){
    println("Take an Umbrella")
  }else if (weatherIfElse == "Hailing"){
    println("Wait at home")
  } else if (weatherIfElse == "Hurricane"){
    println("Go to the basement")
  }else if (weatherIfElse == "Apocalypse"){
    println("Yolo")
  }else{
    println("your good to go")
  }

  // as you can see theres a lot of code repetition

  /**
   * Pattern Matching
   * looks like a switch statement but is much more
   */

  val weatherPatternMatching:String = "Raining"

  weatherPatternMatching match{
    case "Cold" => println("Take a coat")
    case "Raining" => println("Take an Umbrella")
    case "Hailing" => println("Wait at home")
    case "Hurricane" => println("Go to the basement")
    case "Apocalypse" => println("Yolo")
    case _ => println("your good to go") // you put the _  to take into consideration any other cases stops you from getting errors
  }

  // both have the same outputs but you save having to do code repetition
  // you can pattern match anything in scala: objects, using traits, types.

  /**
   * Sealed Traits
   * These are similar to a normal trait but it can only be extended from within the same file it was defined in
   * this allows the compiler to emit warnings if a match/case expression is not exhaustive.
   */

  sealed trait Temperature
  case object Cold extends Temperature
  case object Hot extends Temperature

  val weather: Temperature = Cold

  weather match {
    case Cold => println("Take a coat")
    case Hot => println("Dont take a coat")
  }

  /**
   * Object Matching
   */

  abstract class Notification

  case class Email(sender:String, title: String, body:String) extends Notification
  case class SMS(caller:String, message: String) extends Notification
  case class VoiceRecording(contactName: String, link:String) extends Notification

  // when matching on objects you can extract their members and use them in return values
  // as seen in the SMS("12345", "Are you there ?") and the case SMS(number, message)

  private val notification: Notification = SMS("12345", "Are you there ?")
  notification match {
    case Email(email,title,_)  => println("Email from " + email + " With title: " + title)
    case SMS(number, message) => println("SMS from " + number + " ! Message:" + message)
    case VoiceRecording(name,link) => println("Voice Recording from " + name + " - " + link)
    case _ => "Ignored"
  }

  // you can also add extra checks called "Guards" -> its like an if block

  notification match{
    case Email(email,title,_) if title.contains("Jira")  => println("Probably a pull request")
  }

}
