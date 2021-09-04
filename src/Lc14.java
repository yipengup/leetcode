/**
 * 14. 最长公共前缀
 * https://leetcode-cn.com/problems/longest-common-prefix/
 *
 * @author yipengup
 * @date 2021/9/4
 */
public class Lc14 {

    public static void main(String[] args) {
        System.out.println(new Lc14().longestCommonPrefixByPoint(new String[]{"flower", "flow", "flight"}));
        System.out.println(new Lc14().longestCommonPrefixByPoint(new String[]{"dog", "racecar", "car"}));
        System.out.println(new Lc14().longestCommonPrefixByPoint(new String[]{"a"}));
    }

    /**
     * 寻找字符串数组中最长公共前缀
     *
     * @param strs 字符串数组
     * @return 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder commonSubStringBuilder = new StringBuilder();
        String firstStr = strs[0];
        for (int i = 0; i < firstStr.length(); i++) {
            commonSubStringBuilder.append(firstStr.charAt(i));
            for (String str : strs) {
                if (!str.startsWith(commonSubStringBuilder.toString())) {
                    commonSubStringBuilder.deleteCharAt(commonSubStringBuilder.length() - 1);
                    return commonSubStringBuilder.toString();
                }
            }
        }

        return commonSubStringBuilder.toString();
    }


    /**
     * 寻找字符串数组中最长公共前缀
     * <p>
     * 通过指针
     *
     * @param strs 字符串数组
     * @return 最长公共前缀
     */
    public String longestCommonPrefixByPoint(String[] strs) {
        String commonSubString = "";
        // 找出字符串数组中长度最小的元素
        String minLengthStr = strs[0];
        for (String str : strs) {
            if (str.length() < minLengthStr.length()) {
                minLengthStr = str;
            }
        }
        // 边界条件
        if (minLengthStr.length() == 0) {
            return commonSubString;
        }

        // 起始指针
        for (int i = 0; i < minLengthStr.length(); i++) {
            for (String str : strs) {
                if (str.charAt(i) != minLengthStr.charAt(i)) {
                    return minLengthStr.substring(0, i);
                }
            }
        }

        return minLengthStr;
    }

    /**
     * 寻找字符串数组中最长公共子串
     *
     * @param strs 1 <= strs.length <= 200
     * @return 最长公共子串
     */
    public String longestCommonSubString(String[] strs) {
        String commonSubString = "";
        // 找出字符串数组中长度最小的元素
        String minLengthStr = strs[0];
        for (String str : strs) {
            if (str.length() < minLengthStr.length()) {
                minLengthStr = str;
            }
        }

        // 边界条件
        if (minLengthStr.length() == 0) {
            return commonSubString;
        }
        // 循环数组，找出最长公共子串
        StringBuilder commonSubStringBuilder = new StringBuilder();
        for (int i = 0; i < minLengthStr.length(); i++) {
            commonSubStringBuilder.append(minLengthStr.charAt(i));
            boolean contains = true;
            for (String str : strs) {
                if (!str.contains(commonSubStringBuilder)) {
                    contains = false;
                    break;
                }
            }
            if (!contains) {
                // 删除最后一个字符
                commonSubStringBuilder.deleteCharAt(commonSubStringBuilder.length() - 1);
                // 注：这里忽略掉两者相同的情况
                if (commonSubString.length() < commonSubStringBuilder.length()) {
                    commonSubString = commonSubStringBuilder.toString();
                }
                // 切换之后重新更新builder
                commonSubStringBuilder = new StringBuilder();
            }
        }

        // 将最终的结果保存起来，防止出现最终包含没有走 !contains 的逻辑
        if (commonSubString.length() < commonSubStringBuilder.length()) {
            commonSubString = commonSubStringBuilder.toString();
        }

        return commonSubString;
    }
}
