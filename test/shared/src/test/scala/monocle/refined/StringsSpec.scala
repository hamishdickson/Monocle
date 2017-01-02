package monocle.refined

import monocle.MonocleSuite
import monocle.law.discipline.PrismTests
import org.scalacheck.Cogen
import eu.timepit.refined.scalacheck.string.{startsWithArbitrary, endsWithArbitrary}
import eu.timepit.refined._


import scalaz.Equal


class StringsSpec extends MonocleSuite {

  implicit val startsWithCoGen: Cogen[StartsWithString[W.`"hello"`.T]] = Cogen[String].contramap[StartsWithString[W.`"hello"`.T]](_.value)
  implicit val eqStartsWith: Equal[StartsWithString[W.`"hello"`.T]] = Equal.equalA[StartsWithString[W.`"hello"`.T]]

  implicit val endsWithCoGen: Cogen[EndsWithString[W.`"bye"`.T]] = Cogen[String].contramap[EndsWithString[W.`"bye"`.T]](_.value)
  implicit val eqEndsWith: Equal[EndsWithString[W.`"bye"`.T]] = Equal.equalA[EndsWithString[W.`"bye"`.T]]

  implicit val eqString: Equal[String] = Equal.equalA[String]

  checkAll("starts with", PrismTests(startsWith("hello")))
  checkAll("ends with", PrismTests(endsWith("bye")))

}
