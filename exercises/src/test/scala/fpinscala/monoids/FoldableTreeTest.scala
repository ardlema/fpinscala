package fpinscala.monoids

import fpinscala.monoids.Monoid._
import org.scalatest._

class FoldableTreeTest
  extends FlatSpec
  with Matchers {

  val leftLeaf = Leaf(4)
  val rightLeaf = Leaf(3)
  val root = Branch(leftLeaf, rightLeaf)

  "The Foldable[Tree]" should "implement foldRight method" in {
    val sumOfTree = TreeFoldable.foldRight(root)(0)(_ + _)
    sumOfTree should be(7)
  }

  /*it should "implement foldLeft method" in {
    val sumOfTree = TreeFoldable.foldLeft(root)(0)(_ + _)
    sumOfTree should be(7)
  }

  it should "implement foldMap method" in {
    val myList = List(1.0,2.0,3.0)
    val myContatenatedDoubleList = TreeFoldable.foldMap(myList)(elem => elem.toString)(stringMonoid)
    myContatenatedDoubleList should be("1.02.03.0")
  }*/
}
