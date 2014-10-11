package fpinscala.datastructures

import org.scalatest.{Matchers, FlatSpec}

class ListTest extends FlatSpec with Matchers {

  "Our list implementation " should "remove the first element" in {
    val myList = List(1,2,3)
    val myListWithoutFirstElement = List.tail(myList)
    myListWithoutFirstElement should be(List(2,3))
  }

  it should "return and empty list when the list has one element" in {
    val myList = List(1)
    val myListWithoutFirstElement = List.tail(myList)
    myListWithoutFirstElement should be(List())
  }

  it should "return Nil when the list is empty" in {
    val myList = List()
    val myListWithoutFirstElement = List.tail(myList)
    myListWithoutFirstElement should be(Nil)
  }

  it should "replace the first element of a List with values" in {
    val myList = List(1,2,3)
    val myListWithTheFirstElementReplaced = List.setHead(myList, 5)
    myListWithTheFirstElementReplaced should be(List(5,2,3))
  }

  it should "replace the first element of a List with one element" in {
    val myList = List(1)
    val myListWithTheFirstElementReplaced = List.setHead(myList, 5)
    myListWithTheFirstElementReplaced should be(List(5))
  }

  it should "return Nil when trying to replace the first element of an empty list" in {
    val myList = List()
    intercept[RuntimeException] {
      List.setHead(myList, 5)
    }
  }

  it should "drop the first n elements of the list" in {
    val myList = List(3, 4, 5, 6)
    val myListWithTheFirstNElementsRemoved = List.drop(myList, 2)
    myListWithTheFirstNElementsRemoved should be(List(5, 6))
  }

  it should "drop no elements of the list when the number of elements to be removed is 0" in {
    val myList = List(3, 4, 5, 6)
    val myListWithTheFirstNElementsRemoved = List.drop(myList, 0)
    myListWithTheFirstNElementsRemoved should be(List(3, 4, 5, 6))
  }

  it should "ignore the number of elements to be removed when the list is empty" in {
    val myList = List()
    val myListWithTheFirstNElementsRemoved = List.drop(myList, 5)
    myListWithTheFirstNElementsRemoved should be(List())
  }

  it should "remove elements from the list prefix as long as they match a predicate" in {
    val myList = List(3, 4, 5, 6)
    val myListWithTheFirstNElementsRemoved = List.dropWhile(myList, (x: Int) => x < 5)
    myListWithTheFirstNElementsRemoved should be(List(5, 6))
  }

  it should "remove no elements from the list when there is no element that match the predicate" in {
    val myList = List(3, 4, 5, 6)
    val myListWithTheFirstNElementsRemoved = List.dropWhile(myList, (x: Int) => x > 20)
    myListWithTheFirstNElementsRemoved should be(List(3, 4, 5, 6))
  }

  it should "remove no elements from an empty list" in {
    val myList = List()
    val myListWithTheFirstNElementsRemoved = List.dropWhile(myList, (x: Int) => x > 20)
    myListWithTheFirstNElementsRemoved should be(List())
  }

  it should "remove all but the last element of the list" in {
    val myList = List(3, 5, 8, 9)
    val myListWithJustTheLastElement = List.init(myList)
    myListWithJustTheLastElement should be(List(9))
  }

  it should "remove no elements from an empty list after calling init" in {
    val myList = List()
    val myListWithJustTheLastElement = List.init(myList)
    myListWithJustTheLastElement should be(List())
  }

  it should "compute the lenght of a list using foldright" in {
    val myIntegerList = List(100, 23, 34, 90, 25)
    List.length(myIntegerList) should be(5)

    val myStringList = List("string1")
    List.length(myStringList) should be(1)
  }

  it should "compute the foldleft properly" in {
    val myIntegerList = List(3, 4, 7)
    List.foldLeft(myIntegerList, 0)(_ + _) should be(14)

    val myIntegerList2 = List(3, 4, 2)
    List.foldLeft(myIntegerList2, 1)(_ * _) should be(24)
  }
}
