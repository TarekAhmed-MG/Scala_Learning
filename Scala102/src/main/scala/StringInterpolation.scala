object StringInterpolation extends App{


  /**
   * String Interpolation
   */

  val name: String = "Billy"
  println("My name is " + name) // -> My name is Billy

  /*
    S Interpolator
  */

  println(s"My name is $name") // -> My name is Billy -> can put values directly into a string
  val number:Int=10
  print(s"4 * $number = ${4 * number}") // ${} block expressions can also be used

  /*
    F Interpolator
  */

  val pi: Double = 3.1415926
  println(f"Pi  to 2 decimal plces is $pi%.2f") //.%2f gives you 2dp
  println(f"Pi  to 4 decimal plces is $pi%.4f") // .%4f gives you 4dp

  // the f interpolator is typesafe which means if the variable reference and the format string doesnt match it will throw an error
  /*
   * common format strings:
   * %f
   * %d
   * %i
   */

  /*
    Raw Interpolator
   */

  println(s"Im going to go to a \nnew line")
  println(raw"Im going to go on a \nnew line") // similar to s but it doesnt escape literals in the string






}
