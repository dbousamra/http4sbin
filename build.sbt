import sbt.Keys._

name := "http4s"

version := "0.0.1"

scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8", "-language:postfixOps", "-Xfatal-warnings")

lazy val http4sVersion = "0.14.10"

lazy val scalazVersion = "7.1.7"

mainClass := Some("com.dbousamra.http4sbin.http.Boot")

libraryDependencies ++= Seq(
  "org.log4s"      %% "log4s"               % "1.3.2",
  "org.slf4j"      %  "slf4j-log4j12"       % "1.7.12",
  "com.lihaoyi"    %% "scalatags"           % "0.6.1",
  "io.argonaut"    %% "argonaut"            % "6.1",
  "org.http4s"     %% "http4s-dsl"          % http4sVersion,
  "org.http4s"     %% "http4s-argonaut"     % http4sVersion,
  "org.http4s"     %% "http4s-blaze-server" % http4sVersion,
  "org.scalaz"     %% "scalaz-core"         % scalazVersion,
  "org.scalaz"     %% "scalaz-concurrent"   % scalazVersion,
  "org.scalatest"  %% "scalatest"           % "3.0.0"  % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck"          % "1.13.3" % "test" withSources() withJavadoc()
)