/**
 * 7. 整数反转
 *
 * https://leetcode-cn.com/problems/reverse-integer/
 *
 * @author yipengup
 * @date 2021/8/24
 */
public class Test7 {

    public static void main(String[] args) {
        int x = -3;
        System.out.println(x % 10);
        // System.out.println(new Test7().reverse(x));
    }

    /**
     * 主要考虑两步：
     * 1、如何实现整数反转 （双指针）
     * 2、如何将超过整数范围的数返回0 （异常处理）
     *
     * @param x 待反转的带符号整数
     * @return 反转之后符号整数
     */
    public int reverse(int x) {

        // 对负数进行处理
        boolean negative = x < 0;
        if (negative) {
            x = -x;
        }

        // 利用双指针进行字符串反转(空间复杂度为0、时间负责度为O{n})
        String source = String.valueOf(x);
        char[] chars = source.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        String dest = String.valueOf(chars);


        // 利用异常处理超出整数范围的数
        try {
            int ret = Integer.parseInt(dest);
            return negative ? -ret : ret;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
