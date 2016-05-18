package jsontotxt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectToByte {

	public static void main(String[] args) {
		for (int j = 1; j < 1000; j++) {
			UserRole role = new UserRole();
			role.setName("wwww" + j);
			role.setAge(j);
			ObjectToByte(role);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	// 将对象转换成字节数组
	private static void ObjectToByte(UserRole role) {
		try {
			FileOutputStream fos = new FileOutputStream(new File("role.in"));
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(role);
			fos.close();
			bo.close();
			oo.close();
		} catch (Exception e) {
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
	}

	// 将字节数组转换成对象
	public static Object ByteToObject(byte[] bytes) {
		UserRole role = null;
		try {
			// bytearray to object
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);

			role = (UserRole) oi.readObject();
			bi.close();
			oi.close();
		} catch (Exception e) {
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
		return role;
	}

}
