package finalpractical

object Run extends App{

  val tarek = Customer("Tarek", 0)
  val restaurant = OrderCalculator("KFC")
  val tareksOrder = restaurant.TotalBill(restaurant.calculateOrder, List("Chicken Burger", "Beef Burger", "Plain Chips"), tarek)
  println(s"Total: £$tareksOrder")

}
