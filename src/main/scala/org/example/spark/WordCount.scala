package org.example.spark

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
    def main(args: Array[String]): Unit = {

        //1.创建SparkConf并设置App名称
        val conf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
        //2.创建SparkContext，该对象是提交Spark App的入口
        val sc = new SparkContext(conf)

        val rdd = sc.makeRDD(List(2, 1, 3, 4))
        val array = rdd.sortBy(x => x).collect()
        println("array: " + array.mkString(","))
        val test = sc.textFile("input", 2)
        //3.使用sc创建RDD并执行相应的transformation和action
        val tuples = test.flatMap(_.split(" ")).filter(!_.equals("")).map((_, 1)).reduceByKey(_ + _)
        tuples.saveAsTextFile("output")
        println(tuples.collect().mkString(","))
        //        sc.textFile(args(0))
        //            .flatMap(_.split(" "))
        //            .map((_, 1)).reduceByKey(_+_, 1)
        //            .sortBy(_._2, false)
        //            .saveAsTextFile("output")

        //4.关闭连接
        sc.stop()
    }


}
