import scala.collection.immutable.ListSet
import scala.collection.mutable

object Collections extends App{

  val list = List(0,1,2,3) // its like a linkedlist
  val stream = Stream(0,1,2,3) // lazy from the tail only - this is deprecated
  val lazyList = LazyList(0,1,2,3) // completely lazy

  /**
   * Lazy Evaluation works by only computing values when they are needed this increases performance as they allow for efficient operations when using large/infinite data structues
   * Efficiency - computation when needed saves resources
   * Composibility - Complex operations
   */

  // example:

  val naturals: LazyList[Int] = LazyList.from(1) // this would create a list from 1 to infinity but it doesnt actually store till infinity
  val fistTenNumbers = naturals.take(10).toList // even though it doesnt store it we can manipulate the list as though it actually there

  //Composibility Example;

  val evenNumbers: LazyList[Int] = naturals.filter(x => x % 2 == 0) //or you can use the _ parameter like so: .filter(_ % 2 == 0) -  which is a lambda expression which automatically sets it to whatever the parameter type is

  val squaredNumbers:LazyList[Int] = evenNumbers.map(x => x * x)

  val firstTenSquares = squaredNumbers.take(10).toList.map(x => println(x.toString))
  // so the numbers actually become computer when you apply the .toList function on it as that's the first instance where the list is used as it will filter through the list.
  // so the LazyList doesnt actually hold anything inside it until the .toList is called.
  
  // queues and stacks

  val queue = mutable.Queue(1,2,3,4,5) // FIFO - first element in first element out, examples can be documents sent to a printer
  val stack = mutable.Stack(1,2,3,4,5) // LIFO - last element in first element out, examples like a web browser history
  
  // sets - contains no duplicates
  
  val set: Set[Int] = Set(1,2,2,2,2,3,4,5,5) // will return Set(1,2,3,4,5)
  println(set) // set does not preserve order - you will see an output of 5,1,2,3,4

  // listSets preserve order

  val listSet: Set[Int] = ListSet(1,2,2,2,2,3,4,5,5,5) //this works because ListSet is a specific implementation of the Trait Set otherwise you can do  val listSet: ListSet[Int] ->
  println(listSet)

  // maps

  Map(("x",24),("y",25),("z",26))
  Map("x"->19,"y"->23,"z"->12)  // these two implementations are identical one uses tuples and one uses arrows -> the arrow version is easier to read









}
