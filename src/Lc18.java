import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 18. 四数之和
 * https://leetcode-cn.com/problems/4sum/
 *
 * @author yipengup
 * @date 2021/9/22
 */
public class Lc18 {

    public static void main(String[] args) {
        System.out.println(new Lc18().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(new Lc18().fourSum(new int[]{2, 2, 2, 2, 2}, 8));
        System.out.println(new Lc18().fourSum(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11));
    }

    /**
     * 四数之和
     *
     * @param nums   1 <= nums.length <= 200 ，-109 <= nums[i] <= 109
     * @param target -109 <= target <= 109
     * @return nums[a] + nums[b] + nums[c] + nums[d] == target 条件列表
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int length = nums.length;
        // 边界条件
        if (length < 4) {
            return Collections.emptyList();
        }
        ArrayList<List<Integer>> result = new ArrayList<>();
        // 首先按照自然排序
        Arrays.sort(nums);
        // 首先进行两层循环，然后三层用双指针进行处理，类似于三数字和
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 如果左边的数和最大的三个数相加都要小于目标值，当前直接跳过， 将左值变大
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            // 如果前面最小的四个数都要比目标值大，那么后面情况就不用考虑了， 直接结束
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            for (int i1 = i + 1; i1 < length - 2; i1++) {
                if (i1 > i + 1 && nums[i1] == nums[i1 - 1]) {
                    continue;
                }
                // 如果左边的数和最大的两个数相加都要小于目标值，当前直接跳过， 将左值变大
                if (nums[i] + nums[i1] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                // 如果前面最小的四个数都要比目标值大，那么后面情况就不用考虑了， 直接结束
                if (nums[i] + nums[i1] + nums[i1 + 1] + nums[i1 + 2] > target) {
                    break;
                }
                int head = i1 + 1;
                int end = length - 1;
                while (head < end) {
                    // 此时如果当前的head和前一个head相同，那么此种情况下所有的情况已经验证了，直接忽略
                    if (head > i1 + 1 && nums[head] == nums[head - 1]) {
                        head++;
                        continue;
                    }
                    // 如果当前的end和后一个end相同，此种情况下所有的情况已经验证了。直接忽略
                    if (end < length - 1 && nums[end] == nums[end + 1]) {
                        end--;
                        continue;
                    }
                    int tmp = nums[i] + nums[i1] + nums[head] + nums[end];
                    if (tmp == target) {
                        result.add(Arrays.asList(nums[i], nums[i1], nums[head], nums[end]));
                        head++;
                        end--;
                    } else if (tmp < target) {
                        // 小于的话就需要将head向右移动，结果才会靠近target
                        head++;
                    } else {
                        end--;
                    }
                }
            }
        }
        return result;
    }
}
