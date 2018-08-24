
name := "scala-hbase-test"
version := "1.0"
scalaVersion := "2.11.12"
//resolvers += "Thrift" at "http://people.apache.org/~rawson/repo/"
//resolvers += "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/"

libraryDependencies ++= Seq(
  "org.apache.hadoop" % "hadoop-common" % "2.6.0-cdh5.15.0",
  "org.apache.hbase" % "hbase" % "1.2.0-cdh5.15.0",
  "org.apache.hbase" % "hbase-client" % "1.1.1",
  "org.apache.hbase" % "hbase-client" % "1.1.1",
  "org.apache.hbase" % "hbase-common" % "1.1.1",
  "org.apache.hbase" % "hbase-server" % "1.1.1",
  "org.apache.spark" %% "spark-core" % "2.3.1",
  "org.apache.spark" %% "spark-sql" % "2.3.1",
  "com.hortonworks" % "shc-core" % "1.1.1-2.1-s_2.11"

)