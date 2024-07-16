package finalpractical

object Run extends App{

  val tarek = Customer("Tarek", 10)
  val jane = Employee("Jane",1011,"London-121")
  val restaurant = Restaurant("KFC","American")

  //Item("Chicken Burger",isPremium = false,true,Food,10.00), // try avoid passing primitive types in as it makes it unreadable so try pass in objects
//  var menuItems = Vector(
//    Item("Cheese Burger",isPremium = false,true,false,false,10.00),
//    Item("Beef Burger",false,true,false,false,15.00),
//    Item("Lobster",true,true,false,false,25.00),
//    Item("Tuna Sandwich",false,false,true,false,5.00),
//    Item("Cheese Sandwich",false,true,true,false,5.00),
//    Item("Plain Chips",false,true,false,false,3.50),
//    Item("Loaded Fries",false,true,false,false,6.50),
//    Item("Cola",false,false,false,true,0.50),
//    Item("Coffee",false,true,false,true,1.00),
//  )

  var menuItems = Vector(
    Item("Cheese Burger", isPremium = false, true, false, false, 5.00),
    Item("Beef Burger", false, true, false, false, 4.50),
    Item("Lobster", true, true, false, false, 250),
    Item("Cola", false, false, false, true, 0.50),
    Item("Coffee", false, true, false, true, 1.00),
  )

  //val crab = Item("Crab",true,true,false,false,25.00)
  //menuItems = restaurant.addMenuItem(menuItems,crab)

  val tareksOrder = restaurant.TotalBill(
    restaurant.calculateOrder,
    menuItems,
    List("Cheese Burger", "Beef Burger", "Lobster", "Cola","Coffee"),
    Some(tarek),
    jane
  )

  println(s"Order Details: \n Total: ${tareksOrder.head}${tareksOrder(1)}\n Employee: ${tareksOrder(2)}\n Store Id: ${tareksOrder(3)}\n Date and Time: ${tareksOrder(4)}")

}
