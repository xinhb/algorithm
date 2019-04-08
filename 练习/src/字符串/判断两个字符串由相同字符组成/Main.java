package 字符串.判断两个字符串由相同字符组成;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 解题思路：
 * 先排序，再比较
 * */
public class Main {

    public static void main(String[] args) {
        //输入两个字符串
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();

        //字符串转为数组，用Array类的排序算法
        byte[] c1 = str1.getBytes();
        Arrays.sort(c1);
        byte[] c2 = str2.getBytes();
        Arrays.sort(c2);

        //排序后再比较
        String s1 = new String(c1);
        String s2 = new String(c2);
        if (s1.equals(s2)) {
            System.out.println("字符相同");
        } else {
            System.out.println("字符不同");
        }

    }
}
