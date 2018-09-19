// https://spark.apache.org/docs/2.3.1/structured-streaming-kafka-integration.html
import org.apache.spark.sql.types._
import org.apache.spark.sql._

// Create df
val schema = List(
  StructField("key", StringType, false),
  StructField("value", IntegerType, true)
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

// Write batch to topic
df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)").write.format("kafka").option("kafka.bootstrap.servers", "kafka:9092").option("topic", "topic1").save()

// Read batch from topic
val dfRead = spark.read.format("kafka").option("kafka.bootstrap.servers", "kafka:9092").option("subscribe", "topic1").load()
dfRead.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)").as[(String, String)]