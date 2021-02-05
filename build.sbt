import Dependencies._
import sbt.Keys.libraryDependencies

name := "kanboard4s"

version := "0.1"

scalaVersion := scalaV

lazy val commonSettings = Seq(
  scalaVersion := scalaV
)

lazy val gen = (project in file("modules/gen"))
  .settings(commonSettings)
  .settings (
    idePackagePrefix := Some("io.razem.kanboard4s"),
    libraryDependencies ++= Seq(
      "com.github.pathikrit" %% "better-files" % "3.9.1",
      "com.lihaoyi" %% "upickle" % "0.9.5"
    )
  )

lazy val lib = (project in file("modules/lib"))
  .settings(commonSettings)
  .settings (
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "upickle" % "0.9.5"
    )
  )

lazy val test = (project in file("modules/test"))
  .settings(commonSettings)
  .settings (
    Test / fork := true,
    libraryDependencies ++= Seq(
      "com.squareup.okhttp3" % "okhttp" % "3.14.9",
      "com.lihaoyi" %% "upickle" % "0.9.5",
      "org.scalatest" %% "scalatest" % "3.2.2",
      "com.dimafeng" %% "testcontainers-scala-scalatest" % "0.39.0"
    ).map(_ % "test")
  )
  .dependsOn(lib)