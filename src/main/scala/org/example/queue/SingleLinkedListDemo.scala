package org.example.queue

import util.control.Breaks._
import java.util

object SingleLinkedListDemo {

    def main(args: Array[String]): Unit = {
        val list = new SingleLinkedList
        list.add(new LinkedNode(1, "a"))
        list.add(new LinkedNode(2, "b"))
        list.add(new LinkedNode(3, "c"))
        list.add(new LinkedNode(4, "d"))
        list.list()
        println("修改2号数据: ")
        list.update(new LinkedNode(2, "bb"))
        list.list()

        println("逆序打印：")
        list.reversePrint()
        println()

        list.delete(new LinkedNode(1, ""))
        println("删除一号数据: ")
        list.list()
        list.delete(new LinkedNode(3, ""))
        list.delete(new LinkedNode(2, ""))
        list.delete(new LinkedNode(4, ""))
        list.list()
    }
}

class SingleLinkedList {

    // 创建头节点，指向链表头部
    val head = new LinkedNode(-1, "")

    /**
     * 使用栈，从尾到头打印单链表，同时不破坏链表本身的结构
     * 1. 遍历 单向链表，将节点push到Stack中
     * 2. 遍历 Stack，取出每个节点，输出信息
     */
    def reversePrint(): Unit = {
        if (isEmpty()) {
            println("链表空")
            return
        }
        // 创建栈
        val stack = new util.Stack[LinkedNode]()
        var temp = head.next
        // 遍历 单向链表，将节点push到Stack中
        breakable {
            while (true) {
                stack.push(temp)
                if (temp.next == null) {
                    break()
                }
                temp = temp.next
            }
        }
        // 遍历 Stack，取出每个节点，输出信息
        while (!stack.empty()){
            val node = stack.pop()
            printf("编号：%d, 名称：%s\t -> ", node.no, node.name)
        }
    }

    def add(node: LinkedNode): Unit = {
        var temp = head
        // 找到链表的尾部
        breakable {
            while (true) {
                if (null == temp.next) {
                    temp.next = node
                    break()
                }
                temp = temp.next
            }
        }
    }

    def update(node: LinkedNode): Unit = {
        if (isEmpty()) {
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
                if (temp.next == null) {
                    break()
                }
                temp = temp.next
            }
        }
    }

    def delete(node: LinkedNode): Unit = {
        if (isEmpty()) {
            println("链表为空，请添加数据")
            return
        }
        var temp = head
        breakable {
            while (true) {
                if (temp.next == null) {
                    break()
                }
                if (temp.next.no == node.no) {
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
        if (isEmpty()) {
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

class LinkedNode(hNo: Int, hName: String) {
    val no = hNo
    var name = hName
    var next: LinkedNode = null // 默认为null
}
