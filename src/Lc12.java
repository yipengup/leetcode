import java.util.HashMap;
import java.util.Map;

/**
 * 12. 整数转罗马数字
 * https://leetcode-cn.com/problems/integer-to-roman/
 * <p>
 * 输入: num = 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * @author yipengup
 * @date 2021/9/2
 */
public class Lc12 {

    public static void main(String[] args) {
        System.out.println(new Lc12().intToRoman(3));
        System.out.println(new Lc12().intToRoman(4));
        System.out.println(new Lc12().intToRoman(5));
        System.out.println(new Lc12().intToRoman(9));
        System.out.println(new Lc12().intToRoman(58));
        System.out.println(new Lc12().intToRoman(1994));
    }

    /**
     * 将整数转成罗马数字
     *
     * @param num 整数 （1 <= num <= 3999）
     * @return 罗马数字
     */
    public String intToRoman(int num) {
        Map<Integer, Character> charMap = initInt2CharMap();
        StringBuilder builder = new StringBuilder();
        int[] types = {1000, 500, 100, 50, 10, 5, 1};
        // 记录上一个状态是否超过
        boolean lastStatus = false;
        for (int i = 0; i < types.length; i++) {
            int type = types[i];
            int divide = num / type;
            if (divide == 0) {
                lastStatus = false;
                continue;
            }
            Character character = charMap.get(type);
            if (divide == 4) {
                if (lastStatus) {
                    // 表示上一个超过了9， 删掉最后一位后，分别添加当前字符和10倍字符
                    builder.deleteCharAt(builder.length() - 1);
                    builder.append(character);
                    builder.append(charMap.get(types[i - 2]));
                } else {
                    // 此时就是纯粹的4，分别添加当前字符和5倍字符
                    builder.append(character);
                    builder.append(charMap.get(types[i - 1]));
                }
                lastStatus = false;
            } else {
                // 其他情况，直接插多个
                for (int j = 0; j < divide; j++) {
                    builder.append(character);
                }
                lastStatus = true;
            }

            num -= divide * type;
        }
        return builder.toString();
    }

    public Map<Integer, Character> initInt2CharMap() {
        HashMap<Integer, Character> map = new HashMap<>();
        map.put(1, 'I');
        map.put(5, 'V');
        map.put(10, 'X');
        map.put(50, 'L');
        map.put(100, 'C');
        map.put(500, 'D');
        map.put(1000, 'M');
        return map;
    }
}
