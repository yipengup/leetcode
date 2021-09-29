import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * @author yipengup
 * @date 2021/9/29
 */
public class Lc22 {

    public static void main(String[] args) {
        System.out.println(new Lc22().generateParenthesisByBackTrace(1));
        System.out.println(new Lc22().generateParenthesisByBackTrace(2));
        System.out.println(new Lc22().generateParenthesisByBackTrace(3));
        System.out.println(new Lc22().generateParenthesisByBackTrace(4));
    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     * 有效括号组合需满足：左括号必须以正确的顺序闭合。
     * <p>
     * 条件：1 <= n <= 8
     * <p>
     * 解法思路：采用递归的方式处理
     *
     * @param n 数字 n 代表生成括号的对数
     * @return 所有可能的并且 有效的 括号组合
     */
    public List<String> generateParenthesis(int n) {
        // 寻找边界条件
        if (n == 1) {
            return Collections.singletonList("()");
        }
        // 用于存储结果并去重
        HashSet<String> set = new HashSet<>();
        generateParenthesis(n - 1).forEach(brackets -> {
            // 对于每一个结果，可以把一对括号插在每一对符号间隙
            for (int i = 0; i < brackets.length(); i++) {
                set.add(brackets.substring(0, i) + "()" + brackets.substring(i));
            }
        });
        return new ArrayList<>(set);
    }

    /**
     * 采用回溯算法来实现
     * 分析：n对括号代表 n个左括号 + n个右括号, 还需要注意的是在填充过程中左括号的个数一定是大于等于右括号
     */
    public List<String> generateParenthesisByBackTrace(int n) {
        ArrayList<String> result = new ArrayList<String>();
        backTrace(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    /**
     * @param result  用于存储结果的列表
     * @param combine 当前拼接的字符串
     * @param open    当前左括号的个数
     * @param close   当前右括号的个数
     * @param n       当前n对括号
     */
    public void backTrace(List<String> result, StringBuilder combine, int open, int close, int n) {
        if (combine.length() == n * 2) {
            result.add(combine.toString());
            return;
        }
        if (open < n) {
            // 表示左括号的个数小于n那么就需要添加左括号
            combine.append("(");
            backTrace(result, combine, open + 1, close, n);
            combine.deleteCharAt(combine.length() - 1);
        }
        if (close < open) {
            // 表示右括号的个数小于左括号那么就需要添加右括号
            combine.append(")");
            backTrace(result, combine, open, close + 1, n);
            combine.deleteCharAt(combine.length() - 1);
        }
    }

}
