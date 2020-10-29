package org.example.trait_test

object Trait01 {
    def main(args: Array[String]): Unit = {

        var mysql = new MySQL5 with DB5 with File5
        mysql.insert(100)

        var mysql2 = new MySQL5  with DB5 //  ok
        mysql2.insert(100)
//        var mysql3 = new MySQL5 with File5 // error
//        mysql3.insert(100)
//        var mysql4 = new MySQL5 with File5 with DB5// error
//        mysql4.insert(100)
        var mysql5 = new MySQL5 with DB5 with File5// ok
        mysql5.insert(100)

    }
}

trait Operate5 {
    def insert(id: Int)
}

trait File5 extends Operate5 {
    // 重写抽象方法的目的是为了明确super关键字的指向问题
    // 重写抽象方法时需要考虑混入特质的顺序!!!
    abstract override def insert(id: Int): Unit = {
        println("将数据保存到文件中..")
        //当重写抽象方法后,这个insert指向哪个特质的insert和混入的顺序有关了.
        //当var mysql = new MySQL5 with DB5 with File5 时，
        //调用 mysql.insert , 这个insert就指向 DB5的insert了
        super.insert(id)
    }
}

trait DB5 extends Operate5 {
    def insert(id: Int): Unit = {
        println("将数据保存到数据库中..")
    }
}

class MySQL5 {

}
