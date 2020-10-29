package org.example.spark.streaming

import java.io.{BufferedReader, InputStreamReader}

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Kafka_streaming {
    def main(args: Array[String]): Unit = {
        // 1. 创建SparkConf
        val conf = new SparkConf().setMaster("local[*]").setAppName("KafkaStreaming");
        val streamContext = new StreamingContext(conf, Seconds(5))

        // 2. 定义kafka参数
        val brokers = "hadoop102:9092,hadoop103:9092,hadoop104:9092"
        val topic = "kafka_stream"
        val consumerGroup = "spark"

        val kafkaLine = KafkaUtils.createStream(
            streamContext,
            "hadoop102:2181",
            "spark",
            Map("kafka_stream" -> 3))
//        kafkaLine.
        kafkaLine.flatMap(t => t._2.split(" ")).map((_,1)).reduceByKey(_+_).print()

        streamContext.start()
        streamContext.awaitTermination()

    }
}
class MyReceiver(host:String, port:Int) extends Receiver[String](StorageLevel.MEMORY_ONLY){
    var socket: java.net.Socket = null
    def receiver():Unit = {
        socket = new java.net.Socket(host, port)
        val reader = new BufferedReader(new InputStreamReader(socket.getInputStream,"UTF-8"))
        var line: String = null
        while ( (line = reader.readLine()) != null){
            if ("END".equals(line)){
                return
            }else{
                this.store(line)
            }
        }

    }
    override def onStart(): Unit = {
        new Thread(new Runnable {
            override def run(): Unit = {
                receiver()
            }
        })
    }

    override def onStop(): Unit = {
        if (null != socket){
            socket.close()
            socket = null
        }
    }
}