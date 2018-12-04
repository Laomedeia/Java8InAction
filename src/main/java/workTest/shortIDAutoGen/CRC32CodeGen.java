package workTest.shortIDAutoGen;

import java.util.Random;
import java.util.zip.CRC32;

/**
 * 
 * @author eaves.zhu
 */
public class CRC32CodeGen {
/**
 * @author eaves.zhu
 * @param deviceId
 * @return int 
 * CRC32
 */
   public static long getCRC32(String deviceId){
	        CRC32 crc32 = new CRC32();  
	        crc32.update(deviceId.getBytes());
	            long tmp = crc32.getValue();
	            return tmp;
   }

	public static void main(String[] args) {
		Random rd = new Random();
		String randomStr = "randomStr:" + ( 10 + rd.nextInt(10) ) ;
		System.out.println(randomStr);
		System.out.println(CRC32CodeGen.getCRC32(randomStr));
	}

}