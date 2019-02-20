import Dependencies._

name := "starter_counts"

organization := "com.dmp"

version := "0.1"

scalaVersion := "2.12.8"

scalacOptions ++= Seq("-optimize", "-deprecation", "-feature")

libraryDependencies ++= Dependencies.starter_counts

git.baseVersion := "0.1"

//versionWithGit

showCurrentGitBranch

scalariformSettings

mainClass in assembly := Some("")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case PathList(xs @ _*) => MergeStrategy.first
}

assemblyJarName in assembly := name.value + "-" + version.value + ".jar"