import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * https://leetcode-cn.com/problems/3sum-closest/
 *
 * @author yipengup
 * @date 2021/9/7
 */
public class Lc16 {

    public static void main(String[] args) {
        System.out.println(new Lc16().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }

    /**
     * 最接近的三数之和
     *
     * @param nums   整数的数组 nums (3 <= nums.length <= 10^3)
     * @param target 参考的整数
     * @return 最接近的三数之和
     */
    public int threeSumClosest(int[] nums, int target) {

        int result = Integer.MAX_VALUE;
        // 先进行排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // 如果两个数相同的话，这种情况已经在上一步计算过了
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int temp = nums[i] + nums[left] + nums[right];
                if (temp == target) {
                    // 因为每组输出只存在一种答案，直接返回
                    return target;
                } else if (temp < target) {
                    // 当临时值小得时候，将左边的指针往右边移动， 此时越靠近target
                    left++;
                } else {
                    // 当临时值大得时候，将右边的指针往左边移动， 此时越靠近target
                    right--;
                }
                // 这里就更新差值
                int diff = temp - target;
                if (Math.abs(diff) < Math.abs(result)) {
                    result = diff;
                }
            }
        }

        return target + result;
    }
}
