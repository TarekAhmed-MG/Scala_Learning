package finalpractical
import scala.util.Random

case class GuessWho(name: String) {
  /**
   * you have characters - create a class for a character
   * need a Map for players which holds all the characters names and their state - being active and inactive
   * need a filter to filter though characters traits and makes everything else inactive
   * a method for guess - where if a player guesses a character then they will win
   * need a game state which has an up to date representation of all active and inactive characters
   */

  val tarek: GameCharacter = GameCharacter("tarek","male","black","brown","beard","baseball cap")
  val vito: GameCharacter =  GameCharacter("vito","male","black","white","none","fedora")
  val sonny: GameCharacter = GameCharacter("sonny","male","brown","white","none","baseball cap")
  val fredo: GameCharacter = GameCharacter("fredo","male","black","white","none","baseball cap")
  val michael: GameCharacter = GameCharacter("michael","male","black","white","none","baseball cap")
  val connie: GameCharacter = GameCharacter("connie","female","brown","white","none","baseball cap")
  val kay: GameCharacter =  GameCharacter("kay","female","black","white","none","white hat")

  val people: List[GameCharacter] = List(tarek,vito,sonny,fredo,michael,connie,kay)

  def chooseCharacter(characterList: List[GameCharacter]): String = {
    characterList(Random.between(0,characterList.size-1)).name
  }

  def printCurrentCharacters(characterList: List[GameCharacter]): Unit = {
    characterList.foreach(character => println(character.name))
  } // put it in GameCharacter class as an object

  def guess(guess: String, character: GameCharacter) = guess.toLowerCase.equals(character.name.toLowerCase) // refactor this make a val that chooses the character and take out the argument character game character all together
    //take the guess and character name put them to lower then check if they are the same, if they are return true otherwise return false
    // val characterGuess = if guess.toLowerCase.equals(character.name.toLowerCase) then true else false

  def question(charcterList: List[GameCharacter], characterType: CharacterAttribute.Value, question: String):List[GameCharacter] = {

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

    characterType.toString match {
      case "gender" => charcterList.filter(x => question.equals(x.gender))
      case "hair" => charcterList.filter(x => question.equals(x.hair))
      case "skincolour" => charcterList.filter(x => question.equals(x.skinColour))
      case "beard" => charcterList.filter(x => question.equals(x.beard))
      case "headwear" => charcterList.filter(x => question.equals(x.headwear))
      case _ => charcterList
    }
  }
}
