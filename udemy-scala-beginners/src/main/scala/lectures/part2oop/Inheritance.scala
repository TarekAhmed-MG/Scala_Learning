package lectures.part2oop

object Inheritance extends App{
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

  // overriding

  class Dog extends Animal{
    override val creatureType = "domestic" // standard overriding like java when you now create an instance of Dog and call creatureType it will show the overriden version
    override def eat = println("crunch, crunch")
  }

  val dog = new Dog
  dog.eat
  println(dog.creatureType)

  // fields can also be overridden in the constructor too:

  class Mouse(override val creatureType: String) extends Animal{ // this is the same as having it inside the class as a class parameter as seen above
    override def eat = println("mnmnnmn")
  }

  val jerry = new Mouse("Brown Mouse")
  println(jerry.creatureType)

  // type substitution (Polymorphism)

  val unknownAnimal: Animal = new Mouse("Mouse")
  unknownAnimal.eat // when we call unknown animal it will know which implementation to use when calling the eat method

  /**
   * Overriding: supplying different implementation in derived classes
   * Overloading: supplying multiple methods with different signatures but with the same name in the same class
   */

  // super: accessing methods/fields in the parent class

  class Fish extends Animal {
    override def eat = {
      super.eat
      println("mmmmmmm")
    }
  }

  val nemo = new Fish
  nemo.eat // this will print both the parent eat and its own version

  /*
    PREVENTING OVERRIDES
    1 - using final on a member (method/field)
    2 - using final on the entire class: this will stop it from being inherited
    3 - seal the class using keyword sealed => This allows you to extend classes in this file alone and prevents extending in other files
        this is used when you want to be exhaustive in type hierarchy.
   */

  /*

    A type hierarchy in Scala is a structured way of organizing types, with Any at the top, encompassing all types, and Nothing at the bottom, being a subtype of every type.
    This hierarchy ensures consistent type relationships and supports features like polymorphism and pattern matching.

               +---------+
               |   Any   |
               +----+----+
                    |
           +--------+---------+
           |                  |
       +---+---+          +---+---+
       | AnyVal |          | AnyRef |
       +---+---+          +---+---+
           |                  |
   +---+---+---+   +----------+------------+
   |   |   |   |   |           |            |
  Int Double Boolean ...   String    List   CustomClasses
                           (and other user-defined classes)

   */










}
