/**
 * 509. 斐波那契数
 * https://leetcode-cn.com/problems/fibonacci-number/
 *
 * @author yipengup
 * @date 2021/8/27
 */
public class Test509 {

    public static void main(String[] args) {

        System.out.println(new Test509().fib(2));
        System.out.println(new Test509().fib(3));
        System.out.println(new Test509().fib(4));
    }

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        // 前一个值
        int pre = 0;
        // 当前值
        int current = 1;
        for (int i = 2; i <= n; i++) {
            int temp = pre;
            pre = current;
            current += temp;
        }
        return current;
    }
}
