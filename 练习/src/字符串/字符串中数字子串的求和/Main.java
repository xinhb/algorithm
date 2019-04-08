package 字符串.字符串中数字子串的求和;

import java.util.Scanner;

/**
 * 需求：
 * 1、忽略其他字符、只取数字计算
 * 2、若数字子串出现字符"-",奇数个数时，数字视为负数；偶数个数时，视为正数。
 * <p>
 * 参考用例：
 * 1、str = "A1CD2E33" ,返回36。
 * <p>
 * 2、str = "A-1B--2C--D6E"，返回7
 */

/**
 * 解题思路：
 * 1、遍历单个字符，组装成数字
 * 2、判断组装的数字是正数还是负数
 */

public class Main {
    public static void main(String[] args) {
        //接收输入的字符串
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        //转为数组处理
        char[] charArr = str.toCharArray();
        //设置标志位
        boolean posi = true;
        //设置统计变量：数字串的和、收集到数字、当前遍历字符
        int res = 0;
        int num = 0;
        int cur = 0;
        //遍历输入串中的每一个字符，收集数字，并求和
        for (int i = 0; i < charArr.length; i++) {
            cur = charArr[i] - '0'; //取到对应数字，非ASCII码
            if (cur >= 0 && cur <= 9) {
                num = num * 10 + (posi ? cur : -cur);
            } else {
                res = res + num;
                num = 0;
                //判断当前遍历字符是'-'
                if (charArr[i] == '-') {
                    //同时还要判断当前'-'的前一个字符
                    if (i - 1 > -1 && charArr[i - 1] == '-') {
                        posi = !posi;
                    } else {
                        posi = false;
                    }
                } else {
                    posi = true;
                }
            }
        }
        res = res +num;
        System.out.println(res);
    }
}
