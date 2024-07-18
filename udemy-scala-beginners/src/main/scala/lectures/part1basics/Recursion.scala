package lectures.part1basics

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recursion extends App{

  // stack overflow happens when the recursive depth is too big.
  // Tail Recursion combats this and you put the recursive call as the last statement this makes it tail recursive.


  // STACK RECURSION

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)
      result
    }

  println(factorial(5))

  /*
  OUTPUT:

  Computing factorial of 5 - I first need factorial of 4
  Computing factorial of 4 - I first need factorial of 3
  Computing factorial of 3 - I first need factorial of 2
  Computing factorial of 2 - I first need factorial of 1
  Computed factorial of 2
  Computed factorial of 3
  Computed factorial of 4
  Computed factorial of 5
  120

  Explanation:

  when the number 5 is passed in it does not satisfy the base condition of if (n <= 1) which means the expression n * factorial(n - 1)
  would be computed -> 5 * factorial(5-1). This call is recursive so will keep going until the base case is satisfied.

  When the number is very large it may cause this function to experience an error called stack overflow which is where the stored numbers overflow the memory stack.
  This happens because when a function is called in this way the JVM has to keep note of the state of each n to be able to compute the n * part of the expression
  n * factorial(n-1) which means the stack grows as the number increases.

  The reason why we see all the computing print statements first before the computed is because when the base case is hit then unwinding process begins and Stacks have a LIFO structure

  so it looks like this for a factorial(5) ==> 5 * factorial(5-1) -> 5 * factorial( 4 * factorial ( 3 * factorial (2 * factorial(1))))

  Tail recursion counters this as the recursive function is the last operation in the call which means the JVM does not need to retain states (value of n) and can instead pass in new
  values into the function reusing the stack.

   */

  //TAIL RECURSION

  @tailrec
  def recursiveHelper(n: Int, accumulator: Int): Int = {
    if (n <= 1) accumulator
    else recursiveHelper(n - 1, n * accumulator)
  }

  // putting it into a method allows for encapsulation
  def tailRecFactorial(n: Int) :Int ={
    @tailrec
    def recursiveHelper(t: Int, accumulator: Int): Int = {
      if (n <= 1) accumulator
      else recursiveHelper(n - 1, n * accumulator)
    }
    recursiveHelper(n,1)
  }

  /*
  Explanation:

  factorial(5) here looks like this -> factorial(5,1)
                                    => factorial(4, 5 * 1)
                                    => factorial(3, 4 * 5 * 1)
                                    => factorial(2, 3 * 4 * 5 * 1)
                                    => factorial(1, 2 * 3 * 4 * 5 * 1)

  The contrast here is that the factorial function is the last operation to be called to the JVM can just pass the new n
  into the function again and replace the previous one completely meaning the stack doesnt grow as it did using stack recursion, essentially turning into an iteration.

  The benefits to this method is that there is:
    - reduced risks in stack overflows for very deep recursions
    - no unwinding after the reaching the base case
    - stack can be reused with new parameters and previous states does not need to be retained. Meaning no need to grow stack
    - Resulting in improvements in both space and time complexity ( space : Stack doesnt grow, time: no unwinding process in the end as recursive call is final operation)

   */

  // A GENERAL RULE OF THUMB IS HOWEVER MANY CALLS YOU HAVE IN THE CODE PATH IS THE AMOUNT OF ACCUMULATORS YOU NEED

  // Examples:

  /*
    1. Concatenate a string n times
    2. IsPrime function, tail recursive
    3. Fibonacci function, tail recursive
   */

  //1.

  //2.

  def fibonacci(n: Int): Int = if (n <= 2) 1 else fibonacci(n - 1) + fibonacci(n - 2)
  println(s"the 8th number in the fibonacci sequence is ${fibonacci(8)}")

  def fib(n:Int)= {
    @tailrec
    def fibHelper(t:Int, last : Int, secondToLast: Int): Int = {
      if (t >= n) last
      else  fibHelper(t + 1, last + secondToLast, last)
    }
    fibHelper(1,1,0)
  }

  println("Tail recursive: " + fib(8))



  //3.





}
