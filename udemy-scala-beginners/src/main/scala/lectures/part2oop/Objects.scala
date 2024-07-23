package lectures.part2oop

object Objects {
  /*

  In Scala Objects are singletons so they are a single instance so if you assign them to a variable it doesnt matter
  how many times you do it they will refer to the same instance unlike a class, when you new up an object from a class
  they will have different instances.

  Scala Objects:
  - Singleton Pattern: An object in Scala is an instance of a singleton. There can only be one instance of an object, and it is created when it is first accessed.
  - Unique Instance: Regardless of how many times you reference the object, it will always point to the same instance.
  - No Constructors: Since you don't create instances of an object using the new keyword, objects do not have constructors like classes do.
  - Use Cases: Objects are often used for utility functions, constants, or for implementing the singleton design pattern.

  Scala Classes:
  - Multiple Instances: Classes in Scala, like in other object-oriented languages, can have multiple instances. Each time you create an instance of a class using the new keyword, a new object is created in memory.
  - Constructors: Classes can have primary and auxiliary constructors to initialize instances.
  - Use Cases: Classes are used when you need multiple instances with different states and behaviors.

   */

  // Scala Objects and Classes can stay in the same scope and they can access each others private members

  object Person { // type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE
  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)

  val person1 = Person
  val person2 = Person
  println(person1 == person2)

  val bobbie = Person(mary, john) // can call a object like a function but its actually the apply method

  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit

}
