package workTest.randomShortId;

import java.util.Random;
import java.util.zip.CRC32;

/**
 * 
 * @author eaves.zhu
 */
public class DeviceIdUtil {
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
		System.out.println(DeviceIdUtil.getCRC32(randomStr));
	}

}