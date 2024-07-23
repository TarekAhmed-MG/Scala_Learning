package lectures.part2oop

object AbstractDataTypes extends App{

  // abstract: classes where fields/methods are left unimplemented and defined by keyword abstract
  abstract class Animal {
    val creatureType: String
    val creatureName: String = "animal"
    def eat: Unit

  }

  // abstracted classes cannot be instantiated - and this is because the methods/fields are not implemented and only defined

  class Dog extends Animal{ // so you can create a concrete class and extend the abstract class and implement the methods to "instantiate" it
    override val creatureType: String = "Canine"
    def eat: Unit = println("Crunch Crunch") // for abstract members the override keyword is not necessary
  }

  // traits: the most abstract of classes in scala its similar to a interface in java: use the keyword trait
  trait Carnivoire {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded {
    val coldBlooded: Boolean
  }

  // like interfaces in java Traits can be inherited alongside classes and allows for multiple "Inheritance" which regular inheritance doesnt allow

  class Crocodile extends Animal with Carnivoire with ColdBlooded {
    val creatureType: String = "crocodile"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit = println(s" im a crocodile and im eating ${animal.creatureType} :)")
    val coldBlooded: Boolean = true
    override val creatureName: String = "croc" // you need an override tag here as the field is not abstract
  }

  /*
    Traits vs Abstract Classes:

      1- In Scala 2 traits do not have constructor parameters - trait Carnivore(someValue: String){} wouldn't work  //(but this is allowed in scala 3)
      2- traits have multiple inheritance so the same class can inherit multiple trait but like java scala only allows single class inheritance.
      3- traits = behaviour, abstract class = "thing" -> so for example abstract class animals describe animals but the traits describe what they do like carnivore or cold blooded

   */





}
