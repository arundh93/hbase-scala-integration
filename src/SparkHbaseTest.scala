import org.apache.spark.sql.SparkSession

object SparkHbaseTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").getOrCreate()
    import spark.implicits._
    val df = Seq("1").toDF
    df.write.parquet("/tmp/tmp.parquet")

  }
}
