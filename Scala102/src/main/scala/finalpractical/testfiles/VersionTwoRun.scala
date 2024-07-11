package finalpractical.testfiles

import finalpractical.{Customer, Employee}

object VersionTwoRun extends App{

  val tarek = Customer("Tarek", 10)
  val jane = Employee("Jane",1011,"London-121")
  val restaurant = VersionTwo("KFC","American")

  val tareksOrder = restaurant.TotalBill(
    restaurant.calculateOrder,
    List("Chicken Burger", "Beef Burger", "Plain Chips", "Lobster"),
    Some(tarek),
    jane
  )
  
  println(s"Order Details: \n Total: £${tareksOrder.head}\n Employee: ${tareksOrder(1)}\n Store Id: ${tareksOrder(2)}\n Date and Time: ${tareksOrder(3)}")


}
