package start;



import org.tanukisoftware.wrapper.WrapperManager;

import listener.ServerListener;

/**
 * @author wengqf ����
 */
public class WrapperStartMain {
	public static void main(String[] args) {
		WrapperManager.start(new ServerListener(), args);
	}
}
