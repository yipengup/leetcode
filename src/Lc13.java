import java.util.HashMap;
import java.util.Map;

/**
 * 13. 罗马数字转整数
 * https://leetcode-cn.com/problems/roman-to-integer/
 *
 * @author yipengup
 * @date 2021/9/3
 */
public class Lc13 {

    public static void main(String[] args) {
        System.out.println(new Lc13().romanToInt("III"));
        System.out.println(new Lc13().romanToInt("IV"));
        System.out.println(new Lc13().romanToInt("IX"));
        System.out.println(new Lc13().romanToInt("LVIII"));
        System.out.println(new Lc13().romanToInt("MCMXCIV"));
    }

    /**
     * 罗马数字转整数
     *
     * @param s 罗马数字 1 <= s.length <= 15
     * @return 转后的整数
     */
    public int romanToInt(String s) {

        Map<String, Integer> initRomanNumMap = initRomanNumMap();

        int result = 0;
        int i;
        for (i = 0; i < s.length() - 1; i++) {
            char cur = s.charAt(i);
            char next = s.charAt(i + 1);
            String key = String.valueOf(cur);
            if (cur == next) {
                result += initRomanNumMap.get(key);
            } else if (initRomanNumMap.containsKey(key + next)) {
                // 包含
                result += initRomanNumMap.get(key + next);
                i++;
            } else {
                result += initRomanNumMap.get(key);
            }
        }
        if (i <= s.length() - 1) {
            // 最后一个字符没有被合并
            return result + initRomanNumMap.get(String.valueOf(s.charAt(s.length() - 1)));
        }
        return result;
    }

    private Map<String, Integer> initRomanNumMap() {
        HashMap<String, Integer> roman2NumMap = new HashMap<>(16);
        roman2NumMap.put("M", 1000);
        roman2NumMap.put("CM", 900);
        roman2NumMap.put("D", 500);
        roman2NumMap.put("CD", 400);
        roman2NumMap.put("C", 100);
        roman2NumMap.put("XC", 90);
        roman2NumMap.put("L", 50);
        roman2NumMap.put("XL", 40);
        roman2NumMap.put("X", 10);
        roman2NumMap.put("IX", 9);
        roman2NumMap.put("V", 5);
        roman2NumMap.put("IV", 4);
        roman2NumMap.put("I", 1);
        return roman2NumMap;
    }
}
