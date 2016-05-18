package jsontotxt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
 * ����д�ļ� �ļ�д��һ����С������д�����ļ���ת���ɶ���
 * 
 * @author wengqf
 *
 */
public class WriteFileByByteSize {

	private static List<UserRole> userList = new ArrayList<UserRole>();

	@Test
	public void testWriteFile() throws IOException {
		produceData();
		consumerData();
		System.in.read();
	}

	private void playThread() {
		Thread thread = new Thread() {
			public void run() {
				File ctpFile = new File("ctp/");
				File list[] = ctpFile.listFiles();
				for (int i = 0; i < list.length; i++) {
					if (list[i].isFile()) {
						play(list[i]);
					}
				}
			}
		};
		thread.start();
	}
	
	private void play(File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			// ��ȡֱ�����һ��
			String line = null;
			while ((line = br.readLine()) != null) {
				if (StringUtils.isBlank(line)) {
					continue;
				}
				UserRole role = JSON.parseObject(line.toString(), UserRole.class);
				System.out.println("====" + role.toString());
				Thread.sleep(1);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void produceData() {

		Thread thread = new Thread() {
			public void run() {
				for (int j = 1; j < 10000; j++) {
					UserRole role = new UserRole();
					role.setName("wwww" + j);
					role.setAge(j);
					userList.add(role);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("���������߳̽���...end");
			}
		};
		thread.start();

	}

	public void consumerData() {

		Thread thread = new Thread() {
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
				long maxSize = 1024 * 16l;// 1M
				File file = null;
				BufferedWriter bw = null;
				try {
					file = createNewFile(".ctp");
					bw = new BufferedWriter(new FileWriter(file, false));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				List<UserRole> ctplist = null;
				int waitTime = 1;
				while (!isInterrupted()) {
					System.out.println("д�߳̿�ʼ...");
					try {
						if (userList.size() == 0) {
							if (waitTime == 5) {
								System.out.println("���������...");
								playThread();
								break;
							}
							Thread.sleep(1000);
							System.out.println("�ȴ�......(" + waitTime++ + ")��");
							continue;
						}
						ctplist = new ArrayList<>(userList);
						userList.removeAll(ctplist);
						// д�ļ�
						int lineNum = 0;
						for (UserRole role : ctplist) {
							bw.append(JSON.toJSONString(role));
							if(lineNum < ctplist.size()-1){
								bw.newLine();
							}
							lineNum++;
							bw.flush();
						}
						// �ж��ļ���С��������������������ļ�д��
						long size = getFileSize(file);
						System.out.println("size:" + size);
						if (size > maxSize) {
							file.setReadOnly();
							bw.close();
							System.out.println("size:" + size + ",maxSize:" + maxSize
									+ "===================================================");
							file = createNewFile(".ctp");
							bw = new BufferedWriter(new FileWriter(file, false));
						}else{
							bw.newLine();
						}
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("д�߳̽���.......end");
				try {
					if (bw != null) {
						bw.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		thread.start();

	}


	private File createNewFile(String fileName) throws IOException {
		String path = "ctp/";
		String name = System.currentTimeMillis() + fileName;
		File filePath = new File(path);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		File file = new File(path + name);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	private long getFileSize(File file) {
		FileChannel fc = null;
		FileInputStream fis = null;
		try {
			if (file.exists() && file.isFile()) {
				fis = new FileInputStream(file);
				fc = fis.getChannel();
				return fc.size();
			} else {
				return 0l;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 0l;
		} catch (IOException e) {
			e.printStackTrace();
			return 0l;
		} finally {
			try {
				if (null != fc) {
					fc.close();
				}
				if (null != fis) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
