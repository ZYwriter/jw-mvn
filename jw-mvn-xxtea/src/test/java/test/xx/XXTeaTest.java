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
		System.out.println("����====��ʱ��" + (endTime-startTime) +" ����,����С��" + byarr.length);
		
		//ѹ��
		long startTime2 = System.currentTimeMillis();
		byte[] byarr2 = getByteAry(file);
		byte[] jzlib2 = ZipUtils.jzlib(byarr2);
		long endTime2 = System.currentTimeMillis();
		System.out.println("ѹ����===��ʱ2��" + (endTime2-startTime2) +" ����,����С��" + jzlib2.length);
		//��ѹ
		long startTime22 = System.currentTimeMillis();
		byte[] unjzlib = ZipUtils.unjzlib(jzlib2);
		long endTime22 = System.currentTimeMillis();
		System.out.println("��ѹ��===��ʱ22��" + (endTime22-startTime22) +" ����,����С��" + unjzlib.length);
		
		//����
		long startTime3 = System.currentTimeMillis();
		byte[] byarr3 = getByteAry(file);
		byte[] jzlib3 = ZipUtils.jzlib(byarr3);
		byte[] encrypt3 = XXTea.encrypt(jzlib3, XXTea.getKey(10));
		long endTime3 = System.currentTimeMillis();
		System.out.println("ѹ���ڼ��ܺ�===��ʱ3��" + (endTime3-startTime3) +" ����,����С��" + encrypt3.length);
		
		//����
		long startTime33 = System.currentTimeMillis();
		byte[] decrypt33 = XXTea.decrypt(encrypt3, XXTea.getKey(10));
		byte[] unjzlib33 = ZipUtils.unjzlib(decrypt33);
		long endTime33 = System.currentTimeMillis();
		System.out.println("���ܺ�===��ʱ33��" + (endTime33-startTime33) +" ����,����С��" + unjzlib33.length);
	}
	
	 /**
     * ת���ֽ����ֽ�����
     * @param filename
     * @return
     */
    private byte[] getByteAry(File fileName) {
        FileInputStream inStream = null;
        ByteArrayOutputStream outStream = null;
        try {
            inStream = new FileInputStream(fileName);//������
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
