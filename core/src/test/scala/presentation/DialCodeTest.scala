package presentation

import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class DialCodeTest extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {

  import DialCode._

  ignore("round trip")(
      forAll((x: Country) =>  getCountry(getDialCode(x)) == x)
  )

}