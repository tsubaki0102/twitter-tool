name := "twitter-tool"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "com.danielasfregola" %% "twitter4s"      % "5.5",
  "ch.qos.logback"      % "logback-classic" % "1.2.3"
)

scalafmtOnCompile in ThisBuild := true
