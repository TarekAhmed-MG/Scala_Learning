package finalpractical

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

case class Restaurant(name: String, theme: String){
// Main Code

  val addMenuItem: (Vector[Item],Item) => (Vector[Item]) = (menuItems, item) => {
    val addItem = menuItems :+ item
    addItem
  }

  // pass in an optional[String] where None give GBP(no transformation and others apply a transformation to the bill val by Currency Enum

  val calculateOrder:(Vector[Item], List[String], Option[Customer],Option[String]) => (Double,List[Any]) = (menuItems,purchasedItems, customer, currency) => {
    
    val order = purchasedItems.flatMap(x => menuItems.collect{case item if item.name == x => item})
    //val bill = order.collect(x => x.price).sum
    //val calculateBillNoPremium = bill - order.filter(x => x.isPremium).map(x => x.price).sum
    
    // do a case match that matches the string to the currency key and then apply the transformation on bill val bill match .....
    
    val bill = currency match
      case Some(currency) => currency match {
        case Currency.USD.code => order.collect(x => x.price).sum * Currency.USD.value
        case Currency.EUR.code => order.collect(x => x.price).sum * Currency.EUR.value
        case Currency.JPY.code => order.collect(x => x.price).sum * Currency.JPY.value
        case Currency.CAD.code => order.collect(x => x.price).sum * Currency.CAD.value
      }
      case None => order.collect(x => x.price).sum

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

  // here do a case match and give the currency sign and pass that to the list
  
  def TotalBill(funcOrder: (Vector[Item], List[String], Option[Customer],Option[String]) => (Double, List[Any]),
                menuItems: Vector[Item],
                purchasedItems: List[String], 
                customer: Option[Customer],
                employee: Employee,
                currency:Option[String]
               ) = {

    val itemList = funcOrder(menuItems,purchasedItems, customer,currency)(1)
    val bill = funcOrder(menuItems,purchasedItems, customer,currency)(0)

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
