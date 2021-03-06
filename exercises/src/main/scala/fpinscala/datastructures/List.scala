package fpinscala.datastructures

import scala._
import fpinscala.datastructures.Cons
import fpinscala.datastructures.Cons
import fpinscala.datastructures.Cons
import fpinscala.datastructures.Cons

sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
case class Cons[+A](head: A, tail: List[A]) extends List[A] // Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`, which may be `Nil` or another `Cons`.

object List { // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match { // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x,xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  } 
  
  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }
  
  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42 
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101 
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }
  
  def sum2(ns: List[Int]) = 
    foldRight(ns, 0)((x,y) => x + y)


  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar

  def tail[A](l: List[A]): List[A] = {
    l match {
      case Nil => Nil
      case Cons(_, xs) => xs
    }
  }

  def setHead[A](l: List[A], h: A): List[A] = {
    l match {
      case Nil => sys.error("trying to set the head over an empty list")
      case Cons(_, xs) => Cons(h, xs)
    }
  }

  def drop[A](l: List[A], n: Int): List[A] = {
    if (n <= 0) l
    else l match {
      case Nil => Nil
      case Cons(_,t) => drop(t, n-1)
    }
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
    l match {
      case Nil => Nil
      case Cons(head, tail) => {
        if (f(head)) dropWhile(tail, f)
        else l
      }
    }
  }

  def init[A](l: List[A]): List[A] = {
    l match {
      case Nil => Nil
      case Cons(head, Nil) => l
      case Cons(head, tail) => init(tail)
    }
  }

  def length[A](l: List[A]): Int = foldRight(l, 0)((_,accumulator) => accumulator + 1)

  @annotation.tailrec
  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = {
    l match {
      case Nil => z
      case Cons(head, tail) => foldLeft(tail, f(z, head))(f)
    }
  }

  def sumWithFoldLeft(ns: List[Int]): Int = foldLeft(ns, 0)(_ + _)

  def productWithFoldLeft(ns: List[Double]): Double = foldLeft(ns, 1.0)(_ * _)

  def lengthWithFoldLeft[A](l: List[A]): Int = foldLeft(l, 0)((acc, _) => acc + 1)

  def reverse[A](l: List[A]): List[A] = foldLeft(l, List[A]())((b, a) => Cons(a, b))

  def foldRightViaFoldLeft[A,B](l: List[A], z: B)(f: (A,B) => B): B = foldLeft(reverse(l), z)((b, a) => f(a,b))

  def foldLeftViaFoldRight[A,B](l: List[A], z: B)(f: (B, A) => B): B = foldRight(l, z)((a, b) => f(b,a))

  def appendViaFoldRight[A](a1: List[A], a2: List[A]): List[A] = foldRight(a1, a2)((elem, list) => Cons(elem, list))

  def concat[A](l: List[List[A]]): List[A] = foldRight(l, List[A]())((a,b) => appendViaFoldRight(a, b))

  def add1(l: List[Int]): List[Int] = foldRight(l, List[Int]())((elem, tail) => Cons(elem+1, tail))

  def convertDoubleToString(l: List[Double]): List[String] = foldRight(l, List[String]())((elem, tail) => Cons(elem.toString, tail))

  def map[A,B](l: List[A])(f: A => B): List[B] = foldRightViaFoldLeft(l, List[B]())((elem, tail) => Cons(f(elem), tail))

  def filter[A](as: List[A])(f: A => Boolean): List[A] = foldRight(as, List[A]())((elem, tail) => if (f(elem)) Cons(elem, tail) else tail)

  def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] = concat(map(as)(f))

  def filterViaFlatMap[A](l: List[A])(f: A => Boolean): List[A] = flatMap(l)(a => if (f(a)) List(a) else Nil)

  def addPairwise(a: List[Int], b: List[Int]): List[Int] = (a, b) match {
      case (Cons(headA, Nil), Cons(headB, Nil)) => Cons(headA + headB, Nil)
      case (Cons(headA, tailA), Cons(headB, tailB)) => appendViaFoldRight(Cons(headA+headB, Nil), addPairwise(tailA, tailB))
      case (Nil, _) => Nil
      case (_, Nil) => Nil
  }

  def zipWith[A,B,C](a: List[A], b: List[B])(f: (A,B) => C): List[C] = (a, b) match {
    case (Cons(headA, Nil), Cons(headB, Nil)) => Cons(f(headA, headB), Nil)
    case (Cons(headA, tailA), Cons(headB, tailB)) => appendViaFoldRight(Cons(f(headA, headB), Nil), zipWith(tailA, tailB)(f))
    case (Nil, _) => Nil
    case (_, Nil) => Nil
  }

  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = (sup, sub) match {
    case (_, Nil) => true
    case (Cons(head1, tail1), Cons(head2, tail2)) => {if (head1 == head2) hasSubsequence(tail1, tail2)
                                                      else (hasSubsequence(tail1, Cons(head2, tail2)))}
    case (Nil, _) => false
  }
}
