/**
 * @author yipengup
 * @date 2021/9/23
 */
public interface SuperClass {

    void fun();

}

class Super2 {

    {
        System.out.println(this.getClass().getName());
    }

}

class Sub extends Super2 {
    {
        System.out.println(this.getClass().getName());
    }
}

class Test {

    public static void main(String[] args) {
        SuperClass superClass = new SuperClass() {

            {
                System.out.println(this.getClass().getName());
            }

            @Override
            public void fun() {

            }
        };

        Super2 super2 = new Super2() {

            {
                System.out.println(this.getClass().getName());
            }

        };

        new Super2();
        new Sub();


    }

}

