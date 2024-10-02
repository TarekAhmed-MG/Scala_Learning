package exercises

trait MySet[A] extends (A => Boolean) {
  //todo: Implement a functional set

  def apply(elem: A) : Boolean = contains(elem)// as the trait returns boolean we need to modify the apply
  // so the general concept of this trait MySet is to return a boolean if myset contains an element
  def contains(elem: A) : Boolean
  def +(elem: A) : MySet[A]
  def ++(anotherSet: MySet[A]) : MySet[A]

  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => B): MySet[B]
  def filter(predicate: A => Boolean) : MySet[A]
  def foreach(f: A => Unit) : Unit
}

class EmptySet[A] extends MySet[A]{

  def contains(elem: A): Boolean = false

  def +(elem: A): MySet[A] = new NonEmptySet[A](elem,this) // creates a new set with the element as the head and this runtime as tail

  def ++(anotherSet: MySet[A]): MySet[A] = anotherSet

  def map[B](f: A => B): MySet[B] = new EmptySet[B]

  def flatMap[B](f: A => B): MySet[B] = new EmptySet[B]

  def filter(predicate: A => Boolean): MySet[A] = this

  def foreach(f: A => Unit): Unit = () // this is the unit () means unit value

}

class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A]{

  def contains(elem: A): Boolean = elem == head || tail.contains(elem)

  def +(elem: A): MySet[A] = 
    if (this contains elem) this // return same instance if its there as set has no duplicates
    else new NonEmptySet[A](elem,this) // otherwise create a new set with the element as head

  def ++(anotherSet: MySet[A]): MySet[A] = tail ++ anotherSet + head // recursively calling ++ method on tail and then adding head

  def map[B](f: A => B): MySet[B]

  def flatMap[B](f: A => B): MySet[B]

  def filter(predicate: A => Boolean): MySet[A]

  def foreach(f: A => Unit): Unit

}