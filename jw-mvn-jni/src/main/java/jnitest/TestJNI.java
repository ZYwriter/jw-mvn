package jnitest;


public class TestJNI {
	public static void main(String[] args) {
		long beginTime = System.currentTimeMillis();
		int i;int j;
		for(i=0;i<1000;i++){
			for(j=0;j<1000;j++){
				JNITest.set(i*j);
				JNITest.get();
			}
		}
		long totalTime = System.currentTimeMillis()-beginTime;
		System.out.println("JNI总耗时："+totalTime);
	}

}
