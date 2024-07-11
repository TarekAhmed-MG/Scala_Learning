package finalpractical

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

case class Restaurant(name: String, theme: String){
// Main Code

  val addMenuItem: (Vector[Item],Item) => (Vector[Item]) = (menuItems, item) => {
    val addItem = menuItems :+ item
    addItem
  }

  val calculateOrder:(Vector[Item], List[String], Option[Customer]) => (Double,List[Any]) = (menuItems,purchasedItems, customer) => {
    
    val order = purchasedItems.flatMap(x => menuItems.collect{case item if item.name == x => item})
    val bill = order.collect(x => x.price).sum
    val calculateBillNoPremium = bill - order.filter(x => x.isPremium).map(x => x.price).sum
    
    val itemTypesOrdered = purchasedItems.flatMap(x => menuItems.collect{case item if item.name == x => item match {
      case item if item.isPremium => itemTypes.PremiumFood
      case item if item.isHotFood => itemTypes.HotFood
      case item if item.isFood => itemTypes.Food
      case item if item.isDrink => itemTypes.Drink
      case _ => None
      }})

    val total = customer match {
      case None => bill
      case Some(customer) => customer.loyaltyPoints match {
        case 10 => bill - (calculateBillNoPremium * 20 / 100)
        case x if x >= 3 && x <= 9 => bill - (calculateBillNoPremium * customer.loyaltyPoints / 100)
        case _ => bill
      }
    }
    
    (total, itemTypesOrdered)
  }
  
  def TotalBill(funcOrder: (Vector[Item], List[String], Option[Customer]) => (Double, List[Any]), 
                menuItems: Vector[Item],
                purchasedItems: List[String], 
                customer: Option[Customer],
                employee: Employee
               ) = {

    val itemList = funcOrder(menuItems,purchasedItems, customer)(1)
    val bill = funcOrder(menuItems,purchasedItems, customer)(0)

    val serviceCharge = itemList match {
      case x if x.contains(itemTypes.PremiumFood) => if bill * 25 / 100 > 40 then 40 else bill * 25 / 100
      case x if x.contains(itemTypes.HotFood) => if bill * 20 / 100 > 20 then 20 else bill * 20 / 100
      case x if x.contains(itemTypes.Food) => if bill * 10 / 100 > 20 then 20 else bill * 10 / 100
      case _ => 0
    }
    
    List(
      bill + serviceCharge,
      employee.name,
      employee.storeId,
      LocalDateTime.now.format(DateTimeFormatter.ofPattern("[dd-MM-yyyy] [HH:mm:ss]"))
    )
    
  }
}
