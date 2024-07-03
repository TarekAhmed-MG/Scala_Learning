package finalpractical

case class GuessWho(name: String) {
  /**
   * you have characters - create a class for a character
   * need a Map for players which holds all the characters names and their state - being active and inactive
   * need a filter to filter though characters traits and makes everything else inactive
   * a method for guess - where if a player guesses a character then they will win
   * need a game state which has an up to date representation of all active and inactive characters
   */

  val tarek = GameCharacter("tarek","male","black","brown","beard","baseball cap")
  val vito =  GameCharacter("vito","male","black","white","none","fedora")
  val sonny = GameCharacter("sonny","male","brown","white","none","baseball cap")
  val fredo = GameCharacter("fredo","male","black","white","none","baseball cap")
  val michael = GameCharacter("michael","male","black","white","none","baseball cap")
  val connie = GameCharacter("connie","female","brown","white","none","baseball cap")
  val kay =  GameCharacter("kay","female","black","white","none","white hat")

  val people = List(tarek,vito,sonny,fredo,michael,connie,kay)

  def guess(guess: String, character: GameCharacter) = {
    //take the guess and character name put them to lower then check if they are the same, if they are return true otherwise return false
    // val characterGuess = if guess.toLowerCase.equals(character.name.toLowerCase) then true else false
    val characterGuess = guess.toLowerCase.equals(character.name.toLowerCase) // both equal
    characterGuess
  }

  def question(characterType: CharacterAttribute.Value, question: String):List[GameCharacter] = {

    // this is a java way of doing it when you have if elses try stick to pattern matching instead
    /*
    val update = if characterType.toString.toLowerCase.equals("gender") then people.filter(x => question.equals(x.gender))
    else if characterType.toString.toLowerCase.equals("hair") then people.filter(x => question.equals(x.hair))
    else if characterType.toString.toLowerCase.equals("skinColour") then people.filter(x => question.equals(x.skinColour))
    else if characterType.toString.toLowerCase.equals("beard") then people.filter(x => question.equals(x.beard))
    else if characterType.toString.toLowerCase.equals("headwear") then people.filter(x => question.equals(x.headwear))
    else people
     */

    // instead of if else you can use pattern matching => match case

    val update = characterType.toString.toLowerCase match {
      case "gender" => people.filter(x => question.equals(x.gender))
      case "hair" => people.filter(x => question.equals(x.hair))
      case "skincolour" => people.filter(x => question.equals(x.skinColour))
      case "beard" => people.filter(x => question.equals(x.beard))
      case "headwear" => people.filter(x => question.equals(x.headwear))
      case _ => people
    }
    update
  }
}
