import finalpractical.*
import org.scalatest.*
import org.scalatest.flatspec.AnyFlatSpec

class GuessWhoSpec extends AnyFlatSpec{

  val tarek = new GameCharacter("tarek","male","black","brown",true,"baseball cap")
  val vito = new GameCharacter("vito","male","black","white",true,"baseball cap")
  val sonny = new GameCharacter("sonny","male","brown","white",true,"baseball cap")
  val fredo = new GameCharacter("fredo","male","black","white",true,"baseball cap")
  val michael = new GameCharacter("michael","male","black","white",true,"baseball cap")
  val connie = new GameCharacter("connie","female","brown","white",false,"baseball cap")
  val kay = new GameCharacter("kay","female","black","white",false,"white hat")


  var guessWhoGame = new GuessWho()

  "tareks name" should "Match" in {
    assert(guessWhoGame.guess("tarek", tarek) === true)
  }

  "johns name" should "not Match" in {
    assert(guessWhoGame.guess("john", tarek) === false)
  }



}
