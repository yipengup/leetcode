/**
 *
 * 136. 只出现一次的数字
 *
 * https://leetcode-cn.com/problems/single-number/
 *
 * @author yipengup
 * @date 2021/8/26
 */
public class Test136 {

    public static void main(String[] args) {
        Test136 test136 = new Test136();
        System.out.println(test136.singleNumber(new int[]{1, 2, 1}));
    }

    /**
     * 非空整数数组
     * 直接采用 ^ 位运算符
     * 当相同的两个数进行异或时，就会被抵消
     */
    public int singleNumber(int[] nums) {

        int ret = 0;
        for (int num : nums) {
            ret ^= num;
        }
        return ret;
    }
}
