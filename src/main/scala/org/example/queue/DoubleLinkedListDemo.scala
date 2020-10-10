package org.example.queue

import scala.util.control.Breaks.{break, breakable}

object DoubleLinkedListDemo {
    def main(args: Array[String]): Unit = {
        val list = new DoubleLinkedList
        println("添加1号数据: ")
        list.add(new LinkedNode2(1, "a"))
        list.list()
        list.add(new LinkedNode2(2, "b"))
        list.add(new LinkedNode2(3, "c"))
        list.add(new LinkedNode2(4, "d"))
        println("遍历所有: ")
        list.list()
        println("修改2号数据: ")
        list.update(new LinkedNode2(2, "bb"))
        list.list()
        println("删除一号数据: ")
        list.delete(new LinkedNode2(1, ""))
        list.list()
        list.delete(new LinkedNode2(3, ""))
        list.delete(new LinkedNode2(2, ""))
        list.delete(new LinkedNode2(4, ""))
        println("删除所有数据: ")
        list.list()
    }
}

class DoubleLinkedList {

    // 创建头节点，指向链表头部
    val head = new LinkedNode2(-1, "")

    def add(node: LinkedNode2): Unit = {
        var temp = head
        // 找到链表的尾部
        breakable {
            while (true) {
                if (null == temp.next) {
                    node.pre = temp
                    temp.next = node
                    break()
                }
                temp = temp.next
            }
        }
    }

    def update(node:LinkedNode2): Unit = {
        if (isEmpty()){
            println("链表为空，请添加数据")
            return
        }
        var temp = head.next
        breakable {
            while (true) {
                if (temp.no == node.no) {
                    temp.name = node.name
                    break()
                }
                if (temp.next == null){
                    break()
                }
                temp = temp.next
            }
        }
    }

    def delete(node: LinkedNode2):Unit = {
        if (isEmpty()){
            println("链表为空，请添加数据")
            return
        }
        var temp = head
        breakable {
            while (true) {
                if (temp.next == null){
                    break()
                }
                if (temp.next.no == node.no) {
                    if (temp.next.next == null){
                        temp.next = null
                        break()
                    }
                    temp.next.next.pre = temp.next.pre
                    temp.next = temp.next.next
                    break()
                }
                temp = temp.next
            }
        }

    }

    def isEmpty(): Boolean = {
        head.next == null
    }

    def list(): Unit = {
        if (isEmpty()){
            println("链表为空，请添加数据")
            return
        }
        var temp = head.next
        breakable {
            while (true) {
                printf("编号：%d, 名称：%s\t", temp.no, temp.name)
                if (null == temp.next) {
                    break()
                }
                temp = temp.next
            }
        }
        println()
    }

}

class LinkedNode2(var hNo: Int, var hName: String) {
    val no = hNo
    var name = hName
    var next: LinkedNode2 = null // 默认为null
    var pre: LinkedNode2 = null // 默认为null
}
