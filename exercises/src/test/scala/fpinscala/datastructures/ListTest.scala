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

}
