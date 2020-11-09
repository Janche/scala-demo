package org.example.implicit_test

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object ImplicitTestVar {
    def main(args: Array[String]): Unit = {
        // 隐式变量（值）
        //        implicit val name : String = "Scala"
        //
        implicit val name1: String = "World"

        // 声明方法，但是参数无默认值
        // 如果函数调用时，没有传递参数，那么可以声明implicit 关键字，去查找隐式值
        def hello(implicit content: String): Unit = {
            println("Hello " + content)
        }

        //调用hello
        //        hello

        var tuple1 = (1, 23, "hello")
        for (item <- tuple1.productIterator) {
            //            println(item)
        }
        //
        //        val list1 = List(1, 2, 3, "abc")
        //        val list5 = 4 :: 5 :: 6 :: list1 :: Nil // 从右向左的逻辑
        //        println(list5)
        //        val list6 = 4 :: 5 :: 6 :: list1 // 从右向左的逻辑
        //        println(list6)
        //        val list7 = 4 :: 5 :: 6 :: list1 ::: Nil
        //        println(list7)

        val lst0 = ListBuffer[Int](1, 2, 3)
        println("lst0(2)=" + lst0(2))
        // 遍历
        for (item <- lst0) {
            println("item=" + item)
        }
        val lst1 = new ListBuffer[Int]
        lst1 += 4
        lst1.append(5)
        lst0 ++= lst1
        val lst2 = lst0 ++ lst1
        val lst3 = lst0 :+ 5
        println("=====删除=======")
        println("lst1=" + lst1)
        lst1.remove(1)
        for (item <- lst1) {
            //            println("item=" + item)
        }

        //        val map1 = mutable.Map( ("A", 1), ("B", "北京"), ("C", 3) )
        //        for ((k, v) <- map1) println(k + " is mapped to " + v)
        //        for (v <- map1.keys) println(v)
        //        for (v <- map1.values) println(v)
        //        for(v <- map1) println(v)

        val list1 = List(3, 5, 7)

        val list2 = list1.map(_ * 2)
        //        println(list2)

        val names = List("Alice", "Bob", "Nick")

        def upper(s: String): String = {
            s.toUpperCase
        }
        //        println(names.flatMap(upper))

        //        val list = List(1, 20, 30, 4, 5)
        //        def sum(n1: Int, n2: Int): Int = {
        //            n1 + n2
        //        }
        //        val res = list.reduceLeft(sum)
        //        println("res=" + res)


        val list = List(3, 4, 2, 7, 5)

        def min(n1: Int, n2: Int): Int = {
            if (n1 > n2) n2 else n1
        }

        println("res=" + list.reduceLeft(min))


        val sentence = "AAAAAAAAAABBBBBBBBCCCCCDDDDDDD"

        def putArry(arr: ArrayBuffer[Char], c: Char): ArrayBuffer[Char] = {

            arr.append(c) //增加
            //说明,理解putArry ,putArray的参数 和foldLeft 的本质
            //1. 返回的arr 是引用方法
            //2. 返回的arr 将会作为putArry( arr : ArrayBuffer[Char], c : Char )的arr : ArrayBuffer[Char]的实参
            //3. 因为是引用，因此arr 这个ArrayBuffer变量就会不断的增加
            arr //返回，
        }

        //创建val arr = ArrayBuffer[Char]()
        val arr = ArrayBuffer[Char]()
        sentence.foldLeft(arr)(putArry)
        //        println("arr=" + arr)

        val list11 = List(1, 2, 3)
        val list22 = List(4, 5, 6)
        val list33 = list11.zip(list22)
        //        println("list33=" + list33)
        (1 to 5).par.foreach(print(_, ","))
        println()
        (1 to 5).foreach(print(_, ","))

        println()

        for (ch <- "+-3!") {
            var sign = 0
            var digit = 0
            ch match {
                case '+' => sign = 1
                case '-' => sign = -1
                // 这里可以增加一个if 的判断，这样就可以对某个范围数据进行匹配了.
                // 匹配到一个 _ 就不会再匹配的，这个原则和普通的case 是一样的。
                case _ if ch.toString.equals("3") => digit = 3 //模式匹配守卫功能
                case _ => sign = 2
            }
            println(ch + " " + sign + " " + digit)
        }



    }
}
