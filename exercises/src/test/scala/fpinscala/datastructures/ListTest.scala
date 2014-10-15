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

  it should "add the elements of the list using foldleft" in {
    val myIntegerList = List(3, 4, 7)
    List.sumWithFoldLeft(myIntegerList) should be(14)
  }

  it should "compute the product of the list using foldleft" in {
    val myDoubleList = List(2.0, 4.0, 7.0)
    List.productWithFoldLeft(myDoubleList) should be(56)
  }

  it should "compute the list of a list using foldleft" in {
    val myDoubleList = List(2.0, 4.0, 7.0)
    List.lengthWithFoldLeft(myDoubleList) should be(3)

    val myStringList = List("string1", "string2")
    List.lengthWithFoldLeft(myStringList) should be(2)
  }

  it should "return the reverse of a list" in {
    val myIntegerList = List(1, 2, 3)
    List.reverse(myIntegerList) should be(List(3, 2, 1))
  }

  it should "implement foldright via foldleft" in {
    val myIntegerList = List(1, 2, 3)
    val result = List.foldRightViaFoldLeft(myIntegerList, 0)(_ + _)
    result should be(6)

    val myDoubleList = List(3.0, 2.0, 3.0)
    val result2 = List.foldRightViaFoldLeft(myDoubleList, 1.0)(_ * _)
    result2 should be(18)
  }

  it should "implement foldleft via foldright" in {
    val myIntegerList = List(1, 2, 3)
    val result = List.foldLeftViaFoldRight(myIntegerList, 0)(_ + _)
    result should be(6)

    val myDoubleList = List(3.0, 2.0, 3.0)
    val result2 = List.foldLeftViaFoldRight(myDoubleList, 1.0)(_ * _)
    result2 should be(18)
  }

  it should "append one list to another" in {
    val myIntegerList = List(1, 2, 3)
    val anotherIntegerList = List(4, 5, 6)
    val appendedList = List.appendViaFoldRight(myIntegerList, anotherIntegerList)

    appendedList should be(List(1, 2, 3, 4, 5, 6))
  }

  it should "concatenate a list of lists into a single list" in {
    val myIntegerList = List(1, 2, 3)
    val anotherIntegerList = List(4, 5, 6)
    val concatenatedList = List(myIntegerList, anotherIntegerList)
    val appendedList = List.concat(concatenatedList)

    val expectedList = List(1, 2, 3, 4, 5, 6)
    appendedList should be(expectedList)
  }

  it should "add 1 to a list of integers" in {
    val myIntegerList = List(1, 2, 3)
    val myIntegerListPlusOne = List.add1(myIntegerList)

    myIntegerListPlusOne should be(List(2, 3, 4))
  }

  it should "convert a double list to string list" in {
    val myDoubleList = List(1.0, 2.0, 3.0)
    val myStringList = List.convertDoubleToString(myDoubleList)

    myStringList should be(List("1.0", "2.0", "3.0"))
  }

  it should "apply map method to list" in {
    val myDoubleList = List(1.0, 2.0, 3.0)
    val myStringList = List.map(myDoubleList)(a => a * 2)

    myStringList should be(List(2.0, 4.0, 6.0))
  }

  it should "filter elements from a list" in {
    val myIntegerList = List(1, 2, 3, 4, 5)
    val myFilteredIntegerList = List.filter(myIntegerList)(a => (a % 2 != 0))

    myFilteredIntegerList should be(List(1, 3, 5))
  }

  it should "apply the flatMap function to a list" in {
    val myIntegerList = List(1, 2, 3)
    val myFilteredIntegerList = List.flatMap(myIntegerList)(i => List(i,i))

    myFilteredIntegerList should be(List(1, 1, 2, 2, 3, 3))
  }

  it should "filter elements from a list using flatMap" in {
    val myIntegerList = List(1, 2, 3, 4, 5)
    val myFilteredIntegerList = List.filterViaFlatMap(myIntegerList)(a => (a % 2 != 0))

    myFilteredIntegerList should be(List(1, 3, 5))
  }

  it should "construct a new list by adding corresponding elements" in {
    val myIntegerList1 = List(1, 2, 3)
    val myIntegerList2 = List(4, 5, 6)
    val expectedIntegerList = List(5, 7, 9)
    val myZippedIntegerList = List.addPairwise(myIntegerList1, myIntegerList2)

    myZippedIntegerList should be(expectedIntegerList)
  }

}
