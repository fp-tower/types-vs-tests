package presentation

import eu.timepit.refined.W
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Less
import eu.timepit.refined.scalacheck.numeric._
import org.scalatest.{FreeSpec, Matchers}
import org.scalatest.prop.Checkers

class IncrementTest extends FreeSpec with Checkers with Matchers {

  def increment(x: Int): Int = x + 1

  "increment(x) > x" in check((x: Int) => increment(x) > x)

  final val maxInt = Int.MaxValue

  "∀ x < MaxInt, f(x) > x" in check((x: Int Refined Less[W.`maxInt`.T]) =>
    increment(x.value) > x.value
  )

  "∀ x - MaxInt, f(x) > x" in check((x: Int) =>
    x == Int.MaxValue || increment(x) > x
  )

  "increment(0) == 1" in {
    assert(increment(0) == 1)
  }

  "increment is injective (one-to-one)" in check((x: Int, y: Int) =>
    increment(x) != increment(y) || x == y
  )

}
