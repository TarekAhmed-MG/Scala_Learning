import finalpractical.*
import org.scalatest.*
import org.scalatest.flatspec.AnyFlatSpec

class GuessWhoSpec extends AnyFlatSpec{

  var guessWhoGame: GuessWho = GuessWho("game 1")


  "tareks name" should "Match" in {
    assert(guessWhoGame.guess("tarek", guessWhoGame.tarek) === true)
  }

  "johns name" should "not Match" in {
    assert(guessWhoGame.guess("john", guessWhoGame.tarek) === false)
  }

  "if female" should "display all female characters" in {
    assert(guessWhoGame.question(CharacterAttribute.gender,"female") === List(guessWhoGame.connie,guessWhoGame.kay))
  }

  "if black" should "display all characters that have black hair" in {
    assert(guessWhoGame.question(CharacterAttribute.hair, "black") === List(guessWhoGame.tarek,guessWhoGame.vito,guessWhoGame.fredo,guessWhoGame.michael, guessWhoGame.kay))
  }

  "if brown" should "display all characters that have skin colour brown" in {
    assert(guessWhoGame.question(CharacterAttribute.skinColour, "brown") === List(guessWhoGame.tarek))
  }

  "if beard" should "display all characters with beards" in {
    assert(guessWhoGame.question(CharacterAttribute.beard, "beard") === List(guessWhoGame.tarek))
  }

  "if fedora" should "display all characters that have fedora" in {
    assert(guessWhoGame.question(CharacterAttribute.headwear, "fedora") === List(guessWhoGame.vito))
  }

}
