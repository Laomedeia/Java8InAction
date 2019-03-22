package workTest;

import lombok.Data;

import java.util.Arrays;

/**
 *  java引用和值传递的区别
 * @author neptune
 * @create 2019 02 19 22:44
 */
public class JavaReferenceAndValuePassingTest {

    @Data
    private static class Leads {
        String leadsNo;
    }

    public static void  test(int[] a, int[] b, int c, Leads lead) {
        a = null;
        b[0] = 4;
        c = 7;
        lead.setLeadsNo("2222");
    }
    public static void main(String[] args) {
        int[] a = { 1,2,3 };
        int[] b = { 3 };
        int c = 6;
        Leads lead = new Leads();
        lead.setLeadsNo("11111");
        test(a,b,c,lead);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(c);
        System.out.println(lead.getLeadsNo());
    }
}
