package helloworld;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * JNA��һ��.dll/.so�ļ�������һ��Java�ӿ�
 * @author wengqf
 *��1����Ҫ����һ���ӿڣ��̳���Library ��StdCallLibrary
 * Ĭ�ϵ��Ǽ̳�Library �������̬���ӿ���ĺ�������stdcall��ʽ����ģ���ô�ͼ̳�StdCallLibrary������������֪��kernel32��
 * ��2���ӿ��ڲ�����
 * �ӿ��ڲ���Ҫһ��������̬������INSTANCE��ͨ������������Ϳ��Ի������ӿڵ�ʵ�����Ӷ�ʹ�ýӿڵķ�����Ҳ���ǵ����ⲿdll/so�ĺ�����
 * �ó���ͨ��Native.loadLibrary()���API������ã��ú�����2��������
 * �� һ�������Ƕ�̬���ӿ�dll/so�����ƣ�������.dll��.so�����ĺ�׺�������JNI�Ĺ淶����Ϊ���˺�׺���Ͳ����Կ����ϵͳƽ̨�ˡ�
 * 			������̬�� �ӿ�·����˳���ǣ��ȴӵ�ǰ��ĵ�ǰ�ļ����ң����û���ҵ������ڹ��̵�ǰ�ļ���������win32/win64�ļ��У�
 * 			�ҵ���������Ӧ��dll�ļ������ �Ҳ����ٵ�WINDOWS����ȥ���������Ҳ����ͻ����쳣�ˡ�����������printf������Windowsƽ̨
 * 			�����ڵ�dll��������msvcrt������ ����ƽ̨��Linux�µ�so��������c��
 * �ڶ��������Ǳ��ӿڵ�Class���͡�JNAͨ�����Class���ͣ�����ָ����.dll/.so�ļ�����̬�����ӿڵ�ʵ������ʵ����JNAͨ�������Զ����ɡ�
 */
public class HelloWorld {

	public interface CLibrary extends Library {
		CLibrary INSTANCE = (CLibrary)
                Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"),
                		CLibrary.class);

            public void printf(String format, Object... args);
        }

        public static void main(String[] args) {
        	CLibrary.INSTANCE.printf("Hello, World\n");
            for (int i=0;i < args.length;i++) {
            	CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
            }
        }
}
