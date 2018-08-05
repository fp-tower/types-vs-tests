package presentation

import eu.timepit.refined.numeric.Less
import org.scalatest.{FreeSpec, Matchers}
import org.scalatest.prop.Checkers
import eu.timepit.refined.W
import eu.timepit.refined.api.Refined
import eu.timepit.refined.scalacheck.numeric._

class IncrementTest extends FreeSpec with Checkers with Matchers {

  def increment(x: Int): Int = x + 1

  "increment(x) > x" ignore check((x: Int) => increment(x) > x)

  final val maxInt = Int.MaxValue

  "when x not MaxInt, f(x) > x" in check((x: Int Refined Less[W.`maxInt`.T]) =>
    increment(x.value) > x.value
  )

  "increment(0) == 1" in {
    assert(increment(0) == 1)
  }

}
