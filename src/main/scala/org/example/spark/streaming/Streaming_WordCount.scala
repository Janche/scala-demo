package org.example.spark.streaming

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.{SparkConf, streaming}

object Streaming_WordCount {

    def main(args: Array[String]): Unit = {
        // 初始化 Spark 配置信息
        val conf = new SparkConf().setMaster("local[*]").setAppName("Streaming-WordCount")
        // 初始化SparkStreamingContext
        val sc = new StreamingContext(conf, streaming.Seconds(4))
        // 监控端口创建 DStream, 读进来的数据为一行一行的
        val lineStream = sc.socketTextStream("hadoop102", 9999)
        // 处理行数据
        val countWords = lineStream.flatMap(line => line.split(" ")).map((_, 1)).reduceByKey(_ + _)
        // 打印
        countWords.print()

        // 启动 SparkStreamingContext
        sc.start()
        sc.awaitTermination()
        
    }

}
