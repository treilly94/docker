// https://kudu.apache.org/docs/developing.html
import org.apache.kudu.spark.kudu._
import org.apache.kudu.client._
import collection.JavaConverters._

import org.apache.spark.sql.types._
import org.apache.spark.sql._

// Create kudu context
val kuduContext = new KuduContext("kudu:7051", spark.sparkContext)

// Create df
val schema = List(
  StructField("name", StringType, false),
  StructField("age", IntegerType, true)
)

val data = Seq(
  Row("miguel", null),
  Row("luisa", 21)
)

val df = spark.createDataFrame(
  spark.sparkContext.parallelize(data),
  StructType(schema)
)

df.show()

// Create table from df
kuduContext.createTable(
    "test_table", df.schema, Seq("name"),
    new CreateTableOptions()
        .setNumReplicas(1)
        .addHashPartitions(List("name").asJava, 3))

