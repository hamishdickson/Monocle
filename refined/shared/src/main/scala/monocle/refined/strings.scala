package monocle.refined

import eu.timepit.refined.api.Refined
import eu.timepit.refined.string.{StartsWith, EndsWith}
import monocle._

object strings extends StringsInstances

trait StringsInstances {

  def startsWith(prefix: String): Prism[String, StartsWithString[prefix.type]] = {
    Prism.partial[String, Refined[String, StartsWith[prefix.type]]] {
      case string if string.startsWith(prefix) => Refined.unsafeApply(string)
    }{_.value}
  }

  def endsWith(suffix: String): Prism[String, EndsWithString[suffix.type]] = {
    Prism.partial[String, Refined[String, EndsWith[suffix.type]]] {
      case string if string.endsWith(suffix) => Refined.unsafeApply(string)
    }{_.value}
  }

}
