package com.walt.bigdata.kafka

import org.apache.spark.sql.SparkSession

object ScalaSparkApp extends App {

  val spark = SparkSession.builder
    .appName("Kafka SparkStreaming test")
    .master("local[3]")
    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    .getOrCreate()

  spark.sparkContext.setLogLevel("WARN")
  import spark.implicits._

  val df = spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "mytopic")
    .load()

  val query = df
    .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
    .as[(String, String)]
    .writeStream
    .outputMode("append")
    .format("console")
    .start()

  query.awaitTermination()

}
