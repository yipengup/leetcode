/**
 * 11. 盛最多水的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
 * <p>
 *
 * @author yipengup
 * @date 2021/9/1
 */
public class Lc11 {

    public static void main(String[] args) {
        System.out.println(new Lc11().maxAreaDoublePoint(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(new Lc11().maxAreaDoublePoint(new int[]{1, 1}));
        System.out.println(new Lc11().maxAreaDoublePoint(new int[]{4, 3, 2, 1, 4}));
        System.out.println(new Lc11().maxAreaDoublePoint(new int[]{1, 2, 1}));
    }

    /**
     * 容器的最大容量
     * <p>
     * height.length >= 2
     * <p>
     * 解题思路：采用暴力破解法，
     * 两次嵌套循环计算出 height[i]~height[j] 容器的盛水量
     * <p>
     * 时间复杂度 O(n^2)
     *
     * @param height 纵坐标列表
     * @return 容器的最大容量
     */
    public int maxArea(int[] height) {

        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int minHeight = Math.min(height[i], height[j]);
                max = Math.max(max, minHeight * (j - i));
            }
        }
        return max;
    }

    /**
     * 容器的最大容量
     * <p>
     * height.length >= 2
     * <p>
     * 解题思路：采用双指针
     * 双指针代表容器的边界，每次将边界小的往里面移动
     * <p>
     * 时间复杂度 O(n)
     *
     * @param height 纵坐标列表
     * @return 容器的最大容量
     */
    public int maxAreaDoublePoint(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int max = 0;
        while (start < end) {
            max = Math.max(max, Math.min(height[start], height[end]) * (end - start));
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return max;
    }
}
