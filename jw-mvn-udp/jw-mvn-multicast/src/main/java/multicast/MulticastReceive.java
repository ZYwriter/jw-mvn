package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;

/**
 *  �鲥�����
 * @author wengqf
 *
 */
public class MulticastReceive {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		InetAddress inetRemoteAddr = InetAddress.getByName("224.0.0.5");

		DatagramPacket recvPack = new DatagramPacket(new byte[1024], 1024);

		MulticastSocket server = new MulticastSocket(8888);

		/*
		 * ����Ƿ������ݱ���,���Բ�����ಥ��; ����ǽ������ݱ���,�������ಥ��; �����ǽ������ݱ���,���Ա������ಥ��;
		 */
		server.joinGroup(inetRemoteAddr);

		System.out.println("---------------------------------");
		System.out.println("Server current start......");
		System.out.println("---------------------------------");

		while (true) {
			server.receive(recvPack);

			byte[] recvByte = Arrays.copyOfRange(recvPack.getData(), 0, recvPack.getLength());

			System.out.println("Server receive msg:" + new String(recvByte));
		}

	}
}
