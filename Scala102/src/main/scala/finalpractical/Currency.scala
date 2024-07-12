package finalpractical

object Currency {
  case class Currency(code:String, symbol:String, value:Double)
  
  //val USD: Map[String, (String, Double)] = Map("USD" -> ("$",1.30))
  val USD: Currency = Currency("USD","$",1.30)
  val EUR: Currency = Currency("EUR","$",1.19)
  val JPY: Currency = Currency("JPY","¥",206.14)
  val CAD: Currency = Currency("CAD","CA$",1.76)
}
