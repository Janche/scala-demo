package org.example

object test {

    def main(args: Array[String]): Unit = {

//        f()
//        println()
//        printTriangle()
//        printTriangle3()
//        def f(): Unit = {
//            printf("hello scala!!!")
//        }
//
//        val linesList = List(("Hello Scala World", 2), ("Hello Spark", 3), ("Hello Python", 2), ("Hello Java", 1))
//
//        val flatMapList = linesList.flatMap(t => t._1.split(" "))
//        println("flatMap: "+flatMapList.mkString(","))
//        val mapList = linesList.map(t => t._1.split(" "))
//        println("map: "+mapList.mkString(","))


        //eg:
        println(addBy(50)(234))

    }

    //第二种. 函数的返回值是一个函数
    def addBy(n: Int) = {
        (d : Double) => n + d
    }

    def printTriangle(): Unit = {
        for (i <- 1 to 18; j = (18-i)/2 ) {
            println(" "*j + "*"*i + " "*j)
        }
    }

    def printTriangle2(): Unit = {
        for (i <- Range(1, 18, 2); j = (18-i)/2 ) {
            println(" "*j + "*"*i + " "*j)
        }
    }

    def printTriangle3(): Unit = {
        for { i <- Range(1, 18, 2)
              j = (18-i)/2} {
            println(" "*j + "*"*i + " "*j)
        }
    }

    def f2() :Unit = {
        println("f2.....")
    }

    paramTest(age=3)

    def paramTest(name: String = "lisi", age: Int) : Unit = {
        println(s"${name} - ${age}")
    }

}
