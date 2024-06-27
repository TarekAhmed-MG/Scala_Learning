package com.rockthejvm

object Basics extends App{
  // by extending app - it allows you to execute any code between the curly braces as a standalone executable

  // ToDo: defining a value
  
  val meaningOfLife: Int = 42 //  this is the structure of defining a value - Keyword Val - name of value - type  then declaration
  // val is the equivalent of a cost in javascript or final in java (immutable) cannot be reassigned

  val aBoolean = false // you also dont need to mention type (: Int etc.) - most of the time its optional.

  // ToDo: Strings and String operations
  
  val aString = "I love Scala"
  val aComposedString = "I" + "Love" + "Scala" // you can concatenate strings like other languages
  val anInterpolatedString = s"The meaning of life is $meaningOfLife" // specific to scala you can inject strings using an s in the front and then add a $ with variable name

  // with scala you have to think of values and expressions (the right hand side is the expression)

  // ToDo: expressions = structures that can be reduced to a value
  
  val anExpression = 2 + 3

  // generally in other programming languages we think of things as instructions which the computer does sequentially
  // but in scala we dont think like this we think of values and composing them to obtain new values

  // ToDo: if-expression - these are like if else in java using the ternary operator eg..  int result = 11 > 9 ? ++val1 : ++val2;
  
  val ifEpression = if(meaningOfLife > 43) 56 else 999
  // this is a if expression as it reduces to a single value which is either 56 or 999 depending on the value of meaningOfLife

  // you can also chain ifExpressions into a composed expression which is still a single expression
  val chainedIfEpression =
    if(meaningOfLife > 43) 56
    else if (meaningOfLife < 0 ) -2
    else if (meaningOfLife > 999) 78
    else 0

  // we are thinking in a different way now as we are now assigning values to the names if conditions are met instead of doing something depending on a condition

  // ToDo: code blocks
  
    // you can add definitions, functions classes and even inner codeblocks inside code blocks but would need to return something.
  val aCodeBlock = {
    // definitions
    val aLocalValue = 67

    // the last expression of a codeblock is the value of the entire block and is the return
    aLocalValue + 3 // we havent defined the type of the codeblock but the compiler looks at this and automatically defines it.
  }

  // ToDo: define a function
  
  /*
      you start a function using def like python then pass the name and parenthesis
      name(inside here you put the arguments -> name: Type -> where each type is seperated by a comma)
      then you specify the return type after the parenthesis (): Return Type
      then followed by an = sign and a single expression which is the return value of the function
  */
  
  // single line function
  def myFunctionSingleLine(x: Int, y: String): String = y + " " + x

  // code block function
  def myFunctionCodeBlock(x: Int, y: String) = {
    y + " " + x
  }

  // ToDo: functions 
  
  // functions can be complex and depend on their own expressions meaning they can be recursive

  def factorial(n:Int): Int = if(n <= 1) 1 else n * factorial(n - 1)

  // In Scala we dont use loops or iteration, we use RECURSION! there are loops and variables but it is discouraged

  // ToDo: Unit type 
  
  /*
      which means --> no meaningful value === "void" in other languages
      we think of this as the type of SIDE EFFECTS they have nothing to do with computing meaningful information
      they relate to doing things like printing to screen sending something to server/socket or saving to file
      println("This has a return type 'unit' which is voids in other languages")
  */
  
  def myUnitReturningFunction(): Unit = {
    println("Returns a Unit")
  }
  
  // the only value Unit contains is ==> () the open and close parenthesis.

}
