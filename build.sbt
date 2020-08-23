
lazy val baseSettings: Seq[Setting[_]] = Seq(
  scalaVersion := "2.13.3",
  scalacOptions     ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions", "-language:existentials",
    "-unchecked",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
  ),
  libraryDependencies ++= Seq(
    "org.typelevel"     %% "cats-core"          % "2.1.1",
    "eu.timepit"        %% "refined"            % "0.9.15",
    "eu.timepit"        %% "refined-scalacheck" % "0.9.15",
    "org.scalatestplus" %% "scalacheck-1-14"    % "3.1.2.0" % Test,
  ),
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
    mdocIn := baseDirectory.value / "mdoc",
    mdocOut := baseDirectory.value / "docs",
  ).dependsOn(core)
  .enablePlugins(MdocPlugin)
