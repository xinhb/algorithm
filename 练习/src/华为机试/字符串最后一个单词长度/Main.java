package 华为机试.字符串最后一个单词长度;

import java.util.Scanner;

/**
 * 思路：
 * 1、将字符串分割成字符串数组 split()方法
 * 2、取最后一个字符串，统计它的长度
 * */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputString = in.nextLine();

        System.out.println(inputString);

        String[] stringsArray = inputString.split(" ");
        int len = stringsArray.length;

        System.out.println(stringsArray[len -1].length());
    }



}
