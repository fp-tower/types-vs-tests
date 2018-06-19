package presentation

import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.{Greater, Positive}
import shapeless.Nat._18

case class Person(name: String, age: Int) {
  require(age >= 0, "age must be positive")
}

case class Person_v2(name: String, age: Int Refined Positive)

case class Person_v3[I](name: String, age: Int Refined I)

object Person_v3 {
  type DbPerson = Person_v3[Int]
  type Person   = Person_v3[Int Refined Positive]
  type Adult    = Person_v3[Int Refined Greater[_18]]
}
