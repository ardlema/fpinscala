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

  it should "implement the flatmap method" in {
    val rightValue = Right(2)
    val rightValueFlatMapped = rightValue.flatMap(element => Right(element * 2))

    rightValueFlatMapped should be(Right(4))

    /*val leftValue = Left(5)
    val leftValueMapped = leftValue.map(element => element)

    leftValueMapped should be(Left(5))*/
  }

  it should "implement the orElse method" in {
    val rightValue = Right(2)
    val rightValueFlatMapped = rightValue.orElse(Right(4))

    rightValueFlatMapped should be(Right(2))

    val leftValue = Left(5)
    val leftValueMapped = leftValue.orElse(Right(2))

    leftValueMapped should be(Right(2))
  }
}
