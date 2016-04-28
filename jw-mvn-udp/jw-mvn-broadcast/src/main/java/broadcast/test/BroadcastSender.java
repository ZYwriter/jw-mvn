package broadcast.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * �㲥���Ͷ�
 * 
 * @author wengqf
 *
 */
public class BroadcastSender {
	public static void main(String[] args) throws IOException, InterruptedException {
		byte[] msg = new String("connection successfully!!!").getBytes();
		/*
		 * ��Java UDP�е�����㲥�Ĵ�������ͬ��,Ҫʵ�־��й㲥���ܵĳ���ֻ��Ҫʹ�ù㲥��ַ����, ���磺����ʹ���˱��صĹ㲥��ַ
		 */
		InetAddress inetAddr = InetAddress.getByName("255.255.255.255");
		DatagramSocket server = new DatagramSocket();

		DatagramPacket sendPack = new DatagramPacket(msg, msg.length, inetAddr, 8888);//�������ݱ�
		System.out.println("Sender send msg Start...");
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Thread.sleep(1000);
			server.send(sendPack);
		}
		System.out.println("Sender send msg complete...");
		System.in.read();
		server.close();
	}
}
