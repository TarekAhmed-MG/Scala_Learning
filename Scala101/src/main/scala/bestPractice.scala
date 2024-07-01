object bestPractice {
  // this file will show you best practices

  /**
   * Pure Functions - Best Practice
   */

  //Impure Function - is an example of what we shouldn't do

  // there are side effects to this as the second time its called you get the previous call thats why we have Allo twice

  var sentence: String = ""

  def appendImpure(word: String): String = {
    sentence = sentence + " " + word
    sentence.trim()
  }

  val a = appendImpure("Allo") // will give you "Allo"
  val b = appendImpure("Allo") // will give you "Allo Allo"

  // Pure function - what we should do
  // this is an example of referential transparency where there are no side effects and always produces the same output without causeing side effects
  def appendPure(sentence: String, word: String): String = sentence + " " + word.trim()

  val c = appendPure(sentence,"Allo") // will return you "Allo"
  val d = appendPure(sentence,"Allo") // return you "Allo" instead of "Allo Allo"
}
