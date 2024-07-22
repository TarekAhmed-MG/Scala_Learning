package lectures.part2oop

object OOBasics extends App{
  // constructor

  val person = new Person()


}

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

/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
 */



