package jsontotxt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class FileSizeTest {

	@Test
	public void getFileSize() {
		FileChannel fc = null;
		FileInputStream fis = null;
		try {
			File f = new File("D:\\1.ctp");
			if (f.exists() && f.isFile()) {
				fis = new FileInputStream(f);
				fc = fis.getChannel();
				System.out.println(fc.size());
			} else {
				System.out.println("file doesn't exist or is not a file");
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
				try {
					if (null != fc) {
						fc.close();
					}
					if(null != fis){
						fis.close();
					}
				} catch (IOException e) {
					System.out.println(e);
				}
		}
	}

}
