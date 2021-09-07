import java.util.*;

/**
 * 15. 三数之和
 * <p>
 * https://leetcode-cn.com/problems/3sum/
 * <p>
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 *
 * @author yipengup
 * @date 2021/9/6
 */
public class Lc15 {

    public static void main(String[] args) {

        System.out.println(new Lc15().threeSumRefer(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(new Lc15().threeSumRefer(new int[]{}));
        System.out.println(new Lc15().threeSumRefer(new int[]{0}));

    }


    /**
     * 返回整数数组中三数之和为0的列表
     *
     * @param nums n个整数的数组 nums, 0 <= nums.length <= 3000
     * @return 三数之和为0的列表
     */
    public List<List<Integer>> threeSumRefer(int[] nums) {
        if (nums.length < 3) {
            return Collections.emptyList();
        }

        // 排序
        nums = Arrays.stream(nums).sorted().toArray();
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {

            // 三个正数不可能等于0，此时后面都没有结果了，直接返回
            if (nums[i] > 0) {
                return result;
            }

            // 去重，如果当前元素和前一个元素相同， 所有的可能都已经加入到结果集中了
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {

                if (nums[i] + nums[left] + nums[right] == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 此时需要同时移动左右两边的值，因为移动一个的话， 要么重复，要么不可能 == 0
                    // 针对左边的值和右边的值去重
                    while (left < right && nums[left] == nums[++left]) {

                    }
                    while (left < right && nums[right] == nums[--right]) {

                    }
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    // 小于0。此时说明左边的值比较小，需要向右边移动
                    left++;
                } else {
                    // 大于0，此时说明右边的值比较大，需要向左边移动
                    right--;
                }
            }

        }

        return result;
    }

    /**
     * 返回整数数组中三数之和为0的列表
     *
     * @param nums n个整数的数组 nums, 0 <= nums.length <= 3000
     * @return 三数之和为0的列表
     */
    public List<List<Integer>> threeSum(int[] nums) {

        if (nums.length <= 1) {
            return Collections.emptyList();
        }

        ArrayList<List<Integer>> result = new ArrayList<>();
        HashSet<String> keys = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int i1 = i + 1; i1 < nums.length - 1; i1++) {
                for (int i2 = i1 + 1; i2 < nums.length; i2++) {
                    int a = nums[i];
                    int b = nums[i1];
                    int c = nums[i2];
                    if (a + b + c == 0) {
                        exist(Arrays.asList(a, b, c), keys, result);
                    }
                }
            }
        }


        return result;
    }

    /**
     * 判断是否包含三数字和为0的列表
     *
     * @param matchList  三数之和为0的列表
     * @param keys       通过特殊方式组合成key之后列表为0的集合
     * @param resultList 整数数组中三数之和为0的列表
     */
    private void exist(List<Integer> matchList, HashSet<String> keys, List<List<Integer>> resultList) {
        matchList.sort(Integer::compareTo);
        StringBuilder builder = new StringBuilder();
        matchList.forEach(i -> builder.append(i).append(","));
        if (!keys.contains(builder.toString())) {
            keys.add(builder.toString());
            resultList.add(matchList);
        }
    }

}
