package javatodll;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class JavaToDll {

	public interface DllLib extends Library {
		DllLib DLLINSTANCE = (DllLib) Native.loadLibrary((Platform.isWindows() ? "dll/JavaToDll" : "c"), DllLib.class);
		int Add(int a, int b);
	}
	
	public static void main(String[] args) {
		int sum = DllLib.DLLINSTANCE.Add(5, 1);
		System.out.println("自定义DLL文件数据和:" + sum);
		System.exit(0);
	}
}
