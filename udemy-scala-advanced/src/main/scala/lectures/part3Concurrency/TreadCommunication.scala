package lectures.part3Concurrency

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
  smartProdCons()
}
