package 判断数组是否有重复元素;

/**
 * @author light
 * @data ${DATA} 21:55
 */
public class RepeatArray {

    public static boolean isRepeat(char[] arr) {
        /**
         * 最优的方法：用一个char数组
         * */
        char[] chars = new char[128];
        for (int i = 0; i < arr.length; i++) {
            chars[arr[i]] ++;
            if (chars[arr[i]] > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两个串是变形串
     * 即：两个串的种类和个数相同
     */
    public static boolean isSameString(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int[] map = new int[256];
        for (int i = 0; i < c1.length; i++) {
            map[c1[i]]++;
        }
        for (int i = 0; i < c2.length; i++) {
            //先判断当前下标处元素是否为零
            if (map[c2[i]] == 0) {
                return false;
            }
            map[c2[i]]--;
        }
        return true;
    }

    public static void main(String[] args) {
        char[] arr = {1, 3, 4, 5};
        System.out.println(isRepeat(arr));
        String str1 = "123";
        String str2 = "341";
        System.out.println(isSameString(str1,str2));
    }
}
