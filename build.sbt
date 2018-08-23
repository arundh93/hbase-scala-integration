
name := "scala-hbase-test"
version := "1.0"
scalaVersion := "2.12.6"
resolvers += "Thrift" at "http://people.apache.org/~rawson/repo/"
resolvers += "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/"

libraryDependencies ++= Seq(
 "org.apache.hadoop" % "hadoop-common" % "2.6.0-cdh5.15.0",
 "org.apache.hbase" % "hbase" % "1.2.0-cdh5.15.0",
 "org.apache.hbase" % "hbase-client" % "1.2.0-cdh5.15.0",
 "org.apache.hbase" % "hbase-common" % "1.2.0-cdh5.15.0",
 "org.apache.hbase" % "hbase-server" % "1.2.0-cdh5.15.0"
)