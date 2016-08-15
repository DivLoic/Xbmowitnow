
name := "mowitnow"

version := "1.0"

scalaVersion := "2.11.8"

mainClass in (Compile, run) := Some("org.ldivad.Main")

libraryDependencies ++= Seq(

  "com.typesafe" % "config" % "1.2.1",
  "org.scalactic" %% "scalactic" % "3.0.0",
  "org.scalatest" % "scalatest_2.11" % "3.0.0",

  "ch.qos.logback" %  "logback-classic" % "1.1.7",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0"

)

parallelExecution in Test := false