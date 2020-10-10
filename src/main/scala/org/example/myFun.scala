package org.example

object myFun {

    def main(args: Array[String]): Unit = {
//        f0()()
    }

    def f(): Unit = {
        println("function")
    }

    def f0() = {
        f _
    }

    def f1(i : Int) = {  //等价于这个： def f1(): () => {} = {}
        def f2(j: Int): Int = {
            i * j
        }
        f2 _
    }

    def f3(i: Int)(j: Int): Int = {
        i * j
    }
    //    println(f1(2)(3))
    //    println(f3(3){4})

    // 将函数作为参数传递给另外一个函数，需要采用特殊的声明方式
    // ()=>Unit
    // 参数列表=> 返回值类型
    def f4(f: () => Int): Int = {
        f()+ 10
    }

    def f5() :Int = {
        5
    }
    println(f4(f5))

    def f41(f: (Int)=> Int): Int = {
        f(2)+ 10
    }

    def f42(i :Int) :Int = {
        i * 5
    }
    println("f41:"+ f41(f42))
    println("f41:"+ f41( i => { i*5 }))
    println("f41:"+ f41( (i: Int) => { i*5 }))

    // 匿名函数的两种写法
    // 1. 可直接运行，不常用
    () -> {println("匿名函数写法一，可直接运行")}
    // 2. 需要调用才能运行，常用
    val ff = () => { // 等价于 val ff: () => Unit =  () => {
        println("匿名函数写法二，需要调用才能运行")
    }
    ff()
    // 使用匿名函数改善
    def f6( f: ()=>Int): Int = {
        f() + 3
    }
    f6(()=>{print(222); 2})
    println()
    println(f6(()=>{println(222); 2}))

    println("f7=======")
    def f7( f :(Int) => Unit): Unit = {
       f(10)
    }
    def f8(i :Int) = {
        println(i)
    }
    f7(f8)
    f7( (i)=> {println(i)})
    f7( i => {println(i)})
    f7( {println(_)})
    f7( println(_) )

    println("f7=======")
    def f9( f: (Int, Int) => Int): Int = {
        f(4, 6) * 5
    }
    def f10(i: Int, j: Int): Int = {
        i + j
    }
    println(f9(f10))
    println(f9( (i, j) => { i + j }))
    println(f9( (i, j) =>  i + j ))
    println(f9( _ + _ ))
    1.to(5)
}

