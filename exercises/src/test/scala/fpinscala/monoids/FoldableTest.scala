package fpinscala.monoids

import fpinscala.monoids.Monoid._
import org.scalatest._

class FoldableTest
  extends FlatSpec
  with Matchers {

  "The Foldable[List]" should "implement foldRight method" in {
    val listOfIntegers = List(1, 2, 3)
    val sumOfList = ListFoldable.foldRight(listOfIntegers)(0)(_ + _)
    sumOfList should be(6)
  }
}
