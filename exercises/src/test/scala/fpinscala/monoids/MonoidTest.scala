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

  it should "give a monoid instance for combining Option values" in {
    val monoidOption = optionMonoid[Int]
    val someElement1 = monoidOption.op(Some(1), None)
    someElement1 should be(Some(1))

    val someElement2 = monoidOption.op(None, Some(2))
    someElement2 should be(Some(2))

    val noneElement = monoidOption.op(None, None)
    noneElement should be(None)
  }

  it should "give a general function concatenate" in {
    val myList = List(1, 2, 3)
    val myContatenatedIntList = concatenate(myList, intAddition)
    myContatenatedIntList should be(6)

    val myListString = List("a", "b", "c")
    val myContatenatedStringList = concatenate(myListString, stringMonoid)
    myContatenatedStringList should be("abc")
  }

  it should "implement foldmap" in {
    val myList = List(1.0,2.0,3.0)
    val myContatenatedDoubleList = foldMap(myList, stringMonoid)(elem => elem.toString)
    myContatenatedDoubleList should be("1.02.03.0")
  }

  it should "implement foldright using foldmap" in {
    val myList = List(1,2,3)
    val listTotal = foldRight(myList)(0)(_ + _)
    listTotal should be(6)
  }

  it should "implement foldleft using foldmap" in {
    val myList = List(1,2,3)
    val listTotal = foldLeft(myList)(0)(_ + _)
    listTotal should be(6)
  }

  it should "implement foldmap for IndexedSeq" in {
    val zeroElementsIndexedSeq = IndexedSeq()
    val zeroListTotal = foldMapV(zeroElementsIndexedSeq, stringMonoid)(elem => elem.toString)
    zeroListTotal should be("")

    val mySimpleIndexedSeq = IndexedSeq(1.0)
    val simpleListTotal = foldMapV(mySimpleIndexedSeq, stringMonoid)(elem => elem.toString)
    simpleListTotal should be("1.0")

    val myIndexedSeq = IndexedSeq(1.0,2.0,3.0)
    val listTotal = foldMapV(myIndexedSeq, stringMonoid)(elem => elem.toString)
    listTotal should be("1.02.03.0")
  }

  it should "detect whether an IndexedSeq is ordered" in {
    val orderedSeq = IndexedSeq(1, 2, 3)
    val isOrdered1 = ordered(orderedSeq)
    isOrdered1 should be(true)

    val notOrderedSeq = IndexedSeq(4, 2, 7)
    val isOrdered2 = ordered(notOrderedSeq)
    isOrdered2 should be(false)
  }
}
