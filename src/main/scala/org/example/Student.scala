package org.example

class Student(a: Int, n: String) {
    def this(age:Int, sal: Double){
        this(age, "zhangsan")
        this.sal = sal
    }

    var age:Int = a
    var sal: Double = 20000.0
    var name: String = n

    override def toString: String = {
        this.age + ", " + this.name + ", " + this.sal
    }
}

object Student {
//    def apply(s: String): Student = new Student()

    //    1 to 5
    //    val range = Range(1, 5)
    def main(args: Array[String]): Unit = {

        val s1 = new Student(10, "lisi")
        val s2 = new Student(10, 200)
        println(s1.toString)
        println(s2.toString)
    }

    def test: Unit = {
//        val student = Student("zhangsan")
//        println(student)
    }

    def test2: Unit = {

    }

    //    val list1 = List(1, 2, 3, "abc")
    //    val listM: List[String] = list1.map {
    //        case num => num + "xxx"
    //    }
    //    println(listM)


    //    var list2 = 4 :: 5 :: 6 :: list1 ::: list1
    //    println(list2)
    //
    //    val list = List("hello scala", "hello world", " hello java")
    //    println(list.flatMap(word => word.trim.split(" ")))

    //    val list = List(1, 2, 3, "abc")
    //    val funp: PartialFunction[Any, Int] = new PartialFunction[Any, Int] {
    //        def isDefinedAt(any: Any) = {
    //            if (any.isInstanceOf[Int]){
    //                true
    //            }else false
    //        }
    //        def apply(any: Any): Int = any.asInstanceOf[Int]
    //    }
    //    val ints: List[Int] = list.collect(funp)
    //    println("list: "+ints)
    //
    //    // 抽象结构
    //    def test(f: => Unit): Unit = {
    //        f
    //    }
    //
    //    test{}

    // wordCount test
    var linesList = List(("Hello Scala World", 2), ("Hello Spark", 3), ("Hello Python", 2), ("Hello Java", 1))
    private val flatList: List[(String, Int)] = linesList.flatMap(t => {
        val words = t._1.split(" ")
        words.map(w => (w, t._2))
    })

    private val groupList: Map[String, List[(String, Int)]] = flatList.groupBy(t => t._1)
    var result: Map[String, Int] = groupList.map(t => {
        val ints = t._2.map(m => m._2)
        (t._1, ints.sum)
    })
    // 第二种写法
    result = groupList.mapValues(t => t.map(m => m._2).sum)

    // 将map转为 集合就可以排序了
    private val resultList: List[(String, Int)] = result.toList.sortWith((left, right) => {
        left._2 > right._2
    })
    // 只能按照字典顺序，根据传入的字段排序
    private val sortByList: List[(String, Int)] = result.toList.sortBy(t => t._2)
    println(sortByList)

    private val takeList: List[(String, Int)] = resultList.take(3)
    println(takeList)

    private val ints = new Array[Int](10)
    ints(0) = 2
    ints(1) = 3
    println(ints.foreach(println(_)))
//    Array.ofDim()

    private val length: Int = "com.xmgame.savethegirl.nearme.gamecenter".length
    println(length)
}


