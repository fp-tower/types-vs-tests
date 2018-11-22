package presentation

import org.scalatest.FreeSpec
import org.scalatest.prop.Checkers

class DialCodeTest extends FreeSpec with Checkers {

  import DialCode._

  "round trip" ignore check((x: Country) =>
    getCountry(getDialCode(x)) == x
  )

}