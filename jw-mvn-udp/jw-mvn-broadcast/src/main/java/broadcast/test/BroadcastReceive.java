package broadcast.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

/**
 * �㲥���ն�
 * @author wengqf
 *
 */
public class BroadcastReceive {
	public static void main(String[] args) throws IOException {
		DatagramPacket receive = new DatagramPacket(new byte[1024], 1024);//�������ݱ�
		@SuppressWarnings("resource")
		DatagramSocket client = new DatagramSocket(8888);
		System.out.println("client receive start...");
		while(true){
			client.receive(receive);
			byte[] recvByte = Arrays.copyOfRange(receive.getData(), 0, receive.getLength());
			System.out.println("Client receive msg:" + new String(recvByte));
		}
	}
}
