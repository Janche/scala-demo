package org.example.sparse

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
/**
 * 稀疏数组-棋盘练习
 */
object SparseArrayDemo {

    def main(args: Array[String]): Unit = {
        // 二维数组映射棋盘
        val rows = 11
        val cols = 11
        val chessMap = Array.ofDim[Int](rows, cols)

        // 初始化 1表示黑子，2表示白子，0表示没有棋子
        chessMap(1)(2) = 1
        chessMap(2)(3) = 2

        println("原始的棋盘：")
        for (row <- chessMap) {
            for (item <- row) {
                printf("%d ", item)
            }
            println()
        }

        // 对原始二维数组进行压缩存储
        val listBuffer = ListBuffer[Node]()
        listBuffer.append(new Node(rows, cols, 2))
        for (i <- 0 until chessMap.length) {
            for (j <- 0 until chessMap(i).length) {
                if (chessMap(i)(j) != 0) {
                    listBuffer.append(new Node(i, j, chessMap(i)(j)))
                }
            }
        }
        println("\n压缩后的棋盘：")
        for (item <- listBuffer) {
            printf("%d %d %d\n", item.row, item.col, item.value)
        }

        println("\n由稀疏数组复原后的棋盘：")
        val node = listBuffer(0)
        val chessMap2 = Array.ofDim[Int](node.row, node.col)
        for (i <- 1 until listBuffer.length) {
            val node = listBuffer(i)
            chessMap2(node.row)(node.col) = node.value
        }

        for (row <- chessMap2) {
            for (item <- row) {
                printf("%d ", item)
            }
            println()
        }

    }

}

class Node(val row: Int, val col: Int, val value: Int)