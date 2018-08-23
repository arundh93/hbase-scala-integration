import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Put}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, HColumnDescriptor, HTableDescriptor, TableName}

object ScalaHbaseTest {

  def main(args: Array[String]) = {
    println("ScalaHbaseTest!")
    val conf: Configuration = HBaseConfiguration.create()
    // By default HBaseConfiguration.create loads resources from hbase-default.xml and hbase-site.xml
    // Hence we need not explicitly set the values below in this case
    //    conf.set("hbase.zookeeper.quorum", "localhost")
    //    conf.set("hbase.zookeeper.property.clientPort", "2181")
    val connection: Connection = ConnectionFactory.createConnection(conf)

    // add to existing table
    val table = connection.getTable(TableName.valueOf("emp"))
    val put1: Put = new Put(Bytes.toBytes("row5"))
    put1.addColumn(Bytes.toBytes("personal data"), Bytes.toBytes("qual5"), Bytes.toBytes("val5"))
    table.put(put1)

    //create new table
    val admin = connection.getAdmin
    val tableDesc = new HTableDescriptor(TableName.valueOf("my-table1"))
    val idsColumnFamilyDesc = new HColumnDescriptor(Bytes.toBytes("ids"))
    tableDesc.addFamily(idsColumnFamilyDesc)
    admin.createTable(tableDesc)

    println("Success")
  }
}