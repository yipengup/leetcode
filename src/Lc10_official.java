/**
 * @author yipengup
 * @date 2021/8/30
 */
public class Lc10_official {

    public static void main(String[] args) {
        System.out.println(new Lc10_official().isMatch("aab", "c*a*b"));
    }

    /**
     * <pre>
     *
     * f[i][j] i,j分别是匹配字符串s和匹配模式p的当前匹配的字符的索引 + 1()
     * 状态转移方程思路：
     *
     * f[i][j] =
     *
     *                              |   f[i-1][j-1] && s[i] == p[j]
     * p.charAt(j) == 普通字符串  --> |
     *                              |   false && s[i] != p[j]
     *
     *                       | f[i][j-2] 此时相当于 * 没有发挥作用, 直接将p左移一位继续匹配
     *                       | -- 此处注意的是， *号前面的字符在没有匹配的时候，是跟*一个整体， 所以对于p来说，应该从j=1开始匹配，而不是0
     *                       | -- 参照例子： s = "aab" p = "c*a*b" 返回true
     * p.charAt(j) == *  --> |
     *                       | f[i-1][j] && s[i] == p[j-1] 此处拿 * 号向前继续匹配s
     *
     *
     * @param s 匹配字符串
     * @param p 匹配模式
     * @return 匹配结果
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // 这里为了防止判断下标越界的问题，直接将空字符串设置为（0，0），此时s、p的开始索引为1
        boolean[][] f = new boolean[m + 1][n + 1];
        // 边界条件,当s和p都为空时返回真
        f[0][0] = true;
        // 此处的i = 0 和 j = 1是为了处理空字符串的问题
        for (int i = 0; i <= m; ++i) {
            // 如果能进入内部循环，那么模式p一定不为空，且p[0]绝对不是'*'
            // i,j分别要 - 1 才能真正获取到s和p的对应的字符
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        // 这里为什么 i == 0 的时候直接返回false呢？是因为这里的i == 0是为了处理s，p都为空产出的临时值
        // 实际真正的匹配要从 i == 1开始匹配
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
