package jsontotxt;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class JsonToTxt {
	private Writer out = null;
	private File file = null;
	private BufferedWriter bw = null;

	@Before
	public void init() throws IOException {
		file = getTodayFile(".ctp");
		out = new FileWriter(file, false);
		bw = new BufferedWriter(out);
	}

	@Test
	public void writeTxt() throws IOException {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			UserRole role = new UserRole();
			role.setName("w" + i);
			role.setAge(i);
			writeJson(role);
		}
		// System.in.read();
	}

	private void writeJson(UserRole role) {
		try {
			bw.append(JSON.toJSONString(role));
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@After
	public void after() throws IOException {
		out.close();
		bw.close();
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
	
	
	/**
	 * 获取文件对象
	 * 
	 * @return
	 * @throws IOException
	 */
	private File getTodayFile(String fileName) throws IOException {
		String path = "flow/";
//		String name = System.currentTimeMillis()+"/";
		File filePath = new File(path + fileName);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		File file = new File(path + fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}
	
}
