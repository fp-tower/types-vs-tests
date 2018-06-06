package presentation

import org.scalacheck.{Arbitrary, Gen}

sealed trait Country

case object UnitedKingdom extends Country
case object France extends Country

object Country {
  implicit val arbCountry: Arbitrary[Country] =
    Arbitrary(Gen.oneOf(UnitedKingdom, France))
}

sealed trait DialCode

object _33 extends DialCode
object _44 extends DialCode

object DialCode {

  def getDialCode(country: Country): DialCode = country match {
    case UnitedKingdom => _44
    case France        => _33
  }

  def getCountry(dialCode: DialCode): Country = dialCode match {
    case _33 => France
    case _44 => France
  }

}
