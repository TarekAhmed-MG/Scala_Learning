package lectures.part1basics

object CBNvsCBV extends App {
  // calling functions by name vs calling by value

  def calledByValue(x: Long): Unit = {
    println(s"by value: $x")
    println(s"by value: $x")
  }

  def calledByName(x: => Long): Unit = {
    println(s"by name: $x")
    println(s"by name: $x")
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  /*
  The difference in these two functions is the way the parameter is evaluated:

    In the functions above System.nanoTime() is passed into both functions and each one does the same thing where they print the value of x though the behavior is different

    Outputs:

    by value: 5466865237333
    by value: 5466865237333
    by name: 5466889727791
    by name: 5466890059250

    * The first one calledByValue(x: Long) //-> this is the standard way which is seen in all programming languages when creating methods/functions
      it assigns a value to the parameter and the same value is then passed in and used like this:

      def calledByValue(x: 1234324L): Unit = {
        println(s"by value: $x") // x = 1234324L
        println(s"by value: $x") // x = 1234324L
      }

    * With the second method calledByName(x: => Long) //-> instead of evaluating in the beginning and passing through the value
    the actual evaluation happens when the parameter is accessed like this:

    def calledByName(x: => Long): Unit = {
    println(s"by name: $x") x = System.nanoTime()
    println(s"by name: $x") x = System.nanoTime()
  }

  and the evaluation happens at the point of processing.
  This method of processing is good for lazy steams and things that might fail and can be used with try types

  which is why for the outputs of the callByName function you have different numbers as with nanoTime because evaluation doesnt happen once
   */

  private def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int): Unit = println(x)

  //printFirst(infinite(), 34) -> This will crash and cause a stack overflow of course
  printFirst(34,infinite()) // -> by using "=>" in the parameter definition the function delays the evaluation till when its used
  // and we can see that y isn't actually used so it is never evaluated.

}
