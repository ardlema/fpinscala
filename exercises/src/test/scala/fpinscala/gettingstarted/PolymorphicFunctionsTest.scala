package fpinscala.gettingstarted

import java.util

import org.scalatest._

class PolymorphicFunctionsTest extends FlatSpec with Matchers {

  "The polymorphic isOrdered method" should "return true for empty list" in {
    val sorted = PolymorphicFunctions.isSorted(Array(), (a: Int, b: Int) => a < b)
    sorted should be (true)
  }

  it should "return true for a one-element array" in {
    val integersArray = Array(1)
    val sorted = PolymorphicFunctions.isSorted(integersArray, (a: Int, b: Int) => a < b)
    sorted should be (true)
  }

  it should "return true for ascending ordered lists" in {
    val integersArray = Array(1, 4, 8, 19)
    val sorted = PolymorphicFunctions.isSorted(integersArray, (a: Int, b: Int) => a < b)
    sorted should be (true)
  }

  it should "return true for descending ordered lists" in {
    val integersArray = Array(20, 15, 10, 5)
    val sorted = PolymorphicFunctions.isSorted(integersArray, (a: Int, b: Int) => a > b)
    sorted should be (true)
  }

  it should "return false for not ordered lists" in {
    val integersArray = Array(1, 4, 8, 2)
    val sorted = PolymorphicFunctions.isSorted(integersArray, (a: Int, b: Int) => a < b)
    sorted should be (false)
  }
}
