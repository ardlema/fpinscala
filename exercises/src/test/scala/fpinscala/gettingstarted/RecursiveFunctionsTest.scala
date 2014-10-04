package fpinscala.gettingstarted

import org.scalatest._

class RecursiveFunctionsTest extends FlatSpec with Matchers {

  "The Fibonacci method" should "return the fibonacci sequence" in {
    val fibonacci1 = MyModule.fib(0)
    fibonacci1 should be (0)
  }

}
