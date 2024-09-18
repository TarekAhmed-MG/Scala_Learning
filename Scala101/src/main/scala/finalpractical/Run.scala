package finalpractical

object Run extends App {
  private val guessWho = GuessWho("Game 2")
  private val randomCharacter = guessWho.chooseCharacter(guessWho.people)

  println(s"your random Character is: $randomCharacter")
  guessWho.printCurrentCharacters(guessWho.people)
  
}
