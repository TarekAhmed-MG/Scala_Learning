package lectures.part3fp

import scala.util.Random

object Options extends App {
  /*
  Options:

  An Option is a wrapper for a value that might or might not be present

  sealed abstract class Option[A+]
  case class Some[A+](x: A) extends Option[A]
  case object None extends Option[Nothing]

  - Some wraps a concrete value
  - None is a singleton value for absence

  good for trying to avoid the null pointer error
   */

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // WORK with unsafe APIs
  def unsafeMethod(): String = null

  //  val result = Some(null) // WRONG as it defeats the purpose of having a Some() -> Some should always contain a value
  val result = Option(unsafeMethod()) // Some or None -> use an option instead and the option will decide if the value is a Some or None
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"

  // Options can be used when you have an unsafe method and a safe backup method like below:
  /*
    - we can give a condition where if None give the safe method
   */
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  /*
    - if you better design your API to return options then the user does not need to wrap your methods in options
      which means the methods are safer and code can be more readable
   */
  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()


  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // USAFE - DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10)) // can turn Some(value) into None if the predicate doesnt match
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions

  /*
      Exercise.
     */
  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method

  // Model Answer:

  val host = config.get("host")
  val port = config.get("port")
  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)

    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  /*
    if (c != null)
      return c.connect
    return null
   */
  val connectionStatus = connection.map(c => c.connect)
  // if (connectionStatus == null) println(None) else print (Some(connectionstatus.get))
  println(connectionStatus)
  /*
    if (status != null)
      println(status)
   */
  connectionStatus.foreach(println)

  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)
  
}
