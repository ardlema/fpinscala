package fpinscala.streamingio

import org.scalatest.{FlatSpec, Matchers}
import fpinscala.streamingio.SimpleStreamTransducers.Process._


class StreamingIOTest extends FlatSpec with Matchers {

  "The Process object" should "implement the take function" in {
    val takeFunction = take[String](2)
    val firstTwoElements = takeFunction(Stream("hola", "adios", "amparo")).toList

    firstTwoElements.size shouldBe(2)
    firstTwoElements should contain theSameElementsAs(List("hola", "adios"))
  }

  it should "implement the drop function" in {
    val dropFunction = drop[Int](3)
    val listWithoutThreeFirstElements = dropFunction(Stream(1,2,3,4,5,6)).toList

    listWithoutThreeFirstElements.size shouldBe(3)
    listWithoutThreeFirstElements should contain theSameElementsAs(List(4,5,6))
  }

  it should "implement the take while function" in {
    val dropFunction = takeWhile[Int](e => e < 5)
    val listWithElementsLessThanFive = dropFunction(Stream(1,2,3,4,5,6)).toList

    listWithElementsLessThanFive.size shouldBe(4)
    listWithElementsLessThanFive should contain theSameElementsAs(List(1, 2, 3, 4))
  }

  it should "implement the drop while function" in {
    val dropFunction = dropWhile[Int](e => e < 4)
    val listWithElementsLessThanFive = dropFunction(Stream(1,2,3,4,5,6)).toList

    listWithElementsLessThanFive.size shouldBe(3)
    listWithElementsLessThanFive should contain theSameElementsAs(List(4, 5, 6))
  }

  it should "implement the count function" in {
    val listWithCountElements = count(Stream("a", "b", "c")).toList

    listWithCountElements.size shouldBe(3)
    listWithCountElements should contain theSameElementsAs(List(1, 2, 3))
  }

  it should "implement the mean function" in {
    val listWithMeanElements = mean(Stream(2, 4, 6)).toList

    listWithMeanElements.size shouldBe(3)
    listWithMeanElements should contain theSameElementsAs(List(2.0, 3.0, 4.0))
  }

  it should "implement the sum function in terms of loop" in {
    val listWithSum = sumInTermsOfLoop(Stream(1, 2, 3)).toList

    listWithSum.size shouldBe(3)
    listWithSum should contain theSameElementsAs(List(1.0, 3.0, 6.0))
  }

  it should "implement the count function in terms of loop" in {
    val listWithCount = countInTermsOfLoop(Stream("hello", "house", "car", "tree")).toList

    listWithCount.size shouldBe(4)
    listWithCount should contain theSameElementsAs(List(1, 2, 3, 4))
  }
}
