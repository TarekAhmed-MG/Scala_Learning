package lectures.part2oop

object AnonymousClasses extends App{

  abstract class Animal {
    def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahah")
  }

  /*
    you would think we instantiated an abstract class but we actually instantiated a real class
    The compiler actually took this part:

    new Animal {
    override def eat: Unit = println("hahahahah")
    }

    and created a new class and assigned it to the funnyAnimal variable this is called an anonymous class:

    compiler creates a class like so:

    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("hahahahah")
    }

    then complier instantiates it like so:

    val funnyAnimal: Animal = new AnonymousClasses$$anon$1

   */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def SayHi: Unit = println(s"Hi, my name is $name, how can i help?")
  }

  val jim = new Person ("Jim") {
     override def SayHi: Unit = println(s"Him my name is Jim, how can i be of service ?")
  }

  // anonymous classes also work with normal classes too

  // so with this you can instantiate types and override fields or methods on the spot
  // you need to pass in the required constructor arguments if needed as seen with val jim and class person
  // you need to implement all abstract fields and/ or methods.
  
  /*
    Exercises: on the MyList
    1.  Generic trait MyPredicate[-T] with a little method test(T) => Boolean
    2.  Generic trait MyTransformer[-A, B] with a method transform(A) => B
    3.  MyList:
        - map(transformer) => MyList
        - filter(predicate) => MyList
        - flatMap(transformer from A to MyList[B]) => MyList[B]

        class EvenPredicate extends MyPredicate[Int]
        class StringToIntTransformer extends MyTransformer[String, Int]

        [1,2,3].map(n * 2) = [2,4,6]
        [1,2,3,4].filter(n % 2) = [2,4]
        [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
   */

}
