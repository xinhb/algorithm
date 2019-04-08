package 字符串.字符串反转;

import java.util.Scanner;

/**
 * 解题思路：
 * String 的split()方法分割字符串后，逆序输出
 * <p>
 * 问题：
 * String类的nextLine()只能读取一行的输入
 */

public class Main {
    public static void main(String[] args) {
        //获取输入串
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入字符串");
        String strInput = sc.nextLine();

        String[] strs = strInput.split(" ");
        //创建StringBuilder对象，拼接字符串
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            int len = strs[i].length(); //每个串的长度
            if (strs[i].charAt(len - 1) == '.') {
                sb.append("." + strs[i].substring(0, len - 1) + " ");
            } else {
                sb.append(strs[i] + " ");
            }
        }
        System.out.println(sb);
        System.out.println("请输再入字符串");
    }
}
