package fpinscala.monoids

import fpinscala.monoids.Monoid._
import org.scalatest._

class FoldableStream
  extends FlatSpec
  with Matchers {

  "The Foldable[Stream]" should "implement foldRight method" in {
    val listOfIntegers = Stream(1, 2, 3)
    val sumOfList = StreamFoldable.foldRight(listOfIntegers)(0)(_ + _)
    sumOfList should be(6)
  }

  it should "implement foldLeft method" in {
    val listOfIntegers = Stream(1, 2, 3)
    val sumOfList = StreamFoldable.foldLeft(listOfIntegers)(0)(_ + _)
    sumOfList should be(6)
  }
}
