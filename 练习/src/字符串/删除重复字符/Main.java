package 字符串.删除重复字符;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 利用集合TreeSet的元素不重复和有序性
 */

public class Main {

    //输入字符串
    Scanner sc = new Scanner(System.in);
    String s = sc.next();

    //字符串转为数组
    char[] chars = s.toCharArray();
    //利用TreeSet去重
    Set<Character> set = new TreeSet();



}
