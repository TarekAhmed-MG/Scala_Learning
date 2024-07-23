package lectures.part2oop

object InheritanceAndTraits extends App{
  /**
   * NOTES:
   * An auxiliary constructor in Scala is a secondary constructor that provides alternative ways
     to instantiate a class by delegating to the primary constructor with specific parameters.
   *
   */

  // single class inheritance
  sealed class Animal {
    val creatureType = "wild"

    def eat = println("yumyum")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0) // the this in this(name,0) is an auxiliary constructor used to provide the required parameters to the primary constructor
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)
  class Child(name:String, age:Int, schoolName: String) extends Person(name,age)






}
