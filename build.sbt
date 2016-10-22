name := "victorops-akka-repl"

val ScalaVersion = "2.11.8"
val AkkaVersion = "2.4.11"
val Ammoniteversion = "0.7.8"

lazy val commonSettings = Seq(
  organization := "victorops",
  version := "1.0.0",
  scalaVersion := ScalaVersion,
  javacOptions in Compile ++= Seq("-source", "1.8", "-target", "1.8"),
  ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }
)

lazy val root = (project in file("."))
  .aggregate(console, counter)

lazy val counter = (project in file("counter"))
  .settings(commonSettings ++ Seq(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
      "com.typesafe.akka" %% "akka-remote" % AkkaVersion,
      "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % "test"
    )
  ):_*)

lazy val console = (project in file("console"))
  .dependsOn(counter)
  .settings(commonSettings ++ Seq(
    libraryDependencies ++= Seq(
      // needed when ammonite is in an sbt module...
      "org.scala-lang" % "scala-library" % ScalaVersion,

      "com.lihaoyi" % "ammonite" % Ammoniteversion cross CrossVersion.full
    )
  ):_*)
