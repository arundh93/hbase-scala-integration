import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Put}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}

object ScalaHbaseTest {

  def main(args: Array[String]) = {
    println("ScalaHbaseTest!")
    val conf: Configuration = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", "localhost")
    conf.set("hbase.zookeeper.property.clientPort", "2181")
    val connection: Connection = ConnectionFactory.createConnection(conf)
    val table = connection.getTable(TableName.valueOf("emp"))
    val put1: Put = new Put(Bytes.toBytes("row5"))
    put1.addColumn(Bytes.toBytes("personal data"), Bytes.toBytes("qual5"), Bytes.toBytes("val5"))
    table.put(put1)
    println("Success")
  }
}