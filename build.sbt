name := "play-java-ebean-example"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.2"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
  
libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += ws
libraryDependencies += "com.h2database" % "h2" % "1.4.194"
libraryDependencies += "com.google.code.gson" % "gson" % "2.6.2"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.18"

libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.1.0" % Test

testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v")
