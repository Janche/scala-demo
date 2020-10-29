package org.example.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object SeriTest {
    def main(args: Array[String]): Unit = {
        //1.创建SparkConf并设置App名称
        val conf = new SparkConf().setAppName("Serializable").setMaster("local[*]")
        //2.创建SparkContext，该对象是提交Spark App的入口
        val sc = new SparkContext(conf)

        val rdd: RDD[String] = sc.parallelize(Array("hadoop", "spark", "hello", "world"))
//        val string = rdd.map((_, 1)).toDebugString
//        println(string)
        val search = new Search("h")
        val matchValue: RDD[String] = search.getMatch2(rdd)
        println(matchValue.collect().mkString(","))
    }
}

class Search(query: String) {

    def isMatch(s:String):Boolean = {
        s.contains(query)
    }

    def getMatch1(rdd: RDD[String]):RDD[String] = {
        rdd.filter(isMatch(_))
    }

    def getMatch2(rdd: RDD[String]):RDD[String] = {
        // 把query变量赋值给 q 就不需要序列化了,因为q 是字符串类型，本身就是序列化的，相当于没有使用到Search对象,
        val q = query
        rdd.filter(_.contains(q))
    }
}