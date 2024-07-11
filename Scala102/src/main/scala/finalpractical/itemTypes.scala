package finalpractical

object itemTypes extends Enumeration {
  type itemType = Value
  val PremiumFood: Value = Value("Premium Food")
  val HotFood: Value = Value("Hot Food")
  val Food: Value = Value("Food")
  val Drink: Value = Value("Drink")
}
