package org.example.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


object HelloWorld {

    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("SparkSql-Demo")
        val spark = SparkSession.builder()
            .config(conf)
            .getOrCreate()

        import spark.implicits._
        val df = spark.read.json("input/people.json")

        df.show()

        df.filter($"age" > 21).show()

        df.createOrReplaceTempView("persons")

        spark.sql("SELECT * FROM persons where age > 21").show()

        spark.stop()

    }
}
