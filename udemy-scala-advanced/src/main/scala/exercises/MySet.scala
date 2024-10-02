package exercises

trait MySet[A] extends (A => Boolean) {
  //todo: Implement a functional set

  def contains(elem: A) : Boolean
  def +(elem: A) : MySet[A]
  def ++(anotherSet: MySet[A]) : MySet[A]

  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => B): MySet[B]
  def filter(predicate: A => Boolean) : MySet[A]
  def foreach(f: A => Unit) : Unit
}
