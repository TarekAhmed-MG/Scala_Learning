package com.rockthejvm

object PatternMatching extends App{

  // ToDo: switch expression

  val anInteger = 55
  // you can match this 55 to a number of cases without using the if-else case
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th" // the _ is equivalent to the default case which means everything else
  }
  // PM is an expression because it can be reduced to a value

  println(order) // 55th

  //so pattern matching is kind of like a switch statement but it takes it to another level as it
  // can deconstruct a data structure to its constituent parts

  case class Person(name: String, age: Int)
  val bob = Person("Bob", 43) // dont have to instantiante with new as there is a companion object with an apply method

  val personGreeting = bob match {
    case Person(n, a) => s"Hi, my name is $n snd i am $a years old." // its now matching bob to an entire structure
    case _ => "Something else"
  }
  println(personGreeting)

  // Pattern Matching usually used mainly for case classes but can be used elsewhere but thats more advanced

  // ToDo: deconstructing tuples
  val aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match {
    case (band,genre) => s"$band belongs to the genre $genre" // if the tuple follows the strutted (band,genre) then use them on the right hand side
    case _ => "I dont know what your talking about"
  }

  // ToDo: deconstructing Lists
  val aList = List(1,2,3)
  val listDescription = aList match {
    case List(_,2,_) => "List containing 2 on its second position" // in this case it checks for if a list has a size of 3 and then will look for the conditions, in this case 2
    case _ => "unknown list"
  }

  // if PM doesnt match anythingm it will throw a MatchError so we add a case _ for best practice
  // PM will try all cases in sequence so if you put the case _ on top, it will match to anything and will return unknown list.
}
