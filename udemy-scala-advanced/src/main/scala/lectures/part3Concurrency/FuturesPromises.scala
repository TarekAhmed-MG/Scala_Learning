package lectures.part3Concurrency
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
object FuturesPromises extends App {

  def calculateMeaningOfLife: Int = {
    Thread.sleep(2000)
    42
  }

  val aFuture = Future {
    calculateMeaningOfLife // calculates the meaning of life on a thread
  } // (global) can be passed into here as the future needs an execution context
  //  handles the thread allocation of futures - you can create one yourself but its in rare cases.

  println(aFuture.value)
  println(aFuture.onComplete {
    case Failure(exception) => println(s"I have failed with exception: $exception")
    case Success(meaningOfLife) => println(s"the meaning of life is $meaningOfLife")
  })

}
