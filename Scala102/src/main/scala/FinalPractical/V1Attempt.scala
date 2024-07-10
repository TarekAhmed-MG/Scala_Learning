package FinalPractical

import FinalPractical.Run.itemTypes

object Run extends App {
  // V1 code - first iteration

  object itemTypes extends Enumeration {
    type itemType = Value
    val Food: Value = Value("Food")
    val HotFood: Value = Value("Hot Food")
    val PremiumFood: Value = Value("Premium Food")
    val Drink: Value = Value("Drink")
  }

  val menuItems: Map[String, (itemTypes.Value, Double)] = Map(

    "Chicken Burger" -> (itemTypes.HotFood, 10.00),
    "Beef Burger" -> (itemTypes.HotFood, 15.00),
    "Lobster" -> (itemTypes.PremiumFood, 25.00),
    "Tuna Sandwich" -> (itemTypes.Food, 5.00),
    "Cheese Sandwich" -> (itemTypes.Food, 5.00),
    "Plain Chips" ->(itemTypes.HotFood, 3.50),
    "Loaded Fries" ->(itemTypes.HotFood, 6.50),
    "Coca Cola" -> (itemTypes.Drink, 1.30),
    "Fanta" -> (itemTypes.Drink, 1.20)

  )

  // create add items function maybe using recursion
  // can put an optional for loyalty points


  def TotalBill(menuItems: Map[String, (itemTypes.Value, Double)], purchasedItems: List[String]) = {

    val totalBillNoServiceCharge = purchasedItems.flatMap(x => menuItems.get(x)).map(x => x(1)).sum // tuple get integer
    val totalItemTypesOrdered = purchasedItems.flatMap(x => menuItems.get(x)).map(x => x(0)) // tuple get item type

//    val serviceCharge = if totalItemTypesOrdered.contains(itemTypes.PremiumFood) then if totalBillNoServiceCharge * 25/100 > 40 then 40 else totalBillNoServiceCharge * 25/100
//    else if totalItemTypesOrdered.contains(itemTypes.HotFood) then if totalBillNoServiceCharge * 20/100 > 20 then 20 else totalBillNoServiceCharge * 20/100
//    else if totalItemTypesOrdered.contains(itemTypes.Food) then if totalBillNoServiceCharge * 10/100 > 20 then 20 else totalBillNoServiceCharge * 10/100
//    else totalBillNoServiceCharge

    val serviceCharge = totalItemTypesOrdered match {
      case x if x.contains(itemTypes.PremiumFood) => if totalBillNoServiceCharge * 25 / 100 > 40 then 40 else totalBillNoServiceCharge * 25 / 100
      case x if x.contains(itemTypes.HotFood) => if totalBillNoServiceCharge * 20 / 100 > 20 then 20 else totalBillNoServiceCharge * 20 / 100
      case x if x.contains(itemTypes.Food) => if totalBillNoServiceCharge * 10 / 100 > 20 then 20 else totalBillNoServiceCharge * 10 / 100
      case _ => totalBillNoServiceCharge
    }

    val finalBill = totalBillNoServiceCharge + serviceCharge
    println(s"Total Bill: £$finalBill")

  }
  // when you place order total bill takes the customer details too and then adds a star using class copy


  TotalBill(menuItems, List("Chicken Burger","Beef Burger","Plain Chips"))

}
