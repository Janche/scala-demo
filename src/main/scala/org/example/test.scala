package org.example

object test {

    def main(args: Array[String]): Unit = {

        f()
        println()
//        printTriangle()
        printTriangle3()
        def f(): Unit = {
            printf("hello scala!!!")
        }

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
