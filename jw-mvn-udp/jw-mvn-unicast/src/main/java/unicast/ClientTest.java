package unicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * µ¥²¥¿Í»§¶Ë
 * @author wengqf
 *
 */
public class ClientTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		byte[] msg = new String("connect test successfully!!!").getBytes();

		DatagramSocket client = new DatagramSocket();

		InetAddress inetAddr = InetAddress.getLocalHost();
		SocketAddress socketAddr = new InetSocketAddress(inetAddr, 8888);

		DatagramPacket sendPacket = new DatagramPacket(msg, msg.length, socketAddr);

		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Thread.sleep(1000);
			client.send(sendPacket);
		}

		client.close();
	}
}
