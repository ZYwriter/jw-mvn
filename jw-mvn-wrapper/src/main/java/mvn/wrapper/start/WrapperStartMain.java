package mvn.wrapper.start;

import org.tanukisoftware.wrapper.WrapperManager;

import mvn.wrapper.listener.ServerListener;

/**
 * @author wengqf ����
 */
public class WrapperStartMain {
	public static void main(String[] args) {
		WrapperManager.start(new ServerListener(), args);
	}
}
