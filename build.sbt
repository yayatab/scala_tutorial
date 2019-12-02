name := "sandbox"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "com.google.guava"  % "guava" % "21.0",
  "com.github.scopt" %% "scopt" % "4.0.0-RC2",
  "org.scalatest"    %% "scalatest" % "3.0.8" % "test"

)

//libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"