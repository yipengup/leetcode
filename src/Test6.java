import java.util.Objects;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * https://leetcode-cn.com/problems/zigzag-conversion/
 *
 * PAYPALISHIRING
 * PAHNAPLSIIGYIR
 *
 * @author yipengup
 * @date 2021/8/24
 */
public class Test6 {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 1;
        Test6 test6 = new Test6();
        System.out.println(test6.convert(s, numRows));
    }

    public String convert(String s, int numRows) {
        // 排除特殊行数
        if (1 == numRows) {
            return s;
        }

        // 2 行的话直接就是将字符串打印成2行即可
        if (2 == numRows) {
            // 定义二维数组
            Character[][] chars = new Character[2][s.length() / 2 + 1];
            // 填充二维数组，基位数填充到第二行， 偶数位填充到第一行（因为索引是从0开始）
            char[] sourceChar = s.toCharArray();
            for (int i = 0; i < sourceChar.length; i++) {
                if (i % 2 == 0) {
                    chars[0][i / 2] = sourceChar[i];
                } else {
                    chars[1][i / 2] = sourceChar[i];
                }
            }
            return printfCharArray(chars);
        }

        // >=3行 重要就是确认列数 （总的元素个数/每一个小正方形元素的个数）* 小正方形的列数）
        // 小正方形元素的个数
        int squareElementSize = numRows + (numRows - 2);
        // 小正方形的个数
        int maxSize = s.length() / squareElementSize + 1;
        // 每个小正方形的列数
        int squareColNum = numRows - 1;
        int colNums = maxSize * squareColNum;
        Character[][] chars = new Character[numRows][colNums];
        // 填充二维数组，也就是填充每一个小正方形
        // 记录当前填充的小正方形
        int currentSquare = 1;
        while (currentSquare <= maxSize) {
            // 已经填充的元素的个数
            int blank = squareElementSize * (currentSquare - 1);
            if (blank >= s.length()) {
                break;
            }
            // 开始填充元素的索引(因为索引是从0开始)
            int blankIndex = blank + 1 - 1;
            // 已经填充的列数
            int blankCol = squareColNum * (currentSquare - 1);
            // 开始填充元素的列数索引（因为索引也是从0开始）
            int blankColIndex = blankCol + 1 - 1;

            // 填充小正方形分两步： 1、填充首行 2、填充其他行
            // 填充首行
            int rowTemp = 0;
            while (rowTemp < numRows && blankIndex < s.length()) {
                chars[rowTemp++][blankColIndex] = s.charAt(blankIndex++);
            }
            // 填充其他行（小正方形的列索引范围为 0 ~ numRows - 2）
            int colTemp = 1;
            while (colTemp < numRows - 1 && blankIndex < s.length()) {
                chars[numRows - 1 - colTemp][blankColIndex + numRows - 1 - colTemp] = s.charAt(blankIndex++);
                colTemp++;
            }
            currentSquare++;
        }
        return printfCharArray(chars);
    }

    private String printfCharArray(Character[][] chars) {
        // 将二维数组转化成字符串返回
        StringBuilder builder = new StringBuilder();
        for (Character[] aChar : chars) {
            for (Character character : aChar) {
                // 只有不为空的才是被填充的地方
                if (Objects.nonNull(character)) {
                    builder.append(character);
                }
            }
        }
        return builder.toString();
    }

}
