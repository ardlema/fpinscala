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
    listWithElementsLessThanFive should contain theSameElementsAs(List(1,2,3,4))
  }

  it should "implement the drop while function" in {
    val dropFunction = dropWhile[Int](e => e < 4)
    val listWithElementsLessThanFive = dropFunction(Stream(1,2,3,4,5,6)).toList

    listWithElementsLessThanFive.size shouldBe(3)
    listWithElementsLessThanFive should contain theSameElementsAs(List(4,5,6))
  }
}
