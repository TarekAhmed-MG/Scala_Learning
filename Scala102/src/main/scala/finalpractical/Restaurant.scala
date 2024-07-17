package finalpractical

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

case class Restaurant(name: String, theme: String){
// Main Code


  val addMenuItem: (Vector[Item],Item) => Vector[Item] = (menuItems, item) => {
   menuItems :+ item

  }

  val happyHour: Boolean = {
    LocalTime.now.isAfter(LocalTime.of(18,0,0)) && LocalTime.now.isBefore(LocalTime.of(21,0,0))
  }

  // pass in an optional[String] where None give GBP(no transformation and others apply a transformation to the bill val by Currency Enum

  val calculateOrder:(Vector[Item], List[String], Option[Customer],Option[String]) => (Double,List[Any]) = (menuItems,purchasedItems, customer, currency) => {
    
    val order = purchasedItems.flatMap(x => menuItems.collect{case item if item.name == x => item})
    
    val billHappyHourCheck = if happyHour then order.collect(x=> if x.isDrink then x.price/2 else x.price).sum else order.collect(x => x.price).sum

    val calculateBillNoPremium = billHappyHourCheck - order.filter(x => x.isPremium).map(x => x.price).sum

    val itemTypesOrdered = purchasedItems.flatMap(x => menuItems.collect{case item if item.name == x => item match {
      case item if item.isPremium => itemTypes.PremiumFood
      case item if item.isHotFood => itemTypes.HotFood
      case item if item.isFood => itemTypes.Food
      case item if item.isDrink => itemTypes.Drink
      case _ => None
      }})

    val total = customer match {
      case None => billHappyHourCheck
      case Some(customer) => customer.loyaltyPoints match {
        case 10 => billHappyHourCheck - (calculateBillNoPremium * 20 / 100)
        case x if x >= 3 && x <= 9 => billHappyHourCheck - (calculateBillNoPremium * customer.loyaltyPoints / 100)
        case _ => billHappyHourCheck
      }
    }

    val totalWithCurrencyCheck = currency match
      case Some(currency) => currency match {
        case Currency.USD.code => total * Currency.USD.value
        case Currency.EUR.code => total * Currency.EUR.value
        case Currency.JPY.code => total * Currency.JPY.value
        case Currency.CAD.code => total * Currency.CAD.value
      }
      case None => total
    
    (totalWithCurrencyCheck, itemTypesOrdered)
  }

  // here do a case match and give the currency sign and pass that to the list

  /**
   * HOF returns total bill with service charge included
   * @param funcOrder takes in calculateOrder
   * @param menuItems
   * @param purchasedItems
   * @param customer
   * @param employee
   * @param currency
   * @return
   */
  def TotalBill(funcOrder: (Vector[Item], List[String], Option[Customer],Option[String]) => (Double, List[Any]),
                menuItems: Vector[Item],
                purchasedItems: List[String], 
                customer: Option[Customer],
                employee: Employee,
                currency:Option[String] = None
               ) = {

    val itemList = funcOrder(menuItems,purchasedItems, customer, currency)(1)
    val bill = funcOrder(menuItems,purchasedItems, customer, currency)(0)

    val serviceCharge = itemList match {
      case x if x.contains(itemTypes.PremiumFood) => if bill * 25 / 100 > 40 then 40 else bill * 25 / 100
      case x if x.contains(itemTypes.HotFood) => if bill * 20 / 100 > 20 then 20 else bill * 20 / 100
      case x if x.contains(itemTypes.Food) => if bill * 10 / 100 > 20 then 20 else bill * 10 / 100
      case _ => 0
    }

    val currencySymbol = currency match
      case Some(currency) => currency match {
        case Currency.USD.code => Currency.USD.symbol
        case Currency.EUR.code => Currency.EUR.symbol
        case Currency.JPY.code => Currency.JPY.symbol
        case Currency.CAD.code => Currency.CAD.symbol
      }
      case None => "£"

    List(
      currencySymbol,
      bill + serviceCharge,
      employee.name,
      employee.storeId,
      LocalDateTime.now.format(DateTimeFormatter.ofPattern("[dd-MM-yyyy] [HH:mm:ss]"))
    )
  }
}
