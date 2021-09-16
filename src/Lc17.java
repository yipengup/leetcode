import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * <p>
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * @author yipengup
 * @date 2021/9/9
 */
public class Lc17 {

    public static void main(String[] args) {
        System.out.println(new Lc17().letterCombinations("23"));
        System.out.println(new Lc17().letterCombinations(""));
        System.out.println(new Lc17().letterCombinations("2"));
    }

    /**
     * 电话号码的字母组合
     *
     * @param digits 仅包含数字 2-9 的字符串， 0 <= digits.length <= 4
     * @return 电话号码字母组合列表
     */
    public List<String> letterCombinations(String digits) {

        // 处理边界事件
        if (digits.length() == 0) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        // 初始化
        init(digits, result);
        for (int i = 1; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            char[] letters = getLettersByDigit(digit);
            // 扩充前大小
            int addPreSize = result.size();
            for (int j = 0; j < addPreSize; j++) {
                String tmp = result.get(j);
                for (char letter : letters) {
                    result.add(tmp + letter);
                }
            }
            result = result.subList(addPreSize, result.size());
        }

        return result;

    }

    private void init(String digits, List<String> result) {
        int digit = digits.charAt(0) - '0';
        char[] letters = getLettersByDigit(digit);
        for (char letter : letters) {
            result.add("" + letter);
        }
    }

    private char[] getLettersByDigit(int digit) {
        switch (digit) {
            case 2:
                return new char[]{'a', 'b', 'c'};
            case 3:
                return new char[]{'d', 'e', 'f'};
            case 4:
                return new char[]{'g', 'h', 'i'};
            case 5:
                return new char[]{'j', 'k', 'l'};
            case 6:
                return new char[]{'m', 'n', 'o'};
            case 7:
                return new char[]{'p', 'q', 'r', 's'};
            case 8:
                return new char[]{'t', 'u', 'v'};
            default:
                // 9
                return new char[]{'w', 'x', 'y', 'z'};
        }
    }
}
