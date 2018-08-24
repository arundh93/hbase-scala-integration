import org.apache.spark.sql.{SparkSession, _}
import org.apache.spark.sql.execution.datasources.hbase._

object SparkHbaseTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").getOrCreate()
    import spark.implicits._
    val newContact = ContactRecord("16891", "40 Ellis St.", "674-555-0110", "John Jackson", "230-555-0194")

    val newData = new Array[ContactRecord](1)
    newData(0) = newContact

    val df1 = spark.sparkContext.parallelize(newData).toDF
    df1.write
      .options(Map(HBaseTableCatalog.tableCatalog -> catalog, HBaseTableCatalog.newTable -> "5"))
      .format("org.apache.spark.sql.execution.datasources.hbase").save()

  }

  def catalog =
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

//  def withCatalog(spark: SparkSession, cat: String): DataFrame = {
//    spark.sqlContext
//      .read
//      .options(Map(HBaseTableCatalog.tableCatalog -> cat))
//      .format("org.apache.spark.sql.execution.datasources.hbase")
//      .load()
//  }
}


case class ContactRecord(
                          rowkey: String,
                          officeAddress: String,
                          officePhone: String,
                          personalName: String,
                          personalPhone: String
                        )
