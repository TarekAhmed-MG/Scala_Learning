package com.rockthejvm

object ObjectOrientation extends App{

  /*
  * Scala is an Object oriented language so we can create classes which can be used all throughout our application
  * If you define a class just as other languages you can define members/attributes/fields and methods
  * All fields and Methods are by default public so there is no public modifier like it has in Java
  * you can still use private and protected to restrict this just as you would in Java
  * */

  // ToDo: class and instance

  class Animal{
    // define fields
    val age: Int = 0

    // defnine methods
    def eat() = println("I'm Eating")

  }

  val anAnimal = new Animal // object anAnimal - instance of a class

  // ToDo: inheritance

  /*
  * passing arguments to a class for example a dog has a name
  * the example below shows constructor argument because a class definition with arguments in this example "name" is also a constructor definition
  * meaning if you want to create a dog with a name "Pluto" it will also be the constructor argument,
  * so a class definition with arguments will also specify the constructor it will use
  *  */

  class Dog(name: String) extends Animal // constructor definition
  val aDog = new Dog("Pluto")

  /*
  * constructor arguments are not fields which means they are not visible outside the class definition so you cant do things like aDog.name
  * unless you add a val to the front of the constructor definition.
  * */

  class Fish(val name: String) extends Animal
  val aFish = new Fish("Nemo") // by adding a val to the front of name it it saves it as a member of a class turing it into a field
  println(aFish.name)

  // for a class if your not defining anything you dont need to add a code block
  class Farm
  class Cat extends Animal

  // ToDo: subtype polymorphism

  val aDeclaredAnimal: Animal = new Dog("Spot")
  aDeclaredAnimal.eat() // even though it is an Animal object we instantiated it as a Dog
  // so in compile time it only knows to call eat from Animal object but at run time it will be called from the most derived method ie if Dog class overrides the method.

  // ToDo: abstract class

  abstract class WalkingAnimal{
    val hasLegs = true
    def walk(): Unit
  }

  //ToDo: interface = ultimate abstract type meaning you can leave everything unimplemented
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait TravelType {
    def movement(animal: Animal): Unit
  }

  trait Philosopher {
    // Scala is very permissive with the method namings you can use any symbols
    // ?! are used in Akka and also the ! when you want to communicate with actors asynchronously.
    def ?!(thought: String): Unit
  }

  /*
  * In scala you can add implementations in traits
  * but usually its used to provide characteristics that we can use in concrete classes
  * */

  // Scala does offer single Class and Multi Trait inheritance similar to Java

  // ToDo: single-class inheritance, multi-trait "mixing"
  // when adding multiple traits its called mixing in scala

  class Crocodile extends Animal with Carnivore with TravelType with Philosopher {
    // you add the override method when the same class is present in a super class
    // and you use the override the method to change the implementation
    override def eat(animal: Animal): Unit = println("I am eating you, animal!")
    override def movement(animal: Animal): Unit = println("I walk")
    override def ?!(thought: String): Unit = println(s"I was thinking$thought")
  }

  // ToDo: scala method notation and method naming

  val aCroc = new Crocodile

  // Normal Notation -> similar to other languages
  aCroc.eat(aDog)

  // Infix Notation -> methods that have a single argument can be used in this way ==> object method argent
  aCroc eat aDog // ONLY AVAILABLE TO METHODS THAT HAVE ONE ARGUMENT
  aCroc ?! "What if we could fly?" // a method like this would look like an operator

  // ToDo: Operators in Scala are actually Methods

  val basicMath = 2 + 3 // the + which is an operator to add numbers is actually a method that belongs to the int type
  // this is equivalent to:
  val anotherBasicMath = 1.+(2) // this is the same as you would do object.method() so int.+(int) ==> int + int

  // so all the operators we use day to day are all methods

  // ToDo: anonymous classes

  /**
   * In statically typed programming languages abstract methods ie interfaces cannot be instantiated by themselves
   * they need a concrete class to instantiate them
   */

  val dinosaur = new Carnivore{
    override def eat(animal: Animal): Unit = println("I am a dinosaur so i can eat anything")
  }

  // as we can see here the Carnivore is a trait but we was able to instantiate it

  /*
  * So whats happening here is:
  * the compiler is creating a new class called Carnivore with the tag Anonymous followed by some numbers:

    class Carnivore_Anonymous_32122 extends Carnivore {
      override def eat(animal: Animal): Unit = println("I am a dinosaur so i can eat anything")
    }

  * the the compiler instantiates the class for you:

  val dinosaur = new Carnivore_Anonymous_32122

  * this all happens automatically when you do the above
  *  */

  // ToDo: singleton object - this is unique to scala
  object MySingleton // the only instance of the MySingleton type



}
