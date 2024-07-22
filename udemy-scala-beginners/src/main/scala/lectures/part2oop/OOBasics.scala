package lectures.part2oop

object OOBasics extends App{
  // constructor

  val person = new Person()

  val counter = new Counter(25)
  println(counter.Decrement(10).CurrentCount)

  class Person(name: String, val age: Int = 0) {
    // class parameters are not fields and if you want to convert them to fields add a val/var infront

    // body
    val x = 2

    println(1 + 3)

    // method
    def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

    // overloading
    def greet(): Unit = println(s"Hi, I am $name")

    // multiple constructors
    def this(name: String) = this(name, 0)

    def this() = this("John Doe")
  }

  /*
    Novel and a Writer

    Writer: first name, surname, year
      - method fullname

    Novel: name, year of release, author
    - authorAge
    - isWrittenBy(author)
    - copy (new year of release) = new instance of Novel
   */

  class Writer(val firstName: String, val surName: String, val year: Int) {

    def fullName(firstName: String, surname: String): String = firstName + " " + surname

  }

  class Novel(name: String, yearOfRelease: Int, author: Writer) {

    val authorAge: Int = author.year - yearOfRelease

    def isWrittenBy(author: Writer) = author == this.author

    def copy(releaseYear: Int) = new Novel(name, releaseYear, author)

  }

  /*
    Counter class
      - receives an int value
      - method current count
      - method to increment/decrement => new Counter
      - overload inc/dec to receive an amount
   */

  class Counter(val value: Int) {

    def CurrentCount: Int = this.value

    // These functions below work but do not represent immutability which is important in functional programming

    //  def Increment: Int = this.value + 1
    //  def Decrement: Int = this.value - 1
    //
    //  def Increment(value:Int): Int = value + 1
    //  def Decrement(value:Int): Int = value - 1

    // the methods below is the way you extend immutability to functions and methods
    // you return back a new counter each time to show that once defined the previous counter cannot be changed

    def Increment: Counter = new Counter(this.value + 1)

    def Decrement: Counter = new Counter(this.value - 1)

    //  def Increment(value:Int) = new Counter(this.value + value)
    //  def Decrement(value:Int) = new Counter(this.value - value)

    /*
      - Method Chaining Basics: The method returns this or a new instance of the class, allowing the next method to be called on it.
      - Benefits: It makes code more readable and concise by eliminating the need for temporary variables and multiple statements.
      - Applicability: Method chaining can be used in any object-oriented programming language that allows methods to return objects.
     */

    // the reason why you can call increment.increment(value -1) is because .increment(value -1) is applied to the result of increment
    // which is a new counter and Increment(value -1) is applied to the new instance.

    // can use a recursive function
    def Increment(value: Int): Counter = {
      if (value <= 0) this // if the value given is 0 then return the same instance
      else Increment.Increment(value - 1)
    }

    def Decrement(value: Int): Counter = {
      if (value <= 0) this // if the value given is 0 then return the same instance
      else Decrement.Decrement(value - 1) // otherwise call the decrement function and also recursively call this method
      // with decrementing the value each time till it gets to 0
    }

  }


}







