package fpinscala.monads

import org.scalatest._

class MonadTest extends FlatSpec with Matchers {

  val theParMonad = Monad.parMonad

  "The Par monad instance" should "satisfy the monad laws" in {
    //TODO: Check conditions
    /*theParMonad.unit()
    theParMonad.flatMap
    theParMonad.map
    theParMonad.map2*/
  }
}
