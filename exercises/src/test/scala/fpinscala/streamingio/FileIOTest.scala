package fpinscala.streamingio

import java.io.File
import java.util.concurrent.Executors

import fpinscala.iomonad._
import fpinscala.streamingio.SimpleStreamTransducers.Process._
import org.scalatest.{FlatSpec, Matchers}


class FileIOTest extends FlatSpec with Matchers {
  implicit val executorService = Executors.newFixedThreadPool(2)
  val classLoader = getClass().getClassLoader()
  val file = new File(classLoader.getResource("temperatures.txt").getFile())

  "The processfile function" should "find out where a file contains more than x lines" in {
    val ioBoolean = processFile(file, count |> exists(_ > 2), false)(_ || _)
    val result = unsafePerformIO(ioBoolean)

    result shouldBe(true)
  }

  it should "find out where a file contains fewer than x lines" in {
    val ioBoolean = processFile(file, count |> exists(_ > 10), false)(_ || _)
    val result = unsafePerformIO(ioBoolean)

    result shouldBe(false)
  }
}

