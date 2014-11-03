package fpinscala.errorhandling

import org.scalatest._

class OptionTest extends FlatSpec with Matchers {

  "The Option trait" should "apply f if the Option is not None." in {
    val someInteger = Some(2)
    val someIntegerMultiplyBy2 = someInteger.map(element => element * 2)

    someIntegerMultiplyBy2 should be(Some(4))

    val none = None
    val noneMultiplyBy2 = none.map(element => element.toString)

    noneMultiplyBy2 should be(None)
  }

  it should "returns the result inside the Some case of the Option, or if the Option is None, returns the given default value" in {
    val someInteger = Some(3)
    val someIntegerGetOrElse = someInteger.getOrElse(4)

    someIntegerGetOrElse should be(3)

    val someNone = None
    val someNoneGetOrElse = someNone.getOrElse(4)

    someNoneGetOrElse should be(4)
  }

  it should "apply f, which may fail, to the Option if not None." in {
    val someInteger = Some(2)
    val someIntegerMultiplyBy2 = someInteger.flatMap(element => Some(element * 2))

    someIntegerMultiplyBy2 should be(Some(4))

    val none = None
    val noneMultiplyBy2 = none.flatMap(element => Some(element.toString))

    noneMultiplyBy2 should be(None)
  }

  it should "returns the first Option if it’s defined; otherwise, it returns the second Option" in {
    val someInteger = Some(5)
    val orElse = someInteger.orElse(Some(4))

    orElse should be(Some(5))

    val someNone = None
    val orElseNone = someNone.orElse(Some(2))

    orElseNone should be(Some(2))
  }

  it should "convert Some to None if the value doesn’t satisfy f." in {
    val someInteger = Some(5)
    val someIntegerFilteredTrue = someInteger.filter(element => element > 2)
    val someIntegerFilteredFalse = someInteger.filter(element => element > 10)

    someIntegerFilteredTrue should be(Some(5))
    someIntegerFilteredFalse should be(None)
  }

  it should "work out the variance" in {
    val someSequence = Seq[Double](600, 470, 170, 430, 300)
    val variance = Option.variance(someSequence)

    variance should be(Some(21704))
  }

  it should "combine two Option values using a binary function, If either Option value is None, then the return value is too" in {
    def addTwoInteger(integer1: Int, integer2: Int): Double = 2.0
    val someValue = Some(3)
    val noneValue = None
    val optionsCombined = Option.map2(someValue, noneValue)(addTwoInteger)

    optionsCombined should be(None)

    val someValue2 = Some(3)
    val noneValue2 = Some(4)
    val optionsCombined2 = Option.map2(someValue2, noneValue2)(addTwoInteger)

    optionsCombined2 should be(Some(2.0))
  }
}
