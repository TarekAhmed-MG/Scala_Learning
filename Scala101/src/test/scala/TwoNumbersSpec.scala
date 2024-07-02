import org.scalatest.*
import org.scalatest.flatspec.AnyFlatSpec

class TwoNumbersSpec extends AnyFlatSpec{

  "add" should "add numbers" in { // gives a description of what we are trying to test
    val nums = new TwoNumbers(1, 2)
    assert(nums.add() === 3)
  }

}
