package org.example.queue

import scala.io.StdIn

/**
 * 环状队列 -- 针对队列只能使用一次的优化解决方案
 */
object CircleQueueDemo {
    def main(args: Array[String]): Unit = {
        val queue = new CircleQueue(4)

        while (true) {
            println()
            println("队列操作方式：")
            println("1. get, 取出队列元素")
            println("2. add, 添加队列元素")
            println("3. show, 遍历队列元素")
            println("4. peek, 查看头元素")
            println("5. exit, 退出程序")
            val str = StdIn.readLine()
            str match {
                case "add" =>
                    println("请输入一个数：")
                    val num = StdIn.readInt()
                    queue.addQueue(num)
                case "get" =>
                    val value = queue.getQueue()
                    if (value.isInstanceOf[Exception]) {
                        val message = value.asInstanceOf[Exception].getMessage
                        println(message)
                    } else {
                        val num = value.asInstanceOf[Int]
                        println(num)
                    }
                case "show" => queue.show()
                case "peek" => queue.peek()
                case "exit" => System.exit(0)
                case _ => println("输入有误，请重新输入！！！")
            }
        }
    }

}

class CircleQueue(size: Int) {

    // 队列大小 ,预留一个元素，实际大小为size-1
    val maxSize = size
    // 队列数据，存放在数组中
    val arr = new Array[Int](size)
    // front 初始化为0，约定包含头元素，指向队列头元素的位置
    var front = 0
    // rear初始化为0，约定不包含这个元素，指向队列尾部前一个位置
    var rear = 0

    def isFull(): Boolean = {
        (rear + 1) % maxSize == front
    }

    def isEmpty(): Boolean = {
        rear == front
    }

    def addQueue(num: Int): Unit = {
        // 先判断队列是否已满
        if (isFull()) {
            println("队列已满，不能添加")
            return
        }
        // 先赋值
        arr(rear) = num
        // 队尾后移
        rear = (rear + 1) % maxSize
    }

    def getQueue(): Any = {
        // 先判断队列是否为空
        if (isEmpty()) {
            return new Exception("对列为空，请添加数据")
        }
        val frontValue = arr(front)
        front = (front + 1) % maxSize
        frontValue
    }

    def show(): Unit = {
        // 先判断队列是否为空
        if (isEmpty()) {
            println("对列为空，请添加数据")
            return
        }
        // 这里使用取模的方式解决
        for (i <- front until front + size()) {
            printf("arr(%d) = %d\t", i % maxSize, arr(i % maxSize))
        }
    }

    def peek(): Unit = {
        // 先判断队列是否为空
        if (isEmpty()) {
            println("对列为空，请添加数据")
            return
        }
        println(arr((front + maxSize) % maxSize))
    }

    def size(): Int = {
        (rear - front + maxSize) % maxSize
    }

}
