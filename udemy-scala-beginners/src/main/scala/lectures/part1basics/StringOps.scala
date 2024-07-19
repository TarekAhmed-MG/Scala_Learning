package lectures.part1basics

object StringOps extends App{

  val str: String = "Hello, i am learning Scala"

  println(str.charAt(2)) // returns character at index 2
  println(str.substring(7,11)) // returns part of string so in this case "i am"
  println(str.split(" ").toList) // this gives you a list with all the words split up by a delimiter where in this case is a "space"
  println(str.replace(" ","-")) // this will replace whatever you give in the first argument with whatever is in the second argument
  println(str.toLowerCase) // makes everything lowercase
  println(str.toUpperCase) // makes it upper case
  println(str.length) // gives you the length of the string


  val aNumberString = "2"
  val aNumber = aNumberString.toInt // this turns the string 45 into an int
  println('a' +: aNumberString :+ 'z') // :+ and +: are the prepending and appending operator you will get a new string "a2z"
  println(str.reverse) // reverses the string
  println(str.take(2)) // retrieves the first specified 0-n chars at those indexes in this case 2


  // scala specific: String Interpolators

  // S Interpolators

  val name = "David"
  val age = 12

  val greeting = s"Hello, my name is $name and i am $age years old"
  val anotherGreeting = s"Hello, my name is $name and i am ${age + 1} years old"

  println(greeting)
  println(anotherGreeting)

  /*
   * F Interpolators
   * f" => interpolated formatted string
   * $ => we will expand the value
   * after the dollar sign you give the value name
   * %2.2f => float number format hence the "f" at the end, 2 means two characters total and .2 means 2 decimals precision
   *
   */
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"

  // they can check for type correctness if the types dont match you will get an error

  // raw-interpolator
  println(raw"this is a \n newline") // this is similar to S interpolation but will print strings literraly so \n will not be escaped
  val injectedString = "this is a \n newline"
  println(raw"$injectedString") // this will escape the \n it ignores escaped characters in the raw string






}
