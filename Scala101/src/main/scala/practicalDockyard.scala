object practicalDockyard extends App{

  class Boat {
   var length: Int = _ // shouldnt use vars so either create a abstract class or trait or override a val
   var width: Int = _
   var topSpeed: Int = _
  }

  class SailBoat extends Boat{
    var numSails: Int = _
    var hasOars: Boolean = _
    var canTrack: Boolean = _
  }

  class LuxurySailBoat(hasJacuzzi: Boolean, hasBooze: Boolean) extends SailBoat

  class PirateShip(numGangPlanks: Int) extends SailBoat

  class MotorBoat extends Boat{
    var engineSize: Double = _
    var fuelType: String = _
  }

  class WarShip(country: String) extends MotorBoat

  class PacerBoat(sponsor: String, quarterMileTime: Double) extends MotorBoat

  class Artillery(numGuns: Int, range: Double)

  class RenownedDesigner(name: String, location: String)

  val boat = new Boat()
  boat.topSpeed = 100
  boat.width = 10
  boat.length = 50

  val boat2 = Boat() // this is a scala 3 thing which allows you to create an object without a new keyword
  // in scala 2 you can only do this if you have a case class
  boat2.topSpeed = 300

  println(boat.topSpeed)
  println(boat2.topSpeed)

}
