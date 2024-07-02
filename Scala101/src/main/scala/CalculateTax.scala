class CalculateTax (input: Int){

  def Calculate() = {
    val calculateTax = if (input > 10000) (input/100) * 15
    else if (input > 50000) (input/100) * 20
    else if (input > 100000) (input/100) * 40
    else 0

    calculateTax
  }

}
