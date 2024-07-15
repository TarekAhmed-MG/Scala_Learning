package finalpractical

case class Item(name:String, isPremium:Boolean, isHotFood:Boolean, isFood:Boolean,isDrink:Boolean, price: Double)

// adjust above to below:
//case class Item(name:String, isPremium:Boolean, isHotFood:Boolean, consumable: Consumable, price: Double)
//sealed trait Consumable{
//  def isHot: Boolean
//}
//case object Food extends Consumable
//case object Drink extends Consumable


