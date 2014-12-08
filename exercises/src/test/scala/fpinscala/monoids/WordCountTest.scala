package fpinscala.monoids

import fpinscala.monoids.Monoid._
import org.scalatest._

class WordCountTest
  extends FlatSpec
  with Matchers {

  "The WC monoid" should "meet the monoid laws" in {
    val wcMonoid1 = wcMonoid.op(Stub("hola"), Stub("adios"))
    wcMonoid1 should be(Stub("holaadios"))

    val wcMonoid2 = wcMonoid.op(Stub(""), Part("lorem", 1, "do"))
    wcMonoid2 should be(Part("lorem", 1, "do"))

    val wcMonoid3 = wcMonoid.op(Part("lorem", 0, ""), Part("", 0, "do"))
    wcMonoid2 should be(Part("lorem", 1, "do"))
  }

  it should "count the number of words in a String" in {
    val stringToBeCounted = "this is the string to be counted"
    count(stringToBeCounted) should be(7)
  }
}
