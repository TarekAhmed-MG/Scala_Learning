package lectures.part3Concurrency

import scala.collection.mutable
import scala.util.Random

object TreadCommunication extends App {

  /*
   the producer-consumer problem
   producer -> adds a value to a container and the consumer extracts value from the computer.
    but we dont know which one will finish first

   */

  class SimpleContainer{
    private var value: Int = 0

    def isEmpty: Boolean = value == 0
    def set(newValue:Int) = value = newValue
    def get: Int = {
      val result = value
      value = 0
      result
    }
   }

  def naiveProdCons(): Unit = {
    val container = new SimpleContainer

    val consumer = new Thread(() => {
      println("[consumer] waiting... ")
      while(container.isEmpty){
        println("[consumer] actively waiting... ")
      }
      println("[consumer] I have consumed " + container.get)
    })

    val producer = new Thread(() => {
      println("[producer] computing... ")
      Thread.sleep(500)
      val value = 42
      println("[producer] I have produced, after long work, the value " + value)
      container.set(value)
    })

    consumer.start()
    producer.start()
  }

//  naiveProdCons()

  // await and notify which will make the one waiting only wait once and only execute after it's been notified to.
  def smartProdCons(): Unit = {

    val container = new SimpleContainer
    val consumer = new Thread(() => {
      println("[consumer] waiting... ")
      container.synchronized{
        container.wait() // wait for producer call
      }

      println("[consumer] I have consumed " + container.get)
    })

    val producer = new Thread(() => {

      println("[producer] hard at work... ")
      Thread.sleep(2000)
      val value = 42

      container.synchronized{
        println("[producer] I have produced, after long work, the value " + value)
        container.set(value)
        container.notify() // will notify consumer to wake up after the producer sets value
      }
    })

    consumer.start()
    producer.start()
  }
  //smartProdCons()

  /*
      producer -> [ ? ? ? ] -> consumer
     */

  def prodConsLargeBuffer(): Unit = {
    val buffer: mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 3

    val consumer = new Thread(() => {
      val random = new Random()

      while (true) {
        buffer.synchronized {
          if (buffer.isEmpty) {
            println("[consumer] buffer empty, waiting...")
            buffer.wait()
          }

          // there must be at least ONE value in the buffer
          val x = buffer.dequeue()
          println("[consumer] consumed " + x)

          // hey producer, there's empty space available, are you lazy?!
          buffer.notify()
        }

        Thread.sleep(random.nextInt(250))
      }
    })

    val producer = new Thread(() => {
      val random = new Random()
      var i = 0

      while (true) {
        buffer.synchronized {
          if (buffer.size == capacity) {
            println("[producer] buffer is full, waiting...")
            buffer.wait()
          }

          // there must be at least ONE EMPTY SPACE in the buffer
          println("[producer] producing " + i)
          buffer.enqueue(i)

          // hey consumer, new food for you!
          buffer.notify()

          i += 1
        }

        Thread.sleep(random.nextInt(500))
      }
    })

    consumer.start()
    producer.start()
  }

   prodConsLargeBuffer()

  // 2 - deadlock
  case class Friend(name: String) {
    def bow(other: Friend) = {
      this.synchronized {
        println(s"$this: I am bowing to my friend $other")
        other.rise(this)
        println(s"$this: my friend $other has risen")
      }
    }

    def rise(other: Friend) = {
      this.synchronized {
        println(s"$this: I am rising to my friend $other")
      }
    }

    var side = "right"
    def switchSide(): Unit = {
      if (side == "right") side = "left"
      else side = "right"
    }

    def pass(other: Friend): Unit = {
      while (this.side == other.side) {
        println(s"$this: Oh, but please, $other, feel free to pass...")
        switchSide()
        Thread.sleep(1000)
      }
    }
  }

  val sam = Friend("Sam")
  val pierre = Friend("Pierre")

    new Thread(() => sam.bow(pierre)).start() // sam's lock,    |  then pierre's lock
    new Thread(() => pierre.bow(sam)).start() // pierre's lock  |  then sam's lock

//   3 - livelock
    new Thread(() => sam.pass(pierre)).start() // live lock works by the fact that the threads are not blocked but they cant come out of the loop
    new Thread(() => pierre.pass(sam)).start()
}
