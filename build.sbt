//import NativePackagerHelper._
name := "sandbox"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(

    "org.apache.kafka" % "kafka-streams" % "2.1.1",
    "org.apache.kafka" % "kafka-clients" % "2.1.1",
    "com.google.guava" % "guava" % "21.0",
    "org.scalatest" %% "scalatest" % "3.0.8" % "test"
  )
//libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"