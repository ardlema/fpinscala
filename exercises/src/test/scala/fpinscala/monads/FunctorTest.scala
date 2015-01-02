package fpinscala.monads

import org.scalatest._

class MonoidTest extends FlatSpec with Matchers {

  val myListFunctor = Functor.listFunctor

  "The Functor trait" should "implement the distribute method" in {
    val distributedList = myListFunctor.distribute(List((1, "hola"), (2, "adios")))

    distributedList should be(List(1, 2), List("hola", "adios"))
  }

  it should "implement codistribute method" in {
    val codistributedList = myListFunctor.codistribute(Left(List(1, 2)))

    codistributedList should be(List(Left(1), Left(2)))
  }
}
