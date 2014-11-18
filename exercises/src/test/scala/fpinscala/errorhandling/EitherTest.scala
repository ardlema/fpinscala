package fpinscala.errorhandling

import org.scalatest._

class EitherTest extends FlatSpec with Matchers {

  "The Either trait" should "implement the map method" in {
    val rightValue = Right(2)
    val rightValueMapped = rightValue.map(element => element * 2)

    rightValueMapped should be(Right(4))

    val leftValue = Left(5)
    val leftValueMapped = leftValue.map(element => element)

    leftValueMapped should be(Left(5))
  }

  /*it should "returns the result inside the Some case of the Option, or if the Option is None, returns the given default value" in {
    val someInteger = Some(3)
    val someIntegerGetOrElse = someInteger.getOrElse(4)

    someIntegerGetOrElse should be(3)

    val someNone = None
    val someNoneGetOrElse = someNone.getOrElse(4)

    someNoneGetOrElse should be(4)
  }*/
}
