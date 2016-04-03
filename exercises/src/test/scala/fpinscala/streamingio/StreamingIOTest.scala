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
}
