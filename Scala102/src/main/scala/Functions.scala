object Functions extends App{
  /**
   * Function
   */

  /*
   val<name of function>:(<input type>) =>  <return type> =(<name assigned to input types seperated by commas>) => {
                                            <steps/instructions>
                                            <last steps/ instructions results in a value that is of the same type ass the return type defined>
                                            }
   */

  val priceIncludingVat: BigDecimal => BigDecimal = (price) => {
    price * BigDecimal(1,2)
  }

  val priceIncludingVatNoCodeBlock: BigDecimal => BigDecimal = (price) => price * BigDecimal(1, 2)

  val priceIncludingVatCondensed = (price: BigDecimal) => price * BigDecimal(1, 2) // can do it this way too all ways work

  /**
   * Pure Functions - Best Practice
   */

  //Impure Function - is an example of what we shouldn't do

  // there are side effects to this as the second time its called you get the previous call thats why we have Allo twice

  var sentence: String = ""

  def appendImpure(word: String): String = {
    sentence = sentence + " " + word
    sentence.trim()
  }

  val a = appendImpure("Allo") // will give you "Allo"
  println(a)
  val b = appendImpure("Allo") // will give you "Allo Allo"
  println(b)

  // Pure function - what we should do
  // this is an example of referential transparency where there are no side effects and always produces the same output without causing side effects
  def appendPure(sentence: String, word: String): String = sentence + " " + word.trim()

  val c = appendPure(sentence, "Allo") // will return you "Allo"
  val d = appendPure(sentence, "Allo") // return you "Allo" instead of "Allo Allo"

  /***
   * Referential Transparency:
   * - Pure Functions
   * - Immutable Values
   */

  //Example:
  val adder: Function2[Int,Int,Int] = (x:Int,y:Int) => x + y

  val addedTwo: Int = adder(1,1)

  if(addedTwo == 2) println("If you can replace adder(1,1) with 2 and this statement still prints its a pure function")


  /** *
   * Non Referential Transparency:
   * - Impure Functions
   * - mutable Values
   */

  var increment = 0
  var incrementer = (x:Int) => increment += x
  val a1 = incrementer(1)
  val a2 = incrementer(1)

  if(increment != 1) println(s"If this statement prints after the first time running it is an impure function: $increment") else println(increment)

  /*
  * Default Parameters
  * */

  def greeting(greeting: String = "Hi", name: String = "Tarek", punctuation: String = "!"): String = {
    greeting + " " + name + punctuation
  }

  val a3 = greeting("Hi", "Tarek", "!") // "Hi Tarek!"
  val b4 = greeting() // "Hi Tarek!"
  val c5 = greeting("Hello") // "Hello Tarek!"
  val d6 = greeting("Mornin", "Angle") // "Mornin Angle!"
  val e7 = greeting("@") // "@ Tarek!"

  /*
    * Named Parameter Passing
    * */

  val f8 = greeting(punctuation = "@") // "Hi Tarek@"

  /**
   * Higher Order Functions
   * They accept a function as a parameter and/or returns a function as a result
   */

  //Normal Functions:
  // name: parameter type => return type = (parameter,..) => function
  val add: (Int,Int) => Int = (a,b) => a + b
  val subtract: (Int,Int) => Int = (a,b) => a - b
  val multiply: (Int,Int) => Int = (a,b) => a * b

  //HOF:
  def doubler(func:(Int,Int) => Int,val1:Int,val2:Int): Int = func(val1,val2) * 2

  /**
   * Breakdown:
   * def declares a function.
   * calculate is the name of the function.
   * func: (Int, Int) => Int - is a parameter named func, which is a function that takes two Int arguments and returns an Int.
   * val1: Int - is the first integer parameter.
   * val2: Int - is the second integer parameter.
   * : Int - specifies indicates calculate function returns an Int.
   */

  // we can now do this:

  val addThenDouble = doubler(add,1,2)
  val subtractThenDouble = doubler(subtract,1,2)
  val multiplyThenDouble = doubler(multiply,1,2)

  // this takes in a function and applies another function to it

  println(addThenDouble)
  println(subtractThenDouble)
  println(multiplyThenDouble)

  // map, flatmap etc are all examples of higher order functions

  // with anonymous functions if you set the function into a val it will become anonymous whereas if you use def and define a function it isn't one.
  // so if you define a function inline and assign it to a val or pass it as an argument directly then it is an anonymous function.

  //Examples:

  val inlineDivide: (Int,Int) => Int = (a,b) => a/b // we create a function inline
  val passDirectlyDivide = doubler((a,b)=> a/b ,4 ,2) // we pass the function into another one directly
  
}
