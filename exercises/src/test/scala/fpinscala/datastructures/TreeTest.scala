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

  it should "returns the maximum depth in a Tree" in {
    val leaf1 = Leaf(1)
    val leaf2 = Leaf(2)
    val leaf3 = Leaf(3)
    val leaf5 = Leaf(5)
    val leaf6 = Leaf(6)
    val rightChildBranch2 = Branch(leaf5, leaf6)
    val rightChildBranch1 = Branch(leaf3, rightChildBranch2)
    val leftChildBranch = Branch(leaf1, leaf2)
    val tree = Branch(leftChildBranch, rightChildBranch1)
    val depth = Tree.depth(tree)
    depth should be(3)
  }

  it should "apply the function passed to all the elements of the Tree" in {
    val treePlusOne = Tree.map(tree)(element => element * 2)
    treePlusOne match {
      case Leaf(_) => fail()
      case Branch(left, right) => (left, right) match {
        case (Leaf(_), Leaf(_)) => fail()
        case (Branch(leaf1, leaf2), Branch(leaf3, leaf4)) => {
          leaf1 should be(Leaf(2))
          leaf2 should be(Leaf(4))
          leaf3 should be(Leaf(6))
          leaf4 should be(Leaf(8))
        }
      }
    }
  }
}
