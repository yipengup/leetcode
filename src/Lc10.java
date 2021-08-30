import java.util.regex.Pattern;

/**
 * 10. 正则表达式匹配
 *
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
        System.out.println(new Lc10().isMatch("aab", "c*a*b"));
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



}
