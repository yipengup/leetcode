/**
 * @author yipengup
 * @date 2021/8/27
 */
public class Sample {

    public static void main(String[] args) {

        int a = 3;
        int b = 32;
        int c = a << 16 | b;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(c));
    }
}
