package workTest.shortIDAutoGen;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Random;

/**
 * @author neptune
 * @create 2020 04 22 10:56 上午
 */
public class ShareCodeUtil3 {

        /**
         * 六位不重复
         * @param args
         */
        public static void main(String[] args) {
            final int fixLen = 6;
            HashSet<String> set = Sets.newHashSet();
            for (int i = 1; i < 10000 + 1; i++) {
                String nNum = fixLenCode(fixLen, i);
                System.out.println(i +"->"+ nNum);
                set.add(nNum);
            }
            System.out.printf("total size: %d", set.size());
        }

        static final char[] chars = new char[]{'8', 'G', 'X', '7', 'D', 'T', 'E', '9', 'R', 'A', 'K', 'L', 'J', '6', 'Z', 'C', 'V', '2', 'B', '3', '4', 'Y', 'U', 'I', 'H', 'N', 'W'};
        static final char[] noSenseChars = new char[]{'F', 'M', 'Q', 'P'};
        static final int charsLen = chars.length;
        static final int noSenseLen = noSenseChars.length;
        static private String fixLenCode(int fixLen, int num) {
            String nNum = "";
            while (num >0) {
                nNum += chars[num % charsLen];
                num = num / charsLen;
            }
            int nNumLen = fixLen- nNum.length();
            for (int j = 0; j < nNumLen; j++) {
                int rnd = new Random().nextInt(noSenseLen);
                nNum = noSenseChars[rnd] + nNum;
            }
            return nNum;
        }

}
