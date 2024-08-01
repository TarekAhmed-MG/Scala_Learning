package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq - is a type of list
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7,5,6))
  println(aSequence.sorted)

  // Ranges - these are also sequences
  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  /*
  lists:
  - A LinearSeq is an immutable LinkedList
  - Head, tail, isEmpty methods are fast O(1)
  - Most other operations are O(n): for example length, reverse

  Sealed has two subtypes:
  - Object Nill(empty) - which denotes an empty list
  - class :: - and a class column called colon
   */

  val aList = List(1,2,3)
  val prepended = 42 +: aList :+ 89 // :: -> the double colon operator is used for prepending you can also use +: to prepend and :+ to append
  println(prepended)

  val apples5 = List.fill(5)("apple") // curried function that fills the list with (n)(value)
  println(apples5)
  println(aList.mkString("-|-")) // prints all the elements concatenated with -|- in between them

  /*
  arrays:
  - Can be manually constructed with predefined lengths
  - Can be mutated(Updated In Place)
  - Are Interoperable with Javas T[] arrays
  - Indexing is fast
   */

  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[String](3)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0  // syntax sugar for numbers.update(2, 0) - updates the number at index 2 with 0 list(n) = x ( updates list at index n with value x )
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers  // implicit conversion
  println(numbersSeq)

  /*
  vectors:
  - effectively constant indexed read and write O(Log32(n))
  - fast element addition: append/prepend
  - implemented as a fixed - branched trie (branch factor 32)

   */
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail - list adv
  // updating an element in the middle takes long - list disAdv
  println(getWriteTime(numbersList))
  // depth of the tree is small - vector adv
  // needs to replace an entire 32-element chunk - vector disAdv
  println(getWriteTime(numbersVector))

}
