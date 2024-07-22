package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    // in scala you have the freedom to name your method as you wish
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the heck?!" // you need to have a space between the ! and : otherwise compiler will think its together
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
  }

  val mary = new Person("Mary", "Inception")

  // equivalent and its called infix notation and methods with a single parameter can be called this way
  println(mary.likes("Inception"))
  println(mary likes "Inception")

  // infix notation = operator notation

  // operators in scala

  val tom = new Person("Tom", "Fight Club")
  println(mary + tom) // hangingOutWith (now changed to +) acts like an operator where it prints a string
  println(mary.+(tom)) // the two print statements are identical

  println(1 + 2)
  println(1.+(2))

  //ALL Operators are methods

  // prefix notation
  val x = -1
  val y = 1.unary_-

  // the - in -1 is a prefix notation that represents the method .unary_-

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive) // if a function does not receive any parameters then you can use it as a postfix notation
  println(mary isAlive) // discouraged as can cause ambiguity when reading code

  // apply
  println(mary.apply()) // special scala method that allows you to call an object like a method as seen below
  println(mary()) // equivalent




}
