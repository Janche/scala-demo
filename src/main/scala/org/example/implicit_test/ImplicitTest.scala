package org.example.implicit_test

object ImplicitTest {
    def main(args: Array[String]): Unit = {
        //将MySQL转成DB。
        implicit def addDelete(mysql: MySQL): DB = {
            new DB
        }

        // MySQL ==> DB， 同时还保留MySQL类本身的功能
         val mysql : MySQL = new MySQL //这样写也√
//        val mysql = new MySQL
        mysql.delete()
        mysql.insert()
    }
}
class MySQL {
    def insert(): Unit = {
        println("插入数据~")
    }
}
class DB {
    def delete(): Unit = {
        println("删除数据")
    }
}