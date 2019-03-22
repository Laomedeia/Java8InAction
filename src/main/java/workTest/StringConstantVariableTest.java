package workTest;

/**
 * 字符量常量，变量及字面量测试
 * @author neptune
 * @create 2019 03 21 10:54
 */
public class StringConstantVariableTest {
    public static void main(String[] args) {
//        final String s1 = "aaa";
        String s1 = new String("1") + new String("1");
        s1.intern();
        String s3 = "11";
        System.out.println(s1 == s3);
        System.out.println(System.identityHashCode(s1)); //打印变量地址
        System.out.println(System.identityHashCode(s3)); //打印变量地址


        String a = new String("a");
        s1.intern();    // s1 = s1.intern() 则会返回 true.因为 intern 取回了常量池的"a"
        String a1 = "a";
        System.out.println(a == a1);
        System.out.println(System.identityHashCode(a)); //打印变量地址
        System.out.println(System.identityHashCode(a1)); //打印变量地址

    }
}
