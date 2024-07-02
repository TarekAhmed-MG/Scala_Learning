import org.scalatest.*
import org.scalatest.flatspec.AnyFlatSpec


class FizzBuzzSpec extends AnyFlatSpec{

  "divide" should "return Fizz" in {
    val fizz = new FizzBuzz(6)
    assert(fizz.start() === ("Fizz"))
  }
  
  "divide" should " return Buzz" in {
    val fizz = new FizzBuzz(10)
    assert(fizz.start() === ("Buzz"))
  }
  
  "divide" should "return FizzBuzz" in {
    val fizz = new FizzBuzz(15)
    assert(fizz.start() === ("FizzBuzz"))
    
  }



}
