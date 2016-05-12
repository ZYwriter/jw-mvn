package test.xx;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import util.XXTea;
import util.ZipUtils;

public class XXTeaTest {
	private File file = null;
	
	@Before
	public void init(){
		file = new File("D:/jys/sh/show2003.dbf");
	}

	@Test
	public void testXX(){
		long startTime = System.currentTimeMillis();
		byte[] byarr = getByteAry(file);
		long endTime = System.currentTimeMillis();
		System.out.println("正常====耗时：" + (endTime-startTime) +" 毫秒,包大小：" + byarr.length);
		
		//压缩
		long startTime2 = System.currentTimeMillis();
		byte[] byarr2 = getByteAry(file);
		byte[] jzlib2 = ZipUtils.jzlib(byarr2);
		long endTime2 = System.currentTimeMillis();
		System.out.println("压缩后===耗时2：" + (endTime2-startTime2) +" 毫秒,包大小：" + jzlib2.length);
		//解压
		long startTime22 = System.currentTimeMillis();
		byte[] unjzlib = ZipUtils.unjzlib(jzlib2);
		long endTime22 = System.currentTimeMillis();
		System.out.println("解压后===耗时22：" + (endTime22-startTime22) +" 毫秒,包大小：" + unjzlib.length);
		
		//加密
		long startTime3 = System.currentTimeMillis();
		byte[] byarr3 = getByteAry(file);
		byte[] jzlib3 = ZipUtils.jzlib(byarr3);
		byte[] encrypt3 = XXTea.encrypt(jzlib3, XXTea.getKey(10));
		long endTime3 = System.currentTimeMillis();
		System.out.println("压缩在加密后===耗时3：" + (endTime3-startTime3) +" 毫秒,包大小：" + encrypt3.length);
		
		//解密
		long startTime33 = System.currentTimeMillis();
		byte[] decrypt33 = XXTea.decrypt(encrypt3, XXTea.getKey(10));
		byte[] unjzlib33 = ZipUtils.unjzlib(decrypt33);
		long endTime33 = System.currentTimeMillis();
		System.out.println("解密后===耗时33：" + (endTime33-startTime33) +" 毫秒,包大小：" + unjzlib33.length);
	}
	
	 /**
     * 转换字节流字节数组
     * @param filename
     * @return
     */
    private byte[] getByteAry(File fileName) {
        FileInputStream inStream = null;
        ByteArrayOutputStream outStream = null;
        try {
            inStream = new FileInputStream(fileName);//输入流
            outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int byteread = 0;
            while ((byteread = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, byteread);
            }
            outStream.close();
            inStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outStream.toByteArray();
    }
}
