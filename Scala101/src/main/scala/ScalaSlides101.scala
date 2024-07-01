object ScalaSlides101 extends App{

  val lemon: Int = 30
  val aBagOfFlour: Int = 90
  val cider: Int = 250


  val johnsCart: Int = (5 * lemon + 2 * aBagOfFlour + 6 * cider)/100
  println(s"total cost is £$johnsCart")

  class Animal{

    var name: String = _
    var age: Int = _

    def hasLimbs(animalType: String, amountOfLimbs: Int) = {
      if amountOfLimbs >0 then println(s"$animalType has $amountOfLimbs") else s"$animalType has no limbs"
    }
  }

  class Mammal extends Animal{
    var hasHair = false
  }
  class Cat(name: String) extends Mammal

  val tom = new Cat("Tom")
  tom.hasHair = true
  tom.hasLimbs("cat",10)



}
