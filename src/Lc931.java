import java.util.Arrays;

/**
 * 931. 下降路径最小和
 * https://leetcode-cn.com/problems/minimum-falling-path-sum/
 *
 * @author yipengup
 * @date 2021/8/28
 */
public class Lc931 {

    public static void main(String[] args) {
        System.out.println(new Lc931().minFallingPathSum(new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}}));
        System.out.println(new Lc931().minFallingPathSum(new int[][]{{-19, 57}, {-40, -5}}));
        System.out.println(new Lc931().minFallingPathSum(new int[][]{{-48}}));
    }


    /**
     * 最小路径和
     *
     * @param matrix 二维数组
     * @return 最小路径和
     */
    public int minFallingPathSum(int[][] matrix) {
        // 确定第一行出发的位置
        int ret = Integer.MAX_VALUE;
        // 初始化数组，用于存储当前位置最小路径和
        int[][] dpArray = new int[matrix.length][matrix[0].length];
        for (int[] ints : dpArray) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        for (int i = 0; i < matrix[0].length; i++) {
            ret = Math.min(ret, dp(0, i, matrix, dpArray));
        }
        return ret;
    }

    /**
     * 递归函数（由顶向下）
     * <p>
     * 状态：每次移动会改变横纵坐标， 函数参数
     * 行为：最小路径和，函数返回值
     *
     * @param row     当前横坐标
     * @param col     当前纵坐标
     * @param matrix  原数组
     * @param dpArray 动态规划数组
     * @return 当前横纵坐标下的最小路径和
     */
    private int dp(int row, int col, int[][] matrix, int[][] dpArray) {

        // 计算出最大的横纵坐标
        int maxRowIndex = matrix.length - 1;
        int maxColIndex = matrix[maxRowIndex].length - 1;

        // 当前值标记为无效
        if (col < 0 || col > maxColIndex) {
            return Integer.MAX_VALUE;
        }

        // 确定基础事件
        if (row == matrix.length - 1) {
            // 当前已经处于最后一层
            return matrix[row][col];
        }

        // 如果结果已经计算出来了， 直接返回
        if (dpArray[row][col] != Integer.MAX_VALUE) {
            return dpArray[row][col];
        }

        // 当前有三种移动方法
        int ret = Integer.MAX_VALUE;
        int first = Math.min(ret, dp(row + 1, col - 1, matrix, dpArray));
        int second = Math.min(first, dp(row + 1, col, matrix, dpArray));
        ret = Math.min(second, dp(row + 1, col + 1, matrix, dpArray)) + matrix[row][col];

        // 将结果保存到备忘录中
        dpArray[row][col] = ret;
        return ret;
    }


}
