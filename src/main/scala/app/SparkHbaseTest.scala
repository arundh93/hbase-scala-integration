package app

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.datasources.hbase._

object SparkHbaseTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").getOrCreate()
    import spark.implicits._
    val newContact = ContactRecord("16891", "40 Ellis St.", "674-555-0110", "John Jackson", "230-555-0194")

    val newData = new Array[ContactRecord](1)
    newData(0) = newContact

    val df2 = spark.read.option("header", "true").csv("/Users/arundh/Downloads/feds1_n.csv")
    val df1 = spark.sparkContext.parallelize(newData).toDF
    df2.write
      .options(Map(HBaseTableCatalog.tableCatalog -> fed_catalog, HBaseTableCatalog.newTable -> "5"))
      .format("org.apache.spark.sql.execution.datasources.hbase").save()

  }

  def catalogForContactRecord =
    s"""{
       |"table":{"namespace":"default", "name":"Contacts"},
       |"rowkey":"key",
       |"columns":{
       |"rowkey":{"cf":"rowkey", "col":"key", "type":"string"},
       |"officeAddress":{"cf":"Office", "col":"Address", "type":"string"},
       |"officePhone":{"cf":"Office", "col":"Phone", "type":"string"},
       |"personalName":{"cf":"Personal", "col":"Name", "type":"string"},
       |"personalPhone":{"cf":"Personal", "col":"Phone", "type":"string"}
       |}
       |}""".stripMargin

  /**
    * User provide table schema definition
    * {"tablename":"name", "rowkey":"key1:key2",
    * "columns":{"col1":{"cf":"cf1", "col":"col1", "type":"type1"},
    * "col2":{"cf":"cf2", "col":"col2", "type":"type2"}}}
    * Note that any col in the rowKey, there has to be one corresponding col defined in columns
    */

  def catalog =
    s"""{
       |"table":{"namespace":"default", "name":"Contacts5"},
       |"rowkey":"seq",
       |"columns":{
       |"key":{"cf":"rowkey", "col":"seq", "type":"string"},
       |"officeAddress":{"cf":"rkey", "col":"Address", "type":"string"},
       |"officePhone":{"cf":"r1key", "col":"Phone", "type":"string"},
       |"personalName":{"cf":"rkey", "col":"Name", "type":"string"},
       |"personalPhone":{"cf":"r3key", "col":"Phone", "type":"string"}
       |}
       |}""".stripMargin

  def fed_catalog =
    s"""{
       |"table":{"namespace":"default", "name":"Feds"},
       |"rowkey":"flight_id",
       |"columns":{
       |"adshex":{"cf":"test", "col":"adshex", "type": "string"},
       |"flight_id":{"cf":"rowkey", "col":"flight_id", "type": "string"},
       |"latitude":{"cf":"test", "col":"latitude", "type": "string"},
       |"longitude":{"cf":"test", "col":"longitude", "type": "string"},
       |"altitude":{"cf":"test", "col":"altitude", "type": "string"},
       |"speed":{"cf":"test", "col":"speed", "type": "string"},
       |"track":{"cf":"test", "col":"track", "type": "string"},
       |"squawk":{"cf":"test", "col":"squawk", "type": "string"},
       |"type":{"cf":"test", "col":"type", "type": "string"},
       |"timestamp":{"cf":"test", "col":"timestamp", "type": "string"},
       |"name":{"cf":"test", "col":"name", "type": "string"},
       |"other_names1":{"cf":"test", "col":"other_names1", "type": "string"},
       |"other_names2":{"cf":"test", "col":"other_names2", "type": "string"},
       |"n_number":{"cf":"test", "col":"n_number", "type": "string"},
       |"serial_number":{"cf":"test", "col":"serial_number", "type": "string"},
       |"mfr_mdl_code":{"cf":"test", "col":"mfr_mdl_code", "type": "string"},
       |"mfr":{"cf":"test", "col":"mfr", "type": "string"},
       |"model":{"cf":"test", "col":"model", "type": "string"},
       |"year_mfr":{"cf":"test", "col":"year_mfr", "type": "string"},
       |"type_aircraft":{"cf":"test", "col":"type_aircraft", "type": "string"},
       |"agency":{"cf":"test", "col":"agency", "type": "string"}
       |}
       |}""".stripMargin


  def catalogForRecordSingleCF =
    s"""{
       |"table":{"namespace":"default", "name":"RecordsSingle"},
       |"rowkey":"seq",
       |"columns":{
       |"seq":{"cf":"rowkey", "col":"seq", "type":"string"},
       |"first":{"cf":"Personal", "col":"first", "type":"string"},
       |"last":{"cf":"Personal", "col":"last", "type":"string"},
       |"age":{"cf":"Personal", "col":"age", "type":"string"},
       |"street":{"cf":"Personal", "col":"street", "type":"string"},
       |"city":{"cf":"Personal", "col":"city", "type":"string"},
       |"state":{"cf":"Personal", "col":"state", "type":"string"},
       |"zip":{"cf":"Personal", "col":"zip", "type":"string"},
       |"dollar":{"cf":"Personal", "col":"dollar", "type":"string"},
       |"pick":{"cf":"Personal", "col":"pick", "type":"string"},
       |"date":{"cf":"Personal", "col":"date", "type":"string"}
       |}
       |}""".stripMargin

  case class Record(
                     seq: String,
                     first: String,
                     last: String,
                     age: String,
                     street: String,
                     city: String,
                     state: String,
                     zip: String,
                     dollar: String,
                     pick: String,
                     date: String
                   )


  def catalogForRecordMultipleCF =
    s"""{
       |"table":{"namespace":"default", "name":"RecordsMultiple"},
       |"rowkey":"seq",
       |"columns":{
       |"rowkey":{"cf":"seq", "col":"seq", "type":"string"},
       |"first":{"cf":"first", "col":"first", "type":"string"},
       |"last":{"cf":"last", "col":"last", "type":"string"},
       |"age":{"cf":"age", "col":"Name", "age":"string"},
       |"street":{"cf":"street", "col":"street", "type":"string"},
       |"city":{"cf":"city", "col":"city", "type":"string"},
       |"state":{"cf":"state", "col":"state", "type":"string"},
       |"zip":{"cf":"zip", "col":"zip", "type":"string"},
       |"dollar":{"cf":"dollar", "col":"dollar", "type":"string"},
       |"pick":{"cf":"pick", "col":"pick", "type":"string"},
       |"date":{"cf":"date", "col":"date", "type":"string"}
       |}
       |}""".stripMargin

  //  def withCatalog(spark: SparkSession, cat: String): DataFrame = {
  //    spark.sqlContext
  //      .read
  //      .options(Map(HBaseTableCatalog.tableCatalog -> cat))
  //      .format("org.apache.spark.sql.execution.datasources.hbase")
  //      .load()
  //  }
}





case class ContactRecord(
                          key: String,
                          officeAddress: String,
                          officePhone: String,
                          personalName: String,
                          personalPhone: String
                        )




