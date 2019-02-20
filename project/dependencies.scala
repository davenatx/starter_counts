import sbt._

object Version {
  val scalaLogging = "3.9.2"
  val logback = "1.2.3"
  val config = "1.3.3"
  val scalaCsv = "1.3.5"  
}

object Library { 
  val scalaLogging  =  "com.typesafe.scala-logging" %% "scala-logging"  % Version.scalaLogging
  val logback       = "ch.qos.logback"              % "logback-classic" % Version.logback
  val config        = "com.typesafe"                % "config"          % Version.config
  val scalaCsv  = "com.github.tototoshi" %% "scala-csv" % Version.scalaCsv
 }

object Dependencies {
  import Library._

  val starter_counts = List(
    scalaLogging,
    logback,
    config,
	scalaCsv
  )
}