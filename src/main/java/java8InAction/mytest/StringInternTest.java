package java8InAction.mytest;

/**
 * @author neptune
 * @create 2018 04 03 上午10:54
 */
public class StringInternTest {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "a";
        String s3 = "bc";
        String s4 = s2 + s3; //false
//        String s4 = "a" + "bc"; //true
        System.out.println(s1 == s4);

        String a = new String("abc");
        String a1 = "abc";
        String a2 = new String("abc");
        System.out.println(a == a1.intern());
        System.out.println(a == a2.intern());
        System.out.println(a1 == a2.intern());

        String str1=new StringBuilder("计算机").append("软件").toString();

        System.out.println(str1.intern()==str1);

        String str2=new StringBuilder("ja").append("va").toString();

        System.out.println(str2.intern()==str2);
    }
}
