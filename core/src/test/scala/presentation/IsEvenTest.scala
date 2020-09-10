package presentation

import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class IsEvenTest extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {

  def isEven(x: Int): Boolean = x % 2 == 0

  test("even returns true")(
     forAll((x: Int) => assert(isEven(2 * x) == true))
  )

  test("odd returns false")(
    forAll((x: Int) => assert(isEven(2 * x + 1) == false))
  )

}
