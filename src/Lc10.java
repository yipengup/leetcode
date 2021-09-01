import java.util.regex.Pattern;

/**
 * 10. 正则表达式匹配
 * <p>
 * https://leetcode-cn.com/problems/regular-expression-matching/
 *
 * @author yipengup
 * @date 2021/8/26
 */
public class Lc10 {

    public static void main(String[] args) {

        System.out.println(new Lc10().isMatch("aa", "a"));
        System.out.println(new Lc10().isMatch("aa", "a*"));
        System.out.println(new Lc10().isMatch("ab", ".*"));
        System.out.println(new Lc10().isMatchDp("aab", "c*a*b"));
        System.out.println(new Lc10().isMatch("mississippi", "mis*is*p*."));
    }

    /**
     * 借用Java的正则表达式
     */
    public boolean isMatch(String s, String p) {

        // 都为空直接返回true
        if (s.equals("") && p.equals("")) {
            return true;
        }

        // 借用Java的正则表达式
        Pattern compile = Pattern.compile(p);
        return compile.matcher(s).matches();
    }

    /**
     * 采用动态
     * <pre>
     *
     * <b> f[i][j] i,j分别是匹配字符串s和匹配模式p的当前匹配的字符的索引 + 1
     *
     * 状态转移方程思路：
     * f[i][j] =
     *                                  |   f[i-1][j-1] && s[i - 1] == p[j - 1]
     * p.charAt(j - 1) == 普通字符串  --> |
     *                                  |   false && s[i - 1] != p[j - 1]
     *
     *                           | f[i][j-2] 此时相当于 （*和前面一个字符） 没有发挥作用 , 直接将p左移两位继续匹配
     *                           | -- 此处注意的是， *号前面的字符在没有匹配的时候，是跟*一个整体， 所以对于p来说，应该是左移两位
     *                           | -- 参照例子： s = "aab" p = "c*a*b" 返回true
     * p.charAt(j - 1) == *  --> |
     *                           | f[i-1][j] && s[i - 1] == p[j-2] 此处拿 * 号向前继续匹配s
     */
    boolean[][] dpArray;

    public boolean isMatchDp(String s, String p) {

        // 初始化动态规划数组（这里数组的长度要+1的原因就是为了兼容边界条件）
        // 所以数组中的索 = s，p的索引 + 1
        dpArray = new boolean[s.length() + 1][p.length() + 1];
        // 动态规划的边界条件（当两个字符串都为空时，返回true）
        dpArray[0][0] = true;
        return dp(s, p);
    }


    public boolean dp(String s, String p) {
        int maxSLength = s.length();
        int maxPLength = p.length();

        // 这里从s为空字符串匹配开始
        for (int i = 0; i <= maxSLength; i++) {
            for (int j = 1; j <= maxPLength; j++) {
                if (p.charAt(j - 1) == '*') {
                    // 当前字符是*
                    // 此处不匹配任何字符，直接忽略掉 *以及*前面的字符
                    dpArray[i][j] = dpArray[i][j - 2];
                    // 当 s.charAt(i - 1), p.charAt(j - 2) 相同时继续将s向前匹配
                    if (!dpArray[i][j] && match(s, p, i, j - 1)) {
                        dpArray[i][j] = dpArray[i - 1][j];
                    }
                } else {
                    // 当前字符是普通字符串
                    if (match(s, p, i, j)) {
                        dpArray[i][j] = dpArray[i - 1][j - 1];
                    }
                }
            }
        }

        return dpArray[maxSLength][maxPLength];
    }

    /**
     * 判断字符是否相等
     *
     * @return 匹配的结果
     */
    public boolean match(String s, String p, int i, int j) {
        if (i == 0) {
            // 这里i = 0 表示s当前匹配的空字符的场景，直接返回false
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }


}
