object CaseClasses extends App{

  /**
   * a case class is more focused with modelling immutable data
   * has boiler plate code for developers to use
   * better to use this instead of a normal class as allows you to create an object without the new keyword:
   *  - as case classes have an apply() method for construction and an unapply() method for extraction built in
   *  other examples such as toString, copy methods etc..
   *  as well as hashcode and equal methods to help compare data.
   */

  /**
   * The apply method is a Constructor - A constructor method takes arguments an creates an object
   * The unapply method is an Extractor - An Extractor method takes an object and tries to give back the arguments
   */

  /**
   * case classes are compared by structure and not reference - example of this was in scala101 final practical
   * when you newed up objects they had different references so changed them to a case class and it worked
   */

  case class Car(name:String,model:String,age:Int)

  val oneBmwM3 = Car("BMW", "M3", 2017)
  val makeThatTwo = Car("BMW", "M3", 2017)
  val bothTheSame = oneBmwM3 == makeThatTwo // Will return true


  /**
   * Copy Method
   * you can copy the case class and create a new instance and change what you need to like below
   * Instead of having to new up an object and rewrite all the other attributes again
   */

  case class AirCraft(name: String, inService: Boolean, noBuild:Int)

  val current = AirCraft("f-35 Lightning",true,100)
  val future  = current.copy(inService = false)
  println(current.toString)
  println(future.toString)

  /**
   * Case Objects:
   * they are like regular objects but have more features
   * they are usually used for enumeration
   */

  case object Ketchup
  case object Mustard
}
