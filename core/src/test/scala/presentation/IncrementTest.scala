package presentation

import eu.timepit.refined.W
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Less
import eu.timepit.refined.scalacheck.numeric._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class IncrementTest extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {

  def increment(x: Int): Int = x + 1

  test("∀ x - MaxInt, f(x) > x") {
    forAll((x: Int) =>
      assert(increment(x) > x || x == Int.MaxValue)
    )
  }

  final val maxInt = Int.MaxValue

  test("∀ x - MaxInt, f(x) > x with refined") {
    forAll((x: Int Refined Less[W.`maxInt`.T]) =>
      assert(increment(x.value) > x.value)
    )
  }

  test("increment(0) == 1") {
    assert(increment(0) == 1)
  }

  test("increment is injective (one-to-one)") (
    forAll((x: Int, y: Int) =>  increment(x) != increment(y) || x == y)
  )

}
