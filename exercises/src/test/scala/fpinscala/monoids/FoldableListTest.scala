package fpinscala.monoids

import fpinscala.monoids.Monoid._
import org.scalatest._

class FoldableListTest
  extends FlatSpec
  with Matchers {

  "The Foldable[List]" should "implement foldRight method" in {
    val listOfIntegers = List(1, 2, 3)
    val sumOfList = ListFoldable.foldRight(listOfIntegers)(0)(_ + _)
    sumOfList should be(6)
  }

  it should "implement foldLeft method" in {
    val listOfIntegers = List(1, 2, 3)
    val sumOfList = ListFoldable.foldLeft(listOfIntegers)(0)(_ + _)
    sumOfList should be(6)
  }

  it should "implement foldMap method" in {
    val myList = List(1.0,2.0,3.0)
    val myContatenatedDoubleList = ListFoldable.foldMap(myList)(elem => elem.toString)(stringMonoid)
    myContatenatedDoubleList should be("1.02.03.0")
  }
}
