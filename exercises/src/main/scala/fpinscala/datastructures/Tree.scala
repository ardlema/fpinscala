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

  def depth[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 0
    case Branch(left, right) => 1 + (depth(left) max depth(right))
  }

  def map[A,B](t: Tree[A])(f: A => B): Tree[B] = {
    t match {
      case Leaf(element: A) => Leaf(f(element))
      case Branch(left, right) => Branch(map(left)(f), map(right)(f))
    }
  }

  def fold[A,B](t: Tree[A])(f: A => B)(g: (B,B) => B): B = t match {
      case Leaf(element: A) => f(element)
      case Branch(left, right) => g(fold(left)(f)(g), fold(right)(f)(g))
  }

  def numberOfNodesWithFold[A](tree: Tree[A]): Int = fold(tree)(e => 1)((a,b) => 1 + a + b)

  def maximumWithFold(tree: Tree[Int]): Int = fold(tree)(e => e)((a,b) => a max b)

  def depthWithFold[A](tree: Tree[A]): Int = fold(tree)(a => 0)((d1,d2) => 1 + (d1 max d2))

  def mapViaFold[A,B](tree: Tree[A])(f: A => B): Tree[B] = fold(tree)(a => Leaf(f(a)): Tree[B])(Branch(_,_))

}