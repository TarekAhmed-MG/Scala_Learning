package finalpractical

object Run extends App{

  val tarek = Customer("Tarek", 10)
  val restaurant = OrderCalculator("KFC")
  val tareksOrder = restaurant.TotalBill(restaurant.calculateOrder, List("Chicken Burger", "Beef Burger", "Plain Chips", "Lobster"), Some(tarek))
  println(s"Total: £$tareksOrder")

}
