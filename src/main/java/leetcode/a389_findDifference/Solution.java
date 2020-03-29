package leetcode.a389_findDifference;

import com.google.common.collect.Sets;
import sun.misc.CharacterDecoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *  Find the Difference
 *  给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 *
 * @author neptune
 * @create 2020 03 27 3:54 下午
 */
public class Solution {

    // 自己写的 直接比较 ascii 的差值
    public char findTheDifference(String s, String t) {
        int beforeSum = s.chars().sum();
        int afterSum = t.chars().sum();
//        System.out.println(beforeSum);
//        System.out.println(afterSum);
        int value = afterSum - beforeSum;
//        System.out.println(value);
        char[] chars = Character.toChars(value);
//        String output = new String();
        return chars[0];
    }

    // 方式二：异或，两个相同元素异或之后的值是0，0和x(任何数)异或等于x，还有一点非常重要：就是不管两个相同的数是在什么时候异或的，最终的结果都会存在0
    // 我举个例子：假如有6个数字：2 3 4 4 3 2， 不管是2^3^4^4^3^2 还是我们经过处理之后组合起来 (2^2)^(3^3)^(4^4)结果都是一样的，不会影响结果
    // 那我们想下本题两个字符串中的字符，s和t中相同的字符都存在两个，将他们全部异或之后肯定为0，然后其中还有一个多出来的就成了0^x=x，从而得到结果，不知道大家理解没
    public char findTheDifference2(String s, String t) {
        char result = 0;
        for (int i = 0; i < s.length(); i++){
            result ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++){
            result ^= t.charAt(i);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String a = "ymbgaraibkfmvocpizdydugvalagaivdbfsfbepeyccqfepzvtpyxtbadkhmwmoswrcxnargtlswqemafandgkmydtimuzvjwxvlfwlhvkrgcsithaqlcvrihrwqkpjdhgfgreqoxzfvhjzojhghfwbvpfzectwwhexthbsndovxejsntmjihchaotbgcysfdaojkjldprwyrnischrgmtvjcorypvopfmegizfkvudubnejzfqffvgdoxohuinkyygbdzmshvyqyhsozwvlhevfepdvafgkqpkmcsikfyxczcovrmwqxxbnhfzcjjcpgzjjfateajnnvlbwhyppdleahgaypxidkpwmfqwqyofwdqgxhjaxvyrzupfwesmxbjszolgwqvfiozofncbohduqgiswuiyddmwlwubetyaummenkdfptjczxemryuotrrymrfdxtrebpbjtpnuhsbnovhectpjhfhahbqrfbyxggobsweefcwxpqsspyssrmdhuelkkvyjxswjwofngpwfxvknkjviiavorwyfzlnktmfwxkvwkrwdcxjfzikdyswsuxegmhtnxjraqrdchaauazfhtklxsksbhwgjphgbasfnlwqwukprgvihntsyymdrfovaszjywuqygpvjtvlsvvqbvzsmgweiayhlubnbsitvfxawhfmfiatxvqrcwjshvovxknnxnyyfexqycrlyksderlqarqhkxyaqwlwoqcribumrqjtelhwdvaiysgjlvksrfvjlcaiwrirtkkxbwgicyhvakxgdjwnwmubkiazdjkfmotglclqndqjxethoutvjchjbkoasnnfbgrnycucfpeovruguzumgmgddqwjgdvaujhyqsqtoexmnfuluaqbxoofvotvfoiexbnprrxptchmlctzgqtkivsilwgwgvpidpvasurraqfkcmxhdapjrlrnkbklwkrvoaziznlpor";
        String b = "qhxepbshlrhoecdaodgpousbzfcqjxulatciapuftffahhlmxbufgjuxstfjvljybfxnenlacmjqoymvamphpxnolwijwcecgwbcjhgdybfffwoygikvoecdggplfohemfypxfsvdrseyhmvkoovxhdvoavsqqbrsqrkqhbtmgwaurgisloqjixfwfvwtszcxwktkwesaxsmhsvlitegrlzkvfqoiiwxbzskzoewbkxtphapavbyvhzvgrrfriddnsrftfowhdanvhjvurhljmpxvpddxmzfgwwpkjrfgqptrmumoemhfpojnxzwlrxkcafvbhlwrapubhveattfifsmiounhqusvhywnxhwrgamgnesxmzliyzisqrwvkiyderyotxhwspqrrkeczjysfujvovsfcfouykcqyjoobfdgnlswfzjmyucaxuaslzwfnetekymrwbvponiaojdqnbmboldvvitamntwnyaeppjaohwkrisrlrgwcjqqgxeqerjrbapfzurcwxhcwzugcgnirkkrxdthtbmdqgvqxilllrsbwjhwqszrjtzyetwubdrlyakzxcveufvhqugyawvkivwonvmrgnchkzdysngqdibhkyboyftxcvvjoggecjsajbuqkjjxfvynrjsnvtfvgpgveycxidhhfauvjovmnbqgoxsafknluyimkczykwdgvqwlvvgdmufxdypwnajkncoynqticfetcdafvtqszuwfmrdggifokwmkgzuxnhncmnsstffqpqbplypapctctfhqpihavligbrutxmmygiyaklqtakdidvnvrjfteazeqmbgklrgrorudayokxptswwkcircwuhcavhdparjfkjypkyxhbgwxbkvpvrtzjaetahmxevmkhdfyidhrdeejapfbafwmdqjqszwnwzgclitdhlnkaiyldwkwwzvhyorgbysyjbxsspnjdewjxbhpsvj";

//        System.out.println(solution.findTheDifference("a", "aa"));
//        System.out.println(solution.findTheDifference("", "y"));
//        System.out.println(solution.findTheDifference("abc", "abca"));
//        System.out.println(solution.findTheDifference(a, b));

        System.out.println(solution.findTheDifference2("a", "aa"));
        System.out.println(solution.findTheDifference2("", "y"));
        System.out.println(solution.findTheDifference2("abc", "abca"));
        System.out.println(solution.findTheDifference2(a, b));
    }
}
