package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 约瑟夫环 的 java 3种 解法
 * @author lirong
 */
public class TestJosephu {

    public static void main(String[] args) {

        System.out.println(lastRemaining2(5, 3));
//        System.out.println(lastRemaining3(5, 3));
    }

    /**
     * n个数，第m个出圈，求最后留下的
     *
     * @param n
     * @param m
     * @return
     */
    public static int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        while (list.size() > 1) {
            // 给集合添加再移除，实现数数
            for (int j = 0; j < m; j++) {
                if (j != m - 1) {
                    list.add(list.get(0));
                }
                if (j == m - 1) {
                    System.out.println("出圈的数字：" + list.get(0));
                }
                list.remove(0);
            }
        }
        return list.get(0);
    }

    /**
     * 通过举例可以得出第一次删除的数字下标为(m-1)%n记为c，
     * 之后每一次删除的数字下标均为(c+m-1)%list.size()
     * @param n
     * @param m
     * @return
     */
    public static int lastRemaining2(int n, int m) {
        if (n == 0 || m == 0) {
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int c = (m - 1) % n;

        while (list.size() != 1) {
            System.out.println(list.get(c));
            list.remove(c);
            c = (c + m - 1) % list.size();
        }
        return list.get(0);
    }

    /**
     * 从最后的坐标倒推
     * @param n
     * @param m
     * @return
     */
    public static int lastRemaining3(int n, int m) {
        // 最后留在圈内的一定是最后一个，也就是下标为0的元素
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }
}
