import sbt.librarymanagement.{VersionNumber, VersionNumberCompatibility}

object CompatibleJavaVersion extends VersionNumberCompatibility {
  def name = "Java specification compatibility"
  def isCompatible(current: VersionNumber, required: VersionNumber): Boolean =
    current.numbers.zip(required.numbers).foldRight(required.numbers.size<=current.numbers.size)((a,b) => (a._1 > a._2) || (a._1==a._2 && b))
  def apply(current: VersionNumber, required: VersionNumber): Boolean = isCompatible(current, required)
}