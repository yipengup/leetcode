import java.util.*;

/**
 * 采用官方的回溯算法
 *
 * @author yipengup
 * @date 2021/9/16
 */
public class Lc17_official {

    public static void main(String[] args) {
        System.out.println(new Lc17_official().letterCombinations("23"));
        System.out.println(new Lc17_official().letterCombinations(""));
        System.out.println(new Lc17_official().letterCombinations("2"));
    }

    /**
     * 电话号码的字母组合
     *
     * @param digits 仅包含数字 2-9 的字符串， 0 <= digits.length <= 4
     * @return 电话号码字母组合列表
     */
    public List<String> letterCombinations(String digits) {

        if (digits.length() == 0) {
            return Collections.emptyList();
        }

        Map<Character, String> phoneMap = new HashMap<>();
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");

        List<String> combinations = new ArrayList<>();
        backTrack(phoneMap, combinations, 0, new StringBuilder(), digits);
        return combinations;
    }

    /**
     * 采用回溯算法处理字母组合（回溯算法， 就是按照当前已经到了叶子节点往回推）
     *
     * @param phoneMap     电话号码和字母组合的映射关系
     * @param combinations 字母组合集合
     * @param deep         当前处理的深度
     * @param combination  当前组合的字符串
     * @param digits       电话号码列表
     */
    private void backTrack(Map<Character, String> phoneMap, List<String> combinations, int deep, StringBuilder combination, String digits) {
        // 深度已经达到最大
        if (deep == digits.length()) {
            combinations.add(combination.toString());
        } else {
            // 从最后一个元素倒推,获取到最后一个电话号码对应的字母
            String letters = phoneMap.get(digits.charAt(deep));
            for (int i = 0; i < letters.length(); i++) {
                // 首先要把当前的字母添加到build中
                combination.append(letters.charAt(i));
                // 然后将深度往后移动
                backTrack(phoneMap, combinations, deep + 1, combination, digits);
                // 添加完之后要将当前的添加的字母给删除，是当前的build可以作用于下一个字母
                combination.deleteCharAt(deep);
            }
        }
    }

}
