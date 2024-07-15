package lectures.part1basics

object ValuesVariablesTypes extends App{

  val x: Int = 42
  val y = 21 // compiler can infer types
  println(x)

  // Vals immutables - like con/finals

  // you can put semicolons but they are optional and isnt needed as you write expressions on different lines
  val aString: String = "Hello";

  // you need semicolons if you write expressions on the same like but you SHOULDN'T do this
  val anotherString:String = "Hi"; val someOtherString: String = "Bye" // not recommended

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = 10
  val aShort: Short = 32313 // short is an int with half the representation size so 2 bytes instead of 4.
  val aLong: Long = 1314212143231L // long is an int with double the representation size so 8 bytes and denoted with L at the end
  val aFloat: Float = 2.0f // you must put an f in the end otherwise the compiler will think its a double
  val aDouble: Double = 3.14

  // variables
  var aVariable: Int = 4
  aVariable = 10

  // variables are mutable and in functional programming they are used for side effects
  // side effects allows us to see what our programs are doing but there are dangers to side effects and need to be mindful of them
  // functional programming uses vals more
  
}
