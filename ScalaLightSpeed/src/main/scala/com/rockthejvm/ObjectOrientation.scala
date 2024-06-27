package com.rockthejvm

object ObjectOrientation extends App{

  // when you extend App it uses the App classes main method so its the equivalent of turning it into -->
  // public static void main(String[] args){} --> this is why it is runnable

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
  // when you define the object as MySingleton you define the type as Singleton and then single instance as well

  object MySingleton{
    val mySpecialValue = 1024
    def mySpecialMethod(): Int = 2048

    // the method apply is a special method that can take in any arguments and can be present in any class or object
    def apply(x: Int): Int = x + 1

  }

  /*
  *  Since the singleton can be initialized once ->
  * you dont need to use the new operator to instantiate it as you would a normal object/class to use it.
  * you would call these methods as you would a static method in java.
  * Singletons are usually used for global states, utility objects or companion objects.
  * example below:
  *  */

  MySingleton.mySpecialMethod()


  MySingleton.apply(65)
  MySingleton(65)

  // the presense of an apply method in a class allows intances of that class to be called in this way:
  MySingleton.apply(19)
  MySingleton(19) // both ways are equivalent

  // when you call the object like this MySingleton(19) the compiler automatically interprets this as a call to the apply method
  // this is used in functional programming, the apply method allows instances of a class to be invoked like functions


  // ToDo: companion objects
  /*
  * this is where the object/trait has the same name as the class
  * companions can access each others private fields/methods but the instances of animals are different to the singleton animal
  * you would use the companion object (singleton) to access things that do not depend on the companion class.
    - it allows for encapsulation: where you can keep related code together where instance-specific data can be shared as methods and utilities in the companion object
    - can place all the factory methods in there for cleaner and more readable code
    - allows for shared functionality by holding methods and values that should be shared across all instances - making them act like static methods for example in java
  *  */

  object Animal {
    val canLiveIndefiitley = false
    def isNocturnal(): Boolean = false
  }

  // can access variables the same way you would with static methods in Java.
  val animalsCanLiveForever = Animal.canLiveIndefiitley
  val animalsIsNocturnal = Animal.isNocturnal()

  // ToDo: Case Classes

  /*
  * Case classes are lightweight data structures with some boilerplate
  - sensible equals and hash code
  - sensivle and quick serialization
  - companion with apply
  - pattern matching (will discuss later)
  * */

  case class Person(name: String, age: Int)

  // usualy you would need to instatinate the class with new like below
  val tarek = Person("Tarek", 26)

  // but with case classes you can omit the new
  // because each case class has an apply and you can instantiate the class using the apply method

  val michael = Person("Michael", 27) // this is equivalent to Person.apply("Michael",27)

  // ToDo: exceptions

  // this is the normal structured try catch you would find in for example java ( the java version of catch being catch(Exception e) {....})
  try{
    // can write code  that can thow
    val x: String = null
    println(x.length)
  } catch{
    case e : Exception => "Faulty error message"
  } finally {
    // finally code execute code no matter what
    // its useful for closing connections releasing files / resources
  }

  // ToDo: Generics

  abstract class MyList[T] { //my list is applicable for any type denoted T (this also works for classes and traits)
    def head: T // which will return the element of type T
    def tail: MyList[T] // the tail will return the list of type T
  }
  // insde the class you can have defenitions that depend on the type T so you can use it as defenitions so when you use it later the T becomes concrete

  // using generics with a concrete type
  val aList: List[Int] = List(1,2,3) // List.apply(1,2,3)
  val first = aList.head // this now becomes concrete as the T becomes an Int as we defined aList as a List[Int] type
  val rest = aList.tail
  val aStringList = List("hello","Scala")
  val firstString = aStringList.head // will return String hello

  //TODO: IMPORTANT INFORMATION

  /*
  * TODO: Point #1: In Scala we usually operate with IMMUTABLE values/objects
  * Any Modifications to an object MUST return ANOTHER object
  * so in the example below it would give you a new object so you dont mutate or change the values of the original object
    - Benefits:
      1) excellent for multithreading/distributed environments -why?
      2) helps makes sense of the code("reasoning about")
  * */

  /*
  * TODO: Point #2: Scala is the closes to the Object Oriented Ideal even though it can be used for both OOP and Functional
  * it is a true OO language
  * */
  val reversedList = aList.reverse // returns a NEW list

  // TODO: Look into this a bit more its such as use cases to get a better understanding of the whole picture
      // my main question is that in scala do we need to create the list class or can we import collections like java
      // question when you print first you get 1 but when you print rest you get List(2,3)

}
