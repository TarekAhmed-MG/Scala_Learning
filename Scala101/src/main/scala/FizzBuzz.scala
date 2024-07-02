class FizzBuzz(num:Int){

  def start(): String = {
    val fizzBuzz:String = if(num % 3 == 0 && num % 5 == 0) "FizzBuzz"
    else if (num % 3 == 0) "Fizz"
    else if (num % 5 == 0) "Buzz"
    else "Not FizzBuzz"

    fizzBuzz // remember that call the value as that's the return type there is no return keyword the last value is the return
  }

}
