import sbt.Keys._

name := "http4sbin"

version := "0.0.1"

scalaVersion := "2.12.3"

scalacOptions := Seq(
  "-unchecked",
  "-feature",
  "-deprecation",
  "-encoding",
  "utf8",
  "-language:postfixOps",
  "-language:higherKinds",
  "-Xfatal-warnings"
)

lazy val http4sVersion = "0.18.0-M1"

mainClass := Some("com.dbousamra.http4sbin.Boot")

libraryDependencies ++= Seq(
  "org.log4s"   %% "log4s"               % "1.3.6",
  "org.slf4j"   % "slf4j-log4j12"        % "1.7.25",
  "com.lihaoyi" %% "scalatags"           % "0.6.3",
  "io.circe"    %% "circe-core"          % "0.9.0-M1",
  "org.http4s"  %% "http4s-dsl"          % http4sVersion,
  "org.http4s"  %% "http4s-circe"        % http4sVersion,
  "org.http4s"  %% "http4s-blaze-server" % http4sVersion
)

enablePlugins(JavaAppPackaging)
