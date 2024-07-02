import finalpractical.*
import org.scalatest.*
import org.scalatest.flatspec.AnyFlatSpec

class GuessWhoSpec extends AnyFlatSpec{

  var john = new GameCharacter("john")
  var guessWhoGame = new GuessWho()

  "johns name" should "Match" in {
    assert(guessWhoGame.guess("john", john) === true)
  }

}
