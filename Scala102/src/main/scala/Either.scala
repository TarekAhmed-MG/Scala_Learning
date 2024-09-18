import sun.security.util.Password

import java.nio.file.attribute.UserPrincipalNotFoundException

object Either extends App{
  /**
   * Similar to Options Either is a container type
   * takes two parameters; Left and Right
   * Left is usually an error case whereas Right is the correct one
   * remember the phrase Right is right(correct)
   * this is a good alternative to using a Try/Catch if you want to return more than a Throwable
   * can be used with pattern matching and projections
   * Avoids side effects keeping functions pure
   */

  // making an either
  def getIntAndAddOne(s:String): Either[String,Int] ={
    try{
      Right(s.toInt)
    }catch
      case e: Exception => Left("Failed")
  }

  println(getIntAndAddOne("12").right.map(num=> num+1)) // by using an either you can now manipulate all the right cases to how you like so here we done num => num+1
  println(getIntAndAddOne("Hello").map(num => num+1))

  // pattern matching eithers:
  getIntAndAddOne("13") match {
    case Left(_) =>println("We have a left")
    case Right(_) => println("We have a right")
  }

  // Practical - write a function that takes a username and password of type string and calls the userExists function and returns an Either with Left of Type User not found or Right of type Boolean

  def userExists(username:String,password: String): Boolean = {
    (username, password) match {
      case ("Boris Johnson", "pword123") => true
      case ("Barack Obama", "merica") => true
      case _ => false
    }
  }

  def checkUser(username:String, password: String): Either[String,Boolean] = {
   if userExists(username,password) then {
     Right(true)
   }else{
     Left("UserNotFoundError")
   }
  }

  println(checkUser("Boris Johnson", "pword1"))

  // Either are a more generic way of try catch which you can personalise and use pattern matching / projections

//val str: String = Option("hello") match {
//  case Some(value) => "hello"
//  case None => "oh no"
//}

  val str: String = Some("hello").getOrElse("None")














}
