package finalpractical

object Run extends App{

  val tarek = Customer("Tarek", 10)
  val jane = Employee("Jane",1011,"London-121")
  val restaurant = Restaurant("KFC","American")

  var menuItems = Vector(
    Item("Chicken Burger",false,true,false,false,10.00),
    Item("Beef Burger",false,true,false,false,15.00),
    Item("Lobster",true,true,false,false,25.00),
    Item("Tuna Sandwich",false,false,true,false,5.00),
    Item("Cheese Sandwich",false,true,true,false,5.00),
    Item("Plain Chips",false,true,false,false,3.50),
    Item("Loaded Fries",false,true,false,false,6.50),
    Item("Coca Cola",false,false,false,true,10.00),
    Item("Fanta",false,true,false,true,1.20),
  )

  val crab = Item("Crab",true,true,false,false,25.00)

  menuItems = restaurant.addMenuItem(menuItems,crab)

  val tareksOrder = restaurant.TotalBill(
    restaurant.calculateOrder,
    menuItems,
    List("Coca Cola", "Coca Cola", "Coca Cola", "Coca Cola","Coca Cola"),
    Some(tarek),
    jane,
    Some("USD")
  )

  println(s"Order Details: \n Total: ${tareksOrder.head}${tareksOrder(1)}\n Employee: ${tareksOrder(2)}\n Store Id: ${tareksOrder(3)}\n Date and Time: ${tareksOrder(4)}")

}
