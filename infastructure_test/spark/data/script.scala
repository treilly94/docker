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

// Read a table from Kudu
spark.read.options(Map("kudu.master" -> "kudu:7051","kudu.table" -> "test_table")).kudu.show()

// Insert data
kuduContext.insertRows(df, "test_table")

// Read a table from Kudu
spark.read.options(Map("kudu.master" -> "kudu:7051","kudu.table" -> "test_table")).kudu.show()

// Update data
val alteredDF = df.withColumn("age", functions.lit(47))
kuduContext.updateRows(alteredDF, "test_table")

// Read a table from Kudu
spark.read.options(Map("kudu.master" -> "kudu:7051","kudu.table" -> "test_table")).kudu.show()

// Delete data
kuduContext.deleteRows(df.select("name"), "test_table")

// Read a table from Kudu
spark.read.options(Map("kudu.master" -> "kudu:7051","kudu.table" -> "test_table")).kudu.show()


// Check for the existence of a Kudu table
kuduContext.tableExists("another_table")

// Delete a Kudu table
kuduContext.deleteTable("test_table")