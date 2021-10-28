/**
 * 27. 移除元素
 * https://leetcode-cn.com/problems/remove-element/
 *
 * @author yipengup
 * @date 2021/10/28
 */
public class Lc27 {

    /**
     * 27. 移除元素
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * <p>
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 50
     * 0 <= val <= 100
     */
    public int removeElement(int[] nums, int val) {

        if (nums.length == 0) {
            return 0;
        }

        // 记录不重复的个数
        int count = 0;
        for (int num : nums) {
            if (num != val) {
                nums[count] = num;
                count++;
            }
        }

        return count;
    }
}
