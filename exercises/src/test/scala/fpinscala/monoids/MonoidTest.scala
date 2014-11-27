package fpinscala.monoids

import org.scalatest._
import fpinscala.monoids.Monoid._

class MonoidTest
  extends FlatSpec
  with Matchers {

  "The Monoid trait" should "implement the int addition" in {
    val intAdditionResult = intAddition.op(2, 3)
    intAdditionResult should be(5)
  }

  it should "implement the int multiplication" in {
    val intAdditionResult = intMultiplication.op(2, 3)
    intAdditionResult should be(6)
  }

  it should "implement the boolean or" in {
    val booleanResult1 = booleanOr.op(true, false)
    booleanResult1 should be(true)

    val booleanResult2 = booleanOr.op(false, true)
    booleanResult2 should be(true)

    val booleanResult3 = booleanOr.op(false, false)
    booleanResult3 should be(false)

    val booleanResult4 = booleanOr.op(true, true)
    booleanResult4 should be(true)
  }

  it should "implement the boolean and" in {
    val booleanResult1 = booleanAnd.op(true, false)
    booleanResult1 should be(false)

    val booleanResult2 = booleanAnd.op(false, true)
    booleanResult2 should be(false)

    val booleanResult3 = booleanAnd.op(false, false)
    booleanResult3 should be(false)

    val booleanResult4 = booleanAnd.op(true, true)
    booleanResult4 should be(true)
  }
}
