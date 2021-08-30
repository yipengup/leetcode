/**
 *
 * 9. 回文数
 * https://leetcode-cn.com/problems/palindrome-number/
 *
 * @author yipengup
 * @date 2021/8/26
 */
public class Lc9 {

    public static void main(String[] args) {
        Lc9 lc9 = new Lc9();
        System.out.println(lc9.isPalindrome(0));
        System.out.println(lc9.isPalindrome(-1));
        System.out.println(lc9.isPalindrome(1));
        System.out.println(lc9.isPalindrome(11));
        System.out.println(lc9.isPalindrome(-11));
        System.out.println(lc9.isPalindrome(121));
        System.out.println(lc9.isPalindrome(-121));
        System.out.println(lc9.isPalindrome( 10));
        System.out.println(lc9.isPalindrome( -101));
    }

    /**
     * 判断是否回文，直接收尾指针比较即可
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String s = String.valueOf(x);
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    class Solution {
        public boolean isPalindrome(int x) {
            int rev = 0;
            int num = x;
            if (x < 0) {
                return false;
            }
            while (x != 0) {
                int digit = x % 10;
                x /= 10;
                rev = rev * 10 + digit;
            }
            if (num == rev) {
                return true;
            }

            return false;

        }
    }


}
