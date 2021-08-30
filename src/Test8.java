/**
 * 8.字符串转换整数 (atoi)
 *
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 *
 * @author yipengup
 * @date 2021/8/25
 */
public class Test8 {

    public static void main(String[] args) {
        // System.out.println(new Test8().myAtoi("42"));
        // System.out.println(new Test8().myAtoi("   -42"));
        // System.out.println(new Test8().myAtoi("+-12"));
        // System.out.println(new Test8().myAtoi("4193 with words"));
        // System.out.println(new Test8().myAtoi("words and 987"));
        // System.out.println(new Test8().myAtoi("-91283472332"));
        // System.out.println(new Test8().myAtoi("-91283472332"));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE / 10);
        System.out.println(Integer.MIN_VALUE % 10);
        System.out.println(Integer.MIN_VALUE);
        // System.out.println(new Test8().myAtoi2("-2147483648"));

    }

    /**
     * 将字符串转化成整数
     * 分析：（从左向右一次读取字符串）
     *
     * 1、去掉前面的空格
     * 2、判断+-
     * 3、判断是否是数字
     *
     * @param s 待处理的字符串
     * @return 转化后的整数
     */
    public int myAtoi(String s) {

        char[] source = s.toCharArray();
        // 定义整数的首指针
        int start = 0;
        int maxLength = source.length;

        // 过滤掉前置空格
        while (start < maxLength && source[start] == ' ') {
            start++;
        }
        // 全是空格
        if (start == maxLength) {
            return 0;
        }

        // 处理正负(这里start只能自增一次，防止 +- 都出现)
        boolean negative = false;
        if (source[start] == '+' || source[start] == '-') {
            if (source[start] == '-') {
                negative = true;
            }
            start++;
        }

        // 处理整数（只循环一次，时间复杂度为 O(n)), 空间复杂度为O(1)）
        int end = start;
        int ret = 0;
        while (end < maxLength && source[end] >= '0' && source[end] <= '9') {
            int i = source[end] - '0';
            if (negative) {
                // 负数 （-2147483648）
                int minValue = -(Integer.MIN_VALUE / 10);
                if (ret > minValue || (ret == minValue && i >= -(Integer.MIN_VALUE % 10))) {
                    // 超出范围
                    return Integer.MIN_VALUE;
                }
            } else {
                // 2147483647
                int maxValue = Integer.MAX_VALUE / 10;
                if (ret > maxValue || (ret == maxValue && i >= Integer.MAX_VALUE % 10)) {
                    // 超出范围
                    return Integer.MAX_VALUE;
                }
            }
            ret = ret * 10 + i;
            end++;
        }

        return negative ? -ret : ret;

    }

    public int myAtoi2(String str) {
        str = str.trim();//去掉前后的空格
        //如果为空，直接返回0
        if (str.length() == 0)
            return 0;
        int index = 0;//遍历字符串中字符的位置
        int res = 0;//最终结果
        int sign = 1;//符号，1是正数，-1是负数，默认为正数
        int length = str.length();
        //判断符号
        if (str.charAt(index) == '-' || str.charAt(index) == '+')
            sign = str.charAt(index++) == '+' ? 1 : -1;
        for (; index < length; ++index) {
            //取出字符串中字符，然后转化为数字
            int digit = str.charAt(index) - '0';
            //按照题中的要求，读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。
            //字符串的其余部分将被忽略。如果读取了非数字，后面的都要忽略
            if (digit < 0 || digit > 9)
                break;
            //越界处理
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10))
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            else
                res = res * 10 + digit;
        }
        return sign * res;
    }
}
