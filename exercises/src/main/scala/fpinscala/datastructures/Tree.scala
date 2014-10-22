package fpinscala.datastructures

sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]


object Tree {
  def numberOfNodes[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 1
    case Branch(left, right) => 1 + numberOfNodes(left) + numberOfNodes(right)
  }

  def maximum(tree: Tree[Int]): Int = {
    def findOutMaximum(tree: Tree[Int], maximum: Int): Int = {
      tree match {
        case Leaf(element) => element.max(maximum)
        case Branch(left, right) => maximum.max(findOutMaximum(left, maximum)).max(findOutMaximum(right, maximum))
      }
    }

    findOutMaximum(tree, 0)
  }
}