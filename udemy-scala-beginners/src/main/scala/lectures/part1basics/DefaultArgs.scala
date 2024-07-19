package lectures.part1basics

import scala.annotation.tailrec

class DefaultArgs extends App{

  @tailrec
  private def trFact(n: Int, acc: Int = 1): Int = if (n <= 1) acc else trFact(n - 1, n * acc)

  val factorial10 = trFact(10,1)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080) : Unit = println("saving picture")
  //savePicture(800,600)

  /*

    In these two examples when you use default parameters you can omit having to write values into the parameter when calling the function so:
    val factorial10 = trFact(10,1) => can actually be written as val factorial10 = trFact(10)

    However this does not work if the leading parameters have default values as the compiler gets confused so something like savePicture(800,600) will not work
    because it would put 800 at format

   */

  // How to tackle this

  // Pass in evert leading argument - but you may have a lot of default arguments
  savePicture("bmp", 1000)

  //name the arguments
  savePicture(width = 500) // this way you dont have to pass in every leading default arguments

  savePicture(height = 200, format = "bmp") // this is a side effect to naming the arguments which is they do not need to be ordered.
  
}
