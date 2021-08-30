/**
 *
 * 136. 只出现一次的数字
 *
 * https://leetcode-cn.com/problems/single-number/
 *
 * @author yipengup
 * @date 2021/8/26
 */
public class Lc136 {

    public static void main(String[] args) {
        Lc136 lc136 = new Lc136();
        System.out.println(lc136.singleNumber(new int[]{1, 2, 1}));
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
