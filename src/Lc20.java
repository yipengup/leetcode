import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 20. 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author yipengup
 * @date 2021/9/29
 */
public class Lc20 {

    public static void main(String[] args) {
        System.out.println(new Lc20().isValid("([)]"));
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效
     * 采用栈的方式实现，遇到左括号入栈，右括号就出栈，如果不匹配就视为无效
     *
     * @param s 给定的字符串 1 <= s.length <= 104
     * @return 是否符合格式
     */
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                // 左括号入栈
                stack.push(c);
            } else {
                // 出栈
                if (stack.size() == 0) {
                    return false;
                }
                Character leftBracket = stack.pop();
                if (leftBracket != getLeftBracket(c)) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    /**
     * 根据右括号获取到左括号
     */
    private char getLeftBracket(char rightBracket) {
        switch (rightBracket) {
            case ')':
                return '(';
            case '}':
                return '{';
            default:
                return '[';
        }
    }
}
