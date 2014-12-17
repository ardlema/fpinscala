package fpinscala.monoids

import fpinscala.monoids.Monoid._
import org.scalatest._

class FoldableIndexedSeqTest
  extends FlatSpec
  with Matchers {

  "The Foldable[IndexedSeq]" should "implement foldRight method" in {
    val listOfIntegers = IndexedSeq(1, 2, 3)
    val sumOfList = IndexedSeqFoldable.foldRight(listOfIntegers)(0)(_ + _)
    sumOfList should be(6)
  }

  it should "implement foldLeft method" in {
    val listOfIntegers = IndexedSeq(1, 2, 3)
    val sumOfList = IndexedSeqFoldable.foldLeft(listOfIntegers)(0)(_ + _)
    sumOfList should be(6)
  }

  it should "implement foldMap method" in {
    val myList = IndexedSeq(1.0,2.0,3.0)
    val myContatenatedDoubleList = IndexedSeqFoldable.foldMap(myList)(elem => elem.toString)(stringMonoid)
    myContatenatedDoubleList should be("1.02.03.0")
  }
}
