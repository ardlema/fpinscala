package fpinscala.gettingstarted

import org.scalatest._

class RecursiveFunctionsTest extends FlatSpec with Matchers {

  "The Fibonacci method" should "return the fibonacci sequence" in {
    val fibonacci0 = MyModule.fib(0)
    fibonacci0 should be (0)
    val fibonacci1 = MyModule.fib(1)
    fibonacci1 should be (1)
    val fibonacci2 = MyModule.fib(2)
    fibonacci2 should be (1)
    val fibonacci3 = MyModule.fib(3)
    fibonacci3 should be (2)
    val fibonacci4 = MyModule.fib(4)
    fibonacci4 should be (3)
  }

}
