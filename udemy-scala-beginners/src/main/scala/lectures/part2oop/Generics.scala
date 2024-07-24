package lectures.part2oop

object Generics extends App {

  // you can create a collection that allows you to pass in whatever primitive type you need for example for a list you can use the generic type
  class MyList[A] {
    // use type A here - you can call A whatever you like its just a placeholder name
  }
  // with the MyList exersise example you can only use Integers and if you wanted to use Strings you would have to recopy the entire code
  // here you can use any as seen below

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]


  class MyMap[key,value] // can add multiple type parameters

  trait myTrait[T] // you can do it with traits too

  // generic methods
  object MyList { // a companion object as its an object that has the same name as the class but objects cannot be parametrized
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  /*
    VARIANCE PROBLEM
   */

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  /*
   Question: if a Cat extends animal does a list of cat also extend animal ?
   - there are 3 answers to this question:
   */

  // 1) Yes List[Cat] extends list[Animal] => this is called covariance

  class CovariantList[+A] // you denote this using the + operator infront of the parameter A

  // you would use this in the same way you would do polymorphic substitution like so:
  val animal:Animal = new Cat()
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // once i declare an animal list of for example cat to be a covariant list of animal can i add any animal to it such as animalList.add(new Dog) ?
  // if you do that then you will not have a list of cat anymore but a new list of Animals and it will be more generic explanation below at MyNewList

  // 2) No, this is called invariance
  class InvariantList[A] // you dont add a + in front so this is the normal one you usually seen before
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // if you try to put a cat or a dog like above it will not work

  // 3) Contravariance - This method is counterintuitive and is the opposite relationship generally a no go

  class ContravariantList[-A] // this is denoted with a - operator in front of the type parameter A

  // this gives you a total opposite type relation replacing a list of cats with a list of animals
  val contravariantAnimalList: ContravariantList[Cat] = new ContravariantList[Animal]

  /*
    This method doesnt make much sense because a Cat is a type of animal so in the case of Covariance you can replace a list of
    animals with a list of cats as when you look at it in the point of Type Hierarchy a Cat is derived from an Animal,
    but with Contravariance your replacing a list of cats with a list of animals so you are going from a derived type to a parent type
    but not all animals are cats. so Covariance doesnt work that well with lists but there is a use case.

   */

  // say we have an animal trainer well in this case we can use a trainer of Animal who can train cats,dogs,birds to also be a trainer of Cat
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types

  //Bounded types allow you to use your generic classes only for certain types that are either a subclass of a different type
  // or a super class of a different type

  class Cage[A <: Animal](animal: A) // here you are saying class parameter A only accepts subtypes of Animal
  val cage = new Cage(new Dog) // this is acceptable because Dog is a type animal

  class Car
  //val newCage = new Cage(new Car) => this will not work because a Car is not type Animal

  // <: is an upper bounded type -> only accepts subtypes of a class
  // >: lower bounded type  -> only accepts supertype of a class

  // bounded types solve the variance problem:

  class MyNewList[+A] {
    // def add(element:A): MyList[A] = ??? this wouldn't work even though the list is generic and covariant
    // and its because if you try to add a different subtype to this list for example a Dog to a list of Cats then you will get back a new list of Animal
    // so you have to define it like this:
    def add[B>: A](element: B): MyList[B] = ???
    // this says if in a list of A you put in a element of B which is a supertype of A then this list will turn into a List of B and not A
    /*
    example:
    A = Cat
    B = Animal
    if you now pass in a dog to a list of cat then it will turn into a list of animal
     */
  }







}
