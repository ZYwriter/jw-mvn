package jnitest;

public class JNITest {
	public native static void set(int i);
	public native static int get();
	static{
		System.loadLibrary("JNITest");
	}	
}
