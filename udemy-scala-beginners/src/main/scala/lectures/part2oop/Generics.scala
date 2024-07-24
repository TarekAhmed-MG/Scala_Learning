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

  // once i declare an animal list to be a covariant list of animal can i add any animal to it such as animalList.add(new Dog) ?
  // 2) No because a cat and a dog are two different animals => this is called invariance
  class InvariantList[A] // you dont add a + in front so this is the normal one you usually seen before
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // if you try to put a cat or a dog like above it will not work

  // 3) Contravariance - This method is counterintuitive and is the opposite relationship generally a no go

  class ContravariantList[-A] // this is denoted with a - operator in front of the type parameter A
  val contravariantAnimalList: ContravariantList[Cat] = new ContravariantList[Animal]








}
