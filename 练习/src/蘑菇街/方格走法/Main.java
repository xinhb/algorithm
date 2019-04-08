package 蘑菇街.方格走法;

import java.util.Scanner;

public class Main {


    static int sum(int m, int n)//递归
    {
        if (m == 1 || n == 1)
            return 1;
        int total = sum(m - 1, n) + sum(m, n - 1);
        return total;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();

        System.out.println(sum(m,n));
    }

}
