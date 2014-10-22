package fpinscala.datastructures

import org.scalatest.{Matchers, FlatSpec}

class TreeTest extends FlatSpec with Matchers {
  val leaf1 = Leaf(1)
  val leaf2 = Leaf(2)
  val leaf3 = Leaf(3)
  val leaf4 = Leaf(4)
  val leftChildBranch = Branch(leaf1, leaf2)
  val rightChildBranch = Branch(leaf3, leaf4)
  val tree = Branch(leftChildBranch, rightChildBranch)


  "Our tree implementation " should "count the number of elements" in {
    val numberOfNodes = Tree.numberOfNodes(tree)
    numberOfNodes should be(7)
  }

  it should "returns the maximum element in a Tree" in {
    val maximumNumber = Tree.maximum(tree)
    maximumNumber should be(4)
  }
}
