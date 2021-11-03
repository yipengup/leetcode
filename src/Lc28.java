import java.util.HashMap;

/**
 * 28. 实现 strStr()
 * https://leetcode-cn.com/problems/implement-strstr/
 *
 * @author yipengup
 * @date 2021/10/28
 */
public class Lc28 {

    public static void main(String[] args) {
        System.out.println(new Lc28().strStrKMP("mississippi", "issip"));
    }


    /**
     * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
     * 0 <= haystack.length, needle.length <= 5 * 104
     * haystack 和 needle 仅由小写英文字符组成
     */
    public int strStr1(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public int strStr(String haystack, String needle) {

        if (needle.length() == 0) {
            return 0;
        }

        if (haystack.length() == 0) {
            return -1;
        }

        int searchIndex = 0;
        while (searchIndex <= haystack.length() - needle.length()) {
            int headIndex = 0;
            int endIndex = needle.length() - 1;
            while (true) {
                // 巧用双指针进行比较
                if (haystack.charAt(searchIndex + headIndex) == needle.charAt(headIndex)
                        && haystack.charAt(searchIndex + endIndex) == needle.charAt(endIndex)) {
                    headIndex++;
                    endIndex--;
                    if (headIndex > endIndex) {
                        return searchIndex;
                    }
                    continue;
                }
                searchIndex++;
                break;
            }
        }
        return -1;
    }

    /**
     * 采用Robin-Karp算法
     */
    public int strStrRK(String haystack, String needle) {

        // 对主串中的每一个子串进行hash求值
        int n = haystack.length();
        int m = needle.length();
        // 边界条件
        if (m == 0) {
            return 0;
        }
        if (n == 0 || n < m) {
            return -1;
        }

        HashMap<String, Integer> map = new HashMap<>(n - m + 1);
        for (int i = 0; i < n - m + 1; i++) {
            String substring = haystack.substring(i, i + m);
            if (!map.containsKey(substring)) {
                map.put(substring, i);
            }
        }

        return map.getOrDefault(needle, -1);
    }

    public int strStrBoyerMooreBadChar(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        // 边界条件
        if (m == 0) {
            return 0;
        }
        if (n == 0 || n < m) {
            return -1;
        }

        HashMap<Character, Integer> badCharInitMap = new HashMap<>(m);
        for (int i = 0; i < m; i++) {
            // 这里后面的字符会覆盖前面的字符，坏字符往后移动的就小
            badCharInitMap.put(needle.charAt(i), i);
        }


        // BM匹配算法重要就是判断 坏字符、好后缀
        // 先处理坏字符（从后向前匹配，找到第一个不符合条件的坏字符，判断坏字符是否存在模式串中）
        for (int i = 0; i < n - m + 1; i++) {
            boolean findBadChar = false;
            for (int j = m - 1; j >= 0; j--) {
                int tailIndex = i + j;
                if (haystack.charAt(tailIndex) != needle.charAt(j)) {
                    // 找到坏字符，判断坏字符是否存在于模式串中
                    // （如何判断坏字符是否在模式串中，并且决定模式串向后移动的位数？）先对模式串的每个字符构建散列表，并且记录每个字符所在的索引
                    findBadChar = true;
                    if (badCharInitMap.containsKey(haystack.charAt(tailIndex))) {
                        // move 表示向右移动的位数
                        int move = j - badCharInitMap.get(haystack.charAt(tailIndex));
                        if (move >= 0) {
                            // 这里减去1因为外层循环会+1
                            i = i + move - 1;
                        }
                    } else {
                        // 将i向右移动j+1位，这里减去1因为外层循环会+1
                        i = i + j;
                    }
                    break;
                }
            }
            // 一直没有找到坏字符完全匹配直接返回
            if (!findBadChar) {
                return i;
            }
        }
        return -1;
    }

    public int strStrBoyerMoore(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        // 边界条件
        if (m == 0) {
            return 0;
        }
        if (n == 0 || n < m) {
            return -1;
        }

        HashMap<Character, Integer> badCharInitMap = new HashMap<>(m);
        for (int i = 0; i < m; i++) {
            // 这里后面的字符会覆盖前面的字符，坏字符往后移动的就小
            badCharInitMap.put(needle.charAt(i), i);
        }

        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        // 初始化好后缀预处理子串
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        // 依次向后移动，将当前节点与模式串的后缀进行匹配，注意i不用移动到最后一位
        for (int i = 0; i < m - 1; i++) {
            int j = i;
            // 好后缀的长度
            int k = 0;
            // 将当前的节点依次与后缀匹配，匹配成功就向前移动继续匹配
            while (j >= 0 && needle.charAt(j) == needle.charAt(m - 1 - k)) {
                k++;
                // 记录长度为k的好后缀匹配的模式串中的子串的起始位置
                // 向后面的移动过程中，后面匹配的会覆盖前面匹配的，这样就不会导致最后主串向后移动过头
                suffix[k] = j;
                j--;
            }
            // 表示当前长度的后缀子串是能够匹配上
            if (j == -1) {
                prefix[k] = true;
            }
        }


        // BM匹配算法重要就是判断 坏字符、好后缀
        // 先处理坏字符（从后向前匹配，找到第一个不符合条件的坏字符，判断坏字符是否存在模式串中）
        for (int i = 0; i < n - m + 1; i++) {
            boolean findBadChar = false;
            for (int j = m - 1; j >= 0; j--) {
                int tailIndex = i + j;
                if (haystack.charAt(tailIndex) != needle.charAt(j)) {
                    // 找到坏字符，判断坏字符是否存在于模式串中
                    // （如何判断坏字符是否在模式串中，并且决定模式串向后移动的位数？）先对模式串的每个字符构建散列表，并且记录每个字符所在的索引
                    // 找到坏字符前已经匹配成功的字符就是好后缀，判断模式串中是否还存在对应的好后缀子串，不存在就找到匹配的好后缀的模式串中前缀子串
                    // （判断模式串中是否还存在对应的子串？并且是否存在模式串中的前缀子串）先对模式串中每个后缀子串与存在对应的子串建立映射关系
                    findBadChar = true;
                    int move = 0;
                    // 先针对好后缀计算好后缀移动的长度
                    int goodSuffixLength = m - j - 1;
                    if (goodSuffixLength > 0) {
                        if (suffix[goodSuffixLength] < 0) {
                            // 好后缀没有匹配到其他的子串，就需要判断是否匹配到前缀子串
                            while (goodSuffixLength > 0) {
                                if (prefix[goodSuffixLength]) {
                                    move = m - goodSuffixLength;
                                    break;
                                }
                                goodSuffixLength--;
                            }
                            // 没有匹配到前缀子串，直接向右移动m位
                            if (move == 0) {
                                i = i + m - 1;
                                break;
                            }
                        } else {
                            // 好后缀匹配到其他的子串, 将匹配的子串的首位置移动到好后缀的位置（ 向右移动j -  suffix[goodSuffixLength]）
                            move = j - suffix[goodSuffixLength];
                        }
                    }

                    // 计算坏字符向右移动的位数
                    if (badCharInitMap.containsKey(haystack.charAt(tailIndex))) {
                        // move 表示向右移动的位数
                        move = Math.max(move, j - badCharInitMap.get(haystack.charAt(tailIndex)));
                    } else {
                        // 将i向右移动j+1位
                        move = Math.max(move, j + 1);
                    }
                    // move 至少向右移动一位，这里减去1因为外层循环会+1
                    move = Math.max(move, 1);
                    i = i + move - 1;
                    break;
                }
            }
            // 一直没有找到坏字符完全匹配直接返回
            if (!findBadChar) {
                return i;
            }
        }
        return -1;
    }

    public int strStrKMP(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        // 边界条件
        if (m == 0) {
            return 0;
        }
        if (n == 0 || n < m) {
            return -1;
        }


        // 组装【好前缀】后缀子串中匹配的最长前缀子串最后一位索引
        int[] next = new int[m];
        // 好前缀只有一位时，不存在后缀子串
        next[0] = -1;
        // k表示当前前缀子串的最后一位索引
        int k = -1;
        // 核心思路就是：前一个最长前缀子串的后一个字符要是与好前缀当前最后一位字符相同，那么当前的最长前缀子串就是前一个最长前缀子串+最后一位字符
        for (int i = 1; i < m - 1; i++) {
            // 前一个最长前缀子串的后一个字符 不等于 好前缀当前最后一位字符
            while (k != -1 && needle.charAt(k + 1) != needle.charAt(i)) {
                // 将k移动到前一个最长前缀子串
                k = next[k];
            }
            // 前一个最长前缀子串的后一个字符要是与好前缀当前最后一位字符相同
            if (needle.charAt(k + 1) == needle.charAt(i)) {
                k++;
            }
            next[i] = k;
        }

        int j = 0;
        for (int i = 0; i < n; i++) {
            // 找到坏字符以及好前缀，将模式串向前移动继续与当前的i对应的值进行匹配
            // 获取到好前缀最长前缀子串
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                // 将最长前缀子串的下一位与i进行匹配
                j = next[j - 1] + 1;
            }

            // 每次都是模式串的第一位与i进行比较
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i + 1 - m;
            }
        }
        return -1;
    }

}
