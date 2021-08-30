import java.util.HashMap;
import java.util.Map;

/**
 * 322. 零钱兑换
 *
 * https://leetcode-cn.com/problems/coin-change/
 * https://labuladong.gitee.io/algo/3/24/60/
 *
 * @author yipengup
 * @date 2021/8/27
 */
public class Test322 {

    public static void main(String[] args) {
        System.out.println(new Test322().coinChange(new int[]{1,2,5}, 11));
        System.out.println(new Test322().coinChange(new int[]{2}, 3));
        System.out.println(new Test322().coinChange(new int[]{1}, 0));
        System.out.println(new Test322().coinChange(new int[]{1}, 1));
        System.out.println(new Test322().coinChange(new int[]{1}, 2));
    }

    /**
     * 零钱兑换
     * 动态规划：
     * 1、重复子问题
     * 2、最优子结构 ， 子结构间相互独立，互不影响。
     * 3、状态转移方程
     *
     * 解决步骤：
     *
     * 明确基础事件 => 目标金额 amount 为 0 时算法返回 0
     * => 明确状态 （原问题和子问题中会变化的变量）=> 唯一的「状态」就是目标金额 amount
     * => 明确选择 （导致「状态」产生变化的行为） => 硬币的面值，就是你的「选择」
     * => 定义动态数组 or 函数 =>
     *
     *      暴力破解（自顶向下）递归函数：
     *          一般来说函数的参数就是状态转移中会变化的量，也就是上面说到的「状态」,函数的返回值就是题目要求我们计算的量
     *
     *      动态规划数组（自底向上）
     *          数组的索引就是状态转移中会变化的量，也就是上面说到的「状态」， 数组的值就是题目要求我们计算的量
     *
     *          当目标金额为 i 时，至少需要 dp[i] 枚硬币凑出。
     *
     *
     * @param coins 表示不同面额的硬币
     * @param amount 零钱的总金额
     * @return 最少硬币个数
     */
    public int coinChange(int[] coins, int amount) {
        // 初始化一个Map集合用作备忘录(金额步骤映射表)
        Map<Integer, Integer> amountStepMap = new HashMap<>();
        return dp(coins, amount, amountStepMap);
    }

    /**
     * 采用递归 自顶向下 进行选择
     *
     * @param coins  表示不同面额的硬币
     * @param amount 零钱的总金额
     * @return 最少硬币个数
     */
    private int dp(int[] coins, int amount, Map<Integer, Integer> amountStepMap) {
        // 基础事件
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return -1;
        }

        // 如果已经存在直接返回即可
        if (amountStepMap.containsKey(amount)) {
            return amountStepMap.get(amount);
        }

        int ret = Integer.MAX_VALUE;
        // 从硬币中随机选出一个
        for (int coin : coins) {
            // 取出一个后剩余的最优解
            int remainStep = dp(coins, amount - coin, amountStepMap);
            // 此时剩余金额异常
            if (remainStep == -1) {
                continue;
            }
            ret = Math.min(ret, remainStep + 1);
        }

        if (ret == Integer.MAX_VALUE) {
            ret = -1;
        }

        // 将结果存到备忘录
        amountStepMap.put(amount, ret);
        return ret;
    }
}
