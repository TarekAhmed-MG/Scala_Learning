package exercises

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean) {
  //todo: Implement a functional set

  def apply(elem: A) : Boolean = contains(elem)// as the trait returns boolean we need to modify the apply
  // so the general concept of this trait MySet is to return a boolean if myset contains an element
  def contains(elem: A) : Boolean
  def +(elem: A) : MySet[A]
  def ++(anotherSet: MySet[A]) : MySet[A]

  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B]
  def filter(predicate: A => Boolean) : MySet[A]
  def foreach(f: A => Unit) : Unit

  def -(elem: A): MySet[A] // removing an element
  def --(anotherSet: MySet[A]): MySet[A] // difference
  def &(anotherSet: MySet[A]): MySet[A] // intersection

  // EXERCISE #3 - implement a unary_! = NEGATION of a set
  // set[1,2,3] =>
  def unary_! : MySet[A]
}

class EmptySet[A] extends MySet[A]{

  def contains(elem: A): Boolean = false

  def +(elem: A): MySet[A] = new NonEmptySet[A](elem,this) // creates a new set with the element as the head and this runtime as tail

  def ++(anotherSet: MySet[A]): MySet[A] = anotherSet

  def map[B](f: A => B): MySet[B] = new EmptySet[B]

  def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]

  def filter(predicate: A => Boolean): MySet[A] = this

  def foreach(f: A => Unit): Unit = () // this is the unit () means unit value

  def -(elem: A): MySet[A] = this

  def --(anotherSet: MySet[A]): MySet[A] = this

  def &(anotherSet: MySet[A]): MySet[A]  = this

  def unary_! : MySet[A] = new PropertyBasedSet[A](_ => true)

}

class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A]{

  def contains(elem: A): Boolean = elem == head || tail.contains(elem)

  def +(elem: A): MySet[A] =
    if (this contains elem) this // return same instance if its there as set has no duplicates
    else new NonEmptySet[A](elem,this) // otherwise create a new set with the element as head

  /*
  when you want to concatenate 123 with 45
      [1 2 3] ++ [4 5] =
  the function gets the tail and concatenates it with 45
      [2 3] ++ [4 5] + 1 =
  this is a recursive function so now the tail (3) is called
      [3] ++ [4 5] + 1 + 2 =
  recursive call again will make the tail empty and ++ another set ++ head
      [] ++ [4 5] + 1 + 2 + 3
  now the concatenation for the empty set is from above which is another set and then added to the previous heads
      [4 5] + 1 + 2 + 3 = [4 5 1 2 3]
     */
  def ++(anotherSet: MySet[A]): MySet[A] =
    tail ++ anotherSet + head // recursively calling ++ method on tail and then adding head

  def map[B](f: A => B): MySet[B] = (tail map f) + f(head) // tail mapped to f and then f is applied to head and a new MySet[B] is created

  def flatMap[B](f: A => MySet[B]): MySet[B] = (tail flatMap f) ++ f(head)

  def filter(predicate: A => Boolean): MySet[A] = {
    val filteredTail = tail filter predicate
    if (predicate(head)) filteredTail + head // if predicate head returns true then return filteredtail + head
    else filteredTail // otherwise dont pass the head
  }

  def foreach(f: A => Unit): Unit ={
    f(head)
    tail foreach f
  }

  def -(elem: A): MySet[A] = if (head == elem) tail else tail - elem + head
  def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet)
  // this is the logical way to do below -> def &(anotherSet: MySet[A]): MySet[A] = filter(x => anotherSet.contains(x)
  // this is equivalent as the apply method we have does .contains automatically
  // and filter of x => anotherSet(x) can be reduced to just below. because another set is also a function
  def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)  // intersecting and filtering is the same because our set is functional

  def unary_! : MySet[A] = new PropertyBasedSet[A](x => !this.contains(x))

}

class PropertyBasedSet[A](property: A => Boolean) extends MySet[A] {
  def contains(elem: A): Boolean = property(elem)

  def +(elem: A): MySet[A] = new PropertyBasedSet[A](x => property(x) ||x== elem)

  def ++(anotherSet: MySet[A]): MySet[A] = new PropertyBasedSet[A](x => property(x) || anotherSet(x))

  def map[B](f: A => B): MySet[B] = politelyFail

  def flatMap[B](f: A => MySet[B]): MySet[B] = politelyFail

  def filter(predicate: A => Boolean): MySet[A] = new PropertyBasedSet[A](x => property(x) && predicate(x))

  def foreach(f: A => Unit): Unit = politelyFail

  def -(elem: A): MySet[A] = filter(x => x != elem)// removing an element

  def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet)// difference

  def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)// intersection

  // EXERCISE #3 - implement a unary_! = NEGATION of a set
  // set[1,2,3] =>
  def unary_! : MySet[A] = new PropertyBasedSet[A](x => !property(x))

  def politelyFail = throw new IllegalArgumentException("Really deep rabbit hole!")
}

object MySet {
  /*
    val s = MySet(1,2,3) = buildSet(seq(1,2,3), [])
    = buildSet(seq(2,3), [] + 1)
    = buildSet(seq(3), [1] + 2)
    = buildSet(seq(), [1, 2] + 3)
    = [1,2,3]
   */
  def apply[A](values: A*): MySet[A] = { // A* is a argVar that allows you to add multiple values of A
    @tailrec
    def buildSet(valSeq: Seq[A], acc: MySet[A]): MySet[A] =
      if (valSeq.isEmpty) acc
      else buildSet(valSeq.tail, acc + valSeq.head)

    buildSet(values, new EmptySet[A])
  }
}

object MySetPlayground extends App {
  val s = MySet(1, 2, 3, 4)
  s + 5 ++ MySet(-1, -2) + 3 flatMap (x => MySet(x, 10 * x)) filter (_ % 2 == 0) foreach println
}