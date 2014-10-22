package fpinscala.datastructures

import org.scalatest.{Matchers, FlatSpec}

class TreeTest extends FlatSpec with Matchers {

  "Our tree implementation " should "count the number of elements" in {
    val leaf1 = Leaf(1)
    val leaf2 = Leaf(2)
    val leaf3 = Leaf(3)
    val leaf4 = Leaf(4)
    val leftChildBranch = Branch(leaf1, leaf2)
    val rightChildBranch = Branch(leaf3, leaf4)
    val root = Branch(leftChildBranch, rightChildBranch)

    val numberOfNodes = Tree.numberOfNodes(root)
    numberOfNodes should be(7)
  }

  /*it should "return and empty list when the list has one element" in {
    val myList = List(1)
    val myListWithoutFirstElement = List.tail(myList)
    myListWithoutFirstElement should be(List())
  }*/
}
