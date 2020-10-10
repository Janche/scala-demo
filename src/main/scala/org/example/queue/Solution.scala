package org.example.queue

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Solution {

    def main(args: Array[String]): Unit = {
        //        println(lastRemaining(5, 3))
//        println(lastRemaining2(10, 17))
        println(lastRemaining3(5, 3))
    }

    def lastRemaining(n: Int, m: Int): Int = {
        var last = 0
        for (i <- 2 to n) {
            last = (last + m) % i
        }
        last
    }

    def lastRemaining2(n: Int, m: Int): Int = {
        var list = ListBuffer[Int]()
        for (i <- 0 until n){
            list.append(i)
        }
        var c = (m - 1) % n
        while (list.length != 1) {
            list.remove(c)
            c = (c + m - 1) % list.length
        }
        list(0)
    }
    def lastRemaining3(n: Int, m: Int): Int = {
        var buffer = new ArrayBuffer[Int]()
        for (i <- 0 until n){
            buffer.append(i)
        }
        var c = (m - 1) % n
        while (buffer.length != 1) {
            buffer.remove(c)
            c = (c + m - 1) % buffer.length
        }
        buffer(0)
    }

}
