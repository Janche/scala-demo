package org.example.queue
import scala.util.control.Breaks.{break, breakable}
/**
 * 约瑟夫环出圈为问题
 */
object JosephuDemo {

    def main(args: Array[String]): Unit = {
        val josePhu = new JosePhu()
//        josePhu.addBoy(4)
//        josePhu.list()

        josePhu.countBoy(1,17,10)
    }


}

class JosePhu {

    var header: Boy = null

    /**
     *
     * @param startNo 开始的位置
     * @param countNum 每次第几个出圈
     * @param boyNums 小孩的总数
     */
    def countBoy(startNo: Int, countNum: Int, boyNums: Int): Unit ={
        addBoy(boyNums)
        if (header == null || startNo > boyNums || countNum <= 0){
            printf("参数有误，不能玩出圈游戏")
            return
        }

        var helper = header
        // 移动helper到开始的位置
        breakable{
            while (true){
                if (helper.next.no == startNo){
                    break()
                }
               helper = helper.next
            }
        }

        // 开始计数出圈
        breakable{
            while (true){
                for (i <- 1 to countNum-1){
                    // 移动指针
                    helper = helper.next
                }

                if (helper.next == helper){
                    printf("第 %d 个小孩出圈\n", helper.no)
                    break()
                }
                printf("第 %d 个小孩出圈\n", helper.next.no)
                // 去掉出圈的小孩
                helper.next = helper.next.next
            }
        }

    }

    /**
     * 创建 boyNums 个节点的单向环形链表
     * @param boyNums
     */
    def addBoy(boyNums: Int): Unit ={
        var curBoy: Boy = null
        for (i <- 1 to boyNums){
            val boy = new Boy(i)
            if (i == 1){
                curBoy = boy
                header = curBoy
            }
            curBoy.next = boy
            curBoy = boy
        }
        curBoy.next = header
    }

    def list(): Unit = {
        var temp = header
        breakable{
            while (true){
                if (temp == null || temp.no == 4){
                    break()
                }
                printf("第 %d 个小孩, ", temp.no)
                temp = temp.next
            }
        }
    }
}

class Boy(bNo: Int){
    val no = bNo
    var next: Boy = null
}