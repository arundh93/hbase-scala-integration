
name := "scala-hbase-test"
version := "1.0"
scalaVersion := "2.11.12"
//resolvers += "Thrift" at "http://people.apache.org/~rawson/repo/"
resolvers += "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/"

libraryDependencies ++= Seq(
  "org.apache.hadoop" % "hadoop-common" % "2.6.0-cdh5.15.0",
  "org.apache.hbase" % "hbase-client" % "1.1.1",
  "org.apache.hbase" % "hbase-common" % "1.1.1",
  "org.apache.hbase" % "hbase-server" % "1.1.1",
  "org.apache.spark" %% "spark-core" % "2.3.1" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.3.1" % "provided",
  "com.hortonworks" % "shc-core" % "1.1.1-2.1-s_2.11"
)

assemblyMergeStrategy in assembly := {
  case PathList("javax",  xs @ _*)         => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("org.apache.commons.**" -> "shadeio.@1").inLibrary("commons-beanutils" % "commons-beanutils" % "1.7.0"),
  ShadeRule.rename("org.apache.commons.**" -> "shade.@1").inLibrary("commons-collections" % "commons-collections" % "3.2.2"),
  ShadeRule.rename("org.apache.jasper.**" -> "shade123.@1").inLibrary("tomcat" % "jasper-runtime" % "5.5.23"),
  ShadeRule.rename("org.apache.jasper.**" -> "shade1.@1").inLibrary("tomcat" % "jasper-compiler" % "5.5.23")

)