object MapsAndFlatMaps extends App{


  val double = List(1,2,3).map(num => num * 2) // goes through each element and multiplies by 2 and gives you a new list of List(2,4,6)

  val doubleUsingSpecialCharacter =  List(1,2,3).map(_ * 2) // same as above but uses _ operator which allows you to take off num => num

  val caseMatch = List(1,2,3).map{
    case 1 => 10
    case x => x * 2
  } // you can also case match so for 1 it will give you 10 and for anything else it will double so you get a new List(10,4,6)

  // slide 60 Scala 102




}
