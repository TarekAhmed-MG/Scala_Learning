package FinalPractical

import FinalPractical.ScratchPad.itemTypes

object ScratchPad extends App{
 // Scratchpad - playground to test ideas

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
    "Plain Chips" -> (itemTypes.HotFood, 3.50),
    "Loaded Fries" -> (itemTypes.HotFood, 6.50),
    "Coca Cola" -> (itemTypes.Drink, 1.30),
    "Fanta" -> (itemTypes.Drink, 1.20)

  )

  // create add items function maybe using recursion
  // can put an optional for loyalty points
  // can maybe put an optional for service charge

//  def CalculateBill(purchasedItems: List[String]) = {
//    val Bill = purchasedItems.flatMap(x => menuItems.get(x)).map(x => x(1)).sum // tuple get integer
//    Bill
//  }
//
//  def OrderItemTypes(purchasedItems: List[String]) = {
//    val totalItemTypesOrdered = purchasedItems.flatMap(x => menuItems.get(x)).map(x => x(0)) // tuple get item type
//    totalItemTypesOrdered
//  }

  val add: (Int, Int) => Int = (a, b) => a + b // example function

  val calculateOrder: (List[String],Customer) => (Double, List[Any]) = (purchasedItems,customer) => {
        val bill = purchasedItems.flatMap(x => menuItems.get(x)).map(x => x(1)).sum // tuple get integer
        val itemTypesOrdered = purchasedItems.flatMap(x => menuItems.get(x)).map(x => x(0)) // tuple get item type
        val calculateBillNoPremium = bill - menuItems.collect{case (key,value) if value(0) == itemTypes.PremiumFood => value(1)}.sum // collect allows you to filter and apply any transformations in 1 go

        val total = customer.loyaltyPoints match {
          case 10 => bill - (calculateBillNoPremium * 20/100)
          case x if x >= 3 && x <= 9 => bill - (calculateBillNoPremium * customer.loyaltyPoints/100)
          case _ => bill
        }

        (total,itemTypesOrdered)
  }

//  def CalculateOrder(purchasedItems: List[String],customer: Customer): (Double, List[Any]) = {
//
//    val bill = purchasedItems.flatMap(x => menuItems.get(x)).map(x => x(1)).sum // tuple get integer
//    val itemTypesOrdered = purchasedItems.flatMap(x => menuItems.get(x)).map(x => x(0)) // tuple get item type
//    val calculateBillNoPremium = bill - menuItems.collect{case (key,value) if value(0) == itemTypes.PremiumFood => value(1)}.sum // collect allows you to filter and apply any transformations in 1 go
//
//    val total = customer.loyaltyPoints match {
//      case 10 => bill - (calculateBillNoPremium * 20/100)
//      case x if x >= 3 && x <= 9 => bill - (calculateBillNoPremium * customer.loyaltyPoints/100)
//      case _ => bill
//    }
//
//    (total,itemTypesOrdered)
//  }

//  def ServiceCharge(orderItemTypes:List[String], bill:Int) = {
//
//    val serviceCharge = orderItemTypes match {
//      case x if x.contains(itemTypes.PremiumFood) => if bill * 25 / 100 > 40 then 40 else bill * 25 / 100
//      case x if x.contains(itemTypes.HotFood) => if bill * 20 / 100 > 20 then 20 else bill * 20 / 100
//      case x if x.contains(itemTypes.Food) => if bill * 10 / 100 > 20 then 20 else bill * 10 / 100
//      case _ => 0
//    }
//    serviceCharge
//  }

  def ServiceCharge(funcOrder: (List[String], Customer) => (Double, List[Any]), purchasedItems: List[String], customer: Customer) = {

    val itemList = funcOrder(purchasedItems, customer)(1)
    val bill = funcOrder(purchasedItems, customer)(0)

    val serviceCharge = itemList match {
      case x if x.contains(itemTypes.PremiumFood) => if bill * 25 / 100 > 40 then 40 else bill * 25 / 100
      case x if x.contains(itemTypes.HotFood) => if bill * 20 / 100 > 20 then 20 else bill * 20 / 100
      case x if x.contains(itemTypes.Food) => if bill * 10 / 100 > 20 then 20 else bill * 10 / 100
      case _ => 0
    }
    serviceCharge
  }

  val john = Customer("John",0)
//  CalculateOrder(List("Chicken Burger", "Beef Burger", "Plain Chips"),john)
  ServiceCharge(calculateOrder,List("Chicken Burger", "Beef Burger", "Plain Chips"),john)

}
