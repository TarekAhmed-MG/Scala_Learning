package lectures.part3Concurrency
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success}
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

  Thread.sleep(3000)

  case class Profile(id: String, name: String) {
    def poke(anotherProfile: Profile) =
      println(s"${this.name} poking ${anotherProfile.name}")
  }

  object SocialNetwork {
    // "database"
    val names = Map(
      "fb.id.1-zuck" -> "Mark",
      "fb.id.2-bill" -> "Bill",
      "fb.id.0-dummy" -> "Dummy"
    )
    val friends = Map(
      "fb.id.1-zuck" -> "fb.id.2-bill"
    )

    val random = new Random()

    // API
    def fetchProfile(id: String): Future[Profile] = Future {
      // fetching from the DB
      Thread.sleep(random.nextInt(300))
      Profile(id, names(id))
    }

    def fetchBestFriend(profile: Profile): Future[Profile] = Future {
      Thread.sleep(random.nextInt(400))
      val bfId = friends(profile.id)
      Profile(bfId, names(bfId))
    }
  }

  // client: mark to poke bill
  val mark = SocialNetwork.fetchProfile("fb.id.1-zuck")
  //  mark.onComplete {
  //    case Success(markProfile) => {
  //      val bill = SocialNetwork.fetchBestFriend(markProfile)
  //      bill.onComplete {
  //        case Success(billProfile) => markProfile.poke(billProfile)
  //        case Failure(e) => e.printStackTrace()
  //      }
  //    }
  //    case Failure(ex) => ex.printStackTrace()
  //  }


  // functional composition of futures
  // map, flatMap, filter
  val nameOnTheWall = mark.map(profile => profile.name)
  val marksBestFriend = mark.flatMap(profile => SocialNetwork.fetchBestFriend(profile))
  val zucksBestFriendRestricted = marksBestFriend.filter(profile => profile.name.startsWith("Z"))

  // for-comprehensions
  // This is the recommended way to do asynchronous programming.
  for { // this is equivalent to the // client: mark to poke bill - above. much cleaner and doesnt need nested futures
    mark <- SocialNetwork.fetchProfile("fb.id.1-zuck")
    bill <- SocialNetwork.fetchBestFriend(mark)
  } mark.poke(bill)

  Thread.sleep(1000)

  val aProfileNoMatterWhat = SocialNetwork.fetchProfile("unknown-id").recover {
    case e: Throwable => Profile("fb.id.0-dummy","No Such Profile")
  }

  val aFetchedProfileNoMatterWhat = SocialNetwork.fetchBestFriend("unknown-id").recoverWith{
    case e: Throwable => SocialNetwork.fetchProfile("fb.id.0-dummy") // we use this recoverWith with arguments we know
      // for sure will exist.
  }

  // this one works by taking the result of the first future if successful or else falls back to the second option
  // if that fails then the exception of the first future will be returned.
  val afallbackResult = SocialNetwork.fetchProfile("unknown-id").fallbackTo(SocialNetwork.fetchProfile("fb.id.0-dummy") )

}
