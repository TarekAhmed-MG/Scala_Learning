package lectures.part3fp

object AnonymousFunctions extends App {

  // an OOP way of creating a function - replace to the anonymous function style
  val doublerOOPStyle = new Function1[Int,Int] {
    override def apply(x: Int): Int = x * 2
  }

  // anonymous function (LAMBDA)
  val doubler: Int => Int = (x: Int) => x * 2
  // val doubler: Int => Int = x => x * 2 // because you defined it on the left doubler: Int => Int you dont have to define x as Int on the right
  // val doubler = (x: Int) => x * 2 // you can also do it this way too

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b // if you have multi parameters you must put the types in brackets

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) // function itself - if you do this it will print the instance reference so must be called with ()
  println(justDoSomething()) // call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  /*
    1.  MyList: replace all FunctionX calls with lambdas
    2.  Rewrite the "special" adder as an anonymous function
   */

  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4))

}
