import org.scalatest.*
import org.scalatest.flatspec.AnyFlatSpec

class CalculateTaxSpec extends AnyFlatSpec {

  "greater than 10000" should "return 15%" in {
    val tax = new CalculateTax(20000)
    assert(tax.Calculate() === 3000)

  }

  "greater than 50000" should "return 20%" in {

    val tax = new CalculateTax(60000)
    assert(tax.Calculate() === 9000)

  }

  "greater than 100000" should "return 40%" in {

    val tax = new CalculateTax(180000)
    assert(tax.Calculate() === 27000)

  }

}
