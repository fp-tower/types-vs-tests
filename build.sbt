lazy val baseSettings: Seq[Setting[_]] = Seq(
  scalaVersion       := "2.12.6",
  scalacOptions     ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions", "-language:existentials",
    "-unchecked",
    "-Yno-adapted-args",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture"
  ),
  libraryDependencies ++= Seq(
    "org.typelevel"  %% "cats-core"  % "1.2.0",
    "eu.timepit"     %% "refined"    % "0.9.2",
    "eu.timepit"     %% "refined-scalacheck" % "0.9.2",
    "org.scalacheck" %% "scalacheck" % "1.14.0",
    "org.scalatest"  %% "scalatest"  % "3.0.5"
  ),
  resolvers += Resolver.sonatypeRepo("releases")
)

lazy val `types-vs-tests` = project.in(file("."))
  .settings(moduleName := "types-vs-tests")
  .settings(baseSettings: _*)
  .aggregate(core, slides)
  .dependsOn(core, slides)

lazy val core = project
  .settings(moduleName := "types-vs-tests-core")
  .settings(baseSettings: _*)


lazy val slides = project
  .settings(moduleName := "types-vs-tests-slides")
  .settings(baseSettings: _*)
  .settings(
    tutSourceDirectory := baseDirectory.value / "tut",
    tutTargetDirectory := baseDirectory.value / "../docs",
    watchSources ++= (tutSourceDirectory.value ** "*.html").get
  ).dependsOn(core)
  .enablePlugins(TutPlugin)
