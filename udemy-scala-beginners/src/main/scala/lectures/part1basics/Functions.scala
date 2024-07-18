package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App{

  def aFunction(a:String, b:Int): String = a + " " + b

  // in this language you dont loop so when you need it you use a recursion:

  // generally you dont need to specify the return type of a function but for recursive functions you need it as the compiler cant figure out the
  // return type on its own

  def aRepeatedFunction(aString:String, n:Int):String = if(n==1) aString else aString + aRepeatedFunction(aString, n-1)

  // best practice is to specify return type

  // you can use codeblocks to define auxillary functions inside it:

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b:Int) : Int = a + b

    aSmallerFunction(n,n-1) // you can use the smaller function to give you a return type of the bigger function
  }

  def factorial(n: Int): Int = if (n<=2) 1 else n * factorial(n-1)
  println(s"10 factorial is ${factorial(10)}")

  def fibonacci(n:Int): Int = if (n <= 2) 1 else fibonacci(n-1) + fibonacci(n-2)
  println(s"the 10th number in the fibonacci sequence is ${fibonacci(8)}")

  def isPrime(n:Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean = if (t <= 1) true else n % t != 0 && isPrimeUntil(t - 1)
    isPrimeUntil(n/2)
  }
  println(s"is 237 a prime number: ${isPrime(237)}")








}
