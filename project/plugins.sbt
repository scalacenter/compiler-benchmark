// Comment to get more information during initialization
logLevel := Level.Warn

// sbt-jmh plugin - pulls in JMH dependencies too
addSbtPlugin("pl.project13.scala" % "sbt-jmh" % "0.3.3")
addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.14")

// Enable this for the bloop build to work
addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.1")

val typesafeConfig = "com.typesafe" % "config" % "1.3.2"
val metaconfigCore = "com.geirsson" %% "metaconfig-core" % "0.6.0"
val metaconfigConfig = "com.geirsson" %% "metaconfig-typesafe-config" % "0.6.0"
val metaconfigDocs = "com.geirsson" %% "metaconfig-docs" % "0.6.0"
val circeDerivation = "io.circe" %% "circe-derivation" % "0.9.0-M3"
val circeParser = "io.circe" %% "circe-parser" % "0.9.0"
// required for java9+
val javaxActivation = "com.sun.activation" % "javax.activation" % "1.2.0"

// Let's add our sbt plugin to the sbt too ;)
unmanagedSourceDirectories in Compile ++= {
  val baseDir = baseDirectory.value.getParentFile.getParentFile
  val integrationsMainDir = baseDir / "integrations"
  if (!integrationsMainDir.exists()) Nil
  else {
    val pluginMainDir = integrationsMainDir / "sbt-bloop" / "src" / "main"
    List(
      baseDir / "config" / "src" / "main" / "scala",
      baseDir / "config" / "src" / "main" / "scala-2.11-12",
      pluginMainDir / "scala",
      pluginMainDir / s"scala-sbt-${Keys.sbtBinaryVersion.value}"
    )
  }
}

libraryDependencies ++= List(
  typesafeConfig,
  metaconfigCore,
  metaconfigDocs,
  metaconfigConfig,
  circeDerivation,
  circeParser,
  javaxActivation
)
