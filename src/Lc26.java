/**
 * 26. 删除有序数组中的重复项
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 * @author yipengup
 * @date 2021/10/28
 */
public class Lc26 {

    /**
     * 删除有序数组中的重复项
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 0 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums 已按升序排列
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        // 记录上一个元素，如果当前元素和上一个元素不相同就+1
        int count = 1;
        int pre = nums[0];
        for (int num : nums) {
            if (num != pre) {
                nums[count] = num;
                count++;
                pre = num;
            }
        }

        return count;
    }
}
