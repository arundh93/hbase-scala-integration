import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{ConnectionFactory,HTable,Put}
import org.apache.hadoop.hbase.util.Bytes

object Hi {

 def main(args: Array[String]) = {
 println("Hi!")
 val conf:Configuration = HBaseConfiguration.create()
  conf.set("hbase.zookeeper.quorum","localhost")
  conf.set("hbase.zookeeper.property.clientPort", "2181")
 val table:HTable = new HTable(conf, "emp")
 val put1:Put = new Put(Bytes.toBytes("row2"))
 put1.add(Bytes.toBytes("personal data"),Bytes.toBytes("qual2"),Bytes.toBytes("val2"))
 table.put(put1)
 println("Success")
 }
}