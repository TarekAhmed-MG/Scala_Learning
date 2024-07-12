import finalpractical.*
import org.scalatest.*
import org.scalatest.flatspec.AnyFlatSpec
import java.time.LocalTime

class RestaurantSpec extends AnyFlatSpec {

  val tarek: Customer = Customer("Tarek", 10)
  val jenny: Employee = Employee("Jenny", 1011, "London-121")
  val restaurant: Restaurant = Restaurant("McDonalds", "American")

  var menuItems: Vector[Item] = Vector(
    Item("Chicken Burger", false, true, false, false, 10.00),
    Item("Beef Burger", false, true, false, false, 15.00),
    Item("Lobster", true, true, false, false, 25.00),
    Item("Tuna Sandwich", false, false, true, false, 5.00),
    Item("Cheese Sandwich", false, true, true, false, 5.00),
    Item("Plain Chips", false, true, false, false, 3.50),
    Item("Loaded Fries", false, true, false, false, 6.50),
    Item("Coca Cola", false, false, false, true, 10.00),
    Item("Fanta", false, true, false, true, 1.20),
  )

  "crab" should "be added to menu" in {

    val crab = Item("Crab", true, true, false, false, 25.00)
    menuItems = restaurant.addMenuItem(menuItems, crab)

    assert(menuItems.size == 10)
  }

  "happy hour" should "be true only between 18:00 and 21:00" in {
    if LocalTime.now.isAfter(LocalTime.of(18,0,0)) && LocalTime.now.isBefore(LocalTime.of(21,0,0))
    then assert(restaurant.happyHour) else assert(!restaurant.happyHour)
  }

  // need to make tests for each currency
  // tests for loyalty points
  // test for happy hour drink discount


}
