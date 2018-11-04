package presentation

import org.scalatest.prop.Checkers
import org.scalatest.{FreeSpec, Matchers}

class IsEvenTest extends FreeSpec with Checkers with Matchers {

  def isEven(x: Int): Boolean = x % 2 == 0

  "even returns true" in check((x: Int) =>
    isEven(2 * x) == true
  )

  "odd returns false" in check((x: Int) =>
    isEven(2 * x + 1) == false
  )

}
