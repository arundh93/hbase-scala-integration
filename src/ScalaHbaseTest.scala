import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{ConnectionFactory, HTable, Put}
import org.apache.hadoop.hbase.util.Bytes

object ScalaHbaseTest {

  def main(args: Array[String]) = {
    println("ScalaHbaseTest!")
    val conf: Configuration = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", "localhost")
    conf.set("hbase.zookeeper.property.clientPort", "2181")
    val table: HTable = new HTable(conf, "emp")
    val put1: Put = new Put(Bytes.toBytes("row3"))
    put1.add(Bytes.toBytes("personal data"), Bytes.toBytes("qual3"), Bytes.toBytes("val3"))
    table.put(put1)
    println("Success")
  }
}