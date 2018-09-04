import org.apache.spark.sql.{SparkSession, _}
import org.apache.spark.sql.execution.datasources.hbase._

object SparkHbase {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("yarn").getOrCreate()
    //    val newContact = ContactRecord("16891", "40 Ellis St.", "674-555-0110", "John Jackson", "230-555-0194")

    val df1 = spark.read.option("header", "true").csv("hdfs:///user/spike/feds_part_no_null.csv")
    df1.write
      .options(Map(HBaseTableCatalog.tableCatalog -> fed_catalog, HBaseTableCatalog.newTable -> "5"))
      .format("org.apache.spark.sql.execution.datasources.hbase").save()

  }

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
}




