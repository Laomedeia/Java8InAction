package leetcode.w01_findMissingOrder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 题目：假如一个订单的编码规则是AAAAOrder2020-0000001,AAAAOrder2020-0000002，后面的数字是自增长，如果订单号码达到AAAAOrder2020-1000000（一百万）时，数据库中应该有100万条数据，
 * 此时我随机删除两条数据（物理删除，且不考虑备份和日志），请问怎么找到删除的数据编码？给出解题思路，答案要求在1秒内运行得到。
 *
 * 原文链接：https://blog.csdn.net/qq_41623154/article/details/105082605
 * @author neptune
 * @create 2020 04 13 8:41 下午
 */
public class Solution {

    public static void main(String[] args) {
        ArrayList<String> num = new ArrayList<>();
        System.out.print("设置总数据量:");
        int total = new Scanner(System.in).nextInt();
        System.out.println("输入打算删除的两个数x，y");
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        int y = input.nextInt();
        int[] arr = new int[total - 2];
        for (int i = 0; i < total; i++) {//此处可以设置成存一百万条数据，用于模拟从数据库度过来的
            String str = "a-" + (i + 1);//类似格式“AAAAOrde-0000001”
            num.add(str);
        }
        if (x > y) {
            int temp = y;
            y = x;
            x = temp;
        }
        //删除指定的两个数
        num.remove(x - 1);
        num.remove(y - 2);
        long start = System.currentTimeMillis();
        for (int i = 0; i < (total - 2); i++) {
            arr[i] = Integer.parseInt(num.get(i).replace("a-", ""));
        }
        int flag =0;//用于标记找到了几个数，当flag=2时，退出循环，已经找到两个数。可以扩展到flag个数，删除flag个整数，然后进行查找
        for(int i=1;i<=total && flag!=2;i++){
            int res = find3(arr, i, flag);
            if(res != -1){
                System.out.println("删除的数字为："+res);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("消耗时间:" + (end - start) + "毫秒");
    }

    public static int find3(int[] a, int i, int flag) {
        int start = 0;
        int end = a.length;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (i < a[mid]) {
                end = mid - 1;
            } else if (i > a[mid]) {
                start = mid + 1;
            } else {
                return -1;//说明这个数存在
            }
        }
        flag++;
        return i;
    }
}


