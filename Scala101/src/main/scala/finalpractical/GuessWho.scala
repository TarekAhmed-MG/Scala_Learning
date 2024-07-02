package finalpractical

class GuessWho {
  /**
   * you have characters - create a class for a character
   * need a Map for players which holds all the characters names and their state - being active and inactive
   * need a filter to filter though characters traits and makes everything else inactive
   * a method for guess - where if a player guesses a character then they will win
   */

  def guess(guess:String, character: GameCharacter) = {
    //take the guess and character name put them to lower then check if they are the same, if they are return true otherwise return false
    val characterGuess = if guess.toLowerCase.equals(character.name.toLowerCase) then true else false
    characterGuess
  }



}
