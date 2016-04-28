package broadcast.multicastSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 * MulticastSocketʵ����һ�����ڹ㲥�Ķ��������ң� ����ֻ��Ҫһ��MulticastSocket,�����̣߳�����
 * MulticastSocket�����ڷ��ͣ�Ҳ���ڽ��գ�����һ ���̷ֱ߳�������û��������룬����MulticastSocket
 * �������ݣ���һ���߳������MulticastSocket�ж�ȡ���ݡ�
 * 
 * @author wengqf
 *
 */
public class MulticastSocketTest implements Runnable {
	// ʹ�ó�����Ϊ������Ķ��㲥IP��ַ
	private static final String BROADCAST_IP = "224.0.0.1";
	// ʹ�ó�����Ϊ������Ķ��㲥Ŀ�ĵĶ˿�
	public static final int BROADCAST_PORT = 30000;
	// ����ÿ�����ݱ�������СΪ4K
	private static final int DATA_LEN = 4096;
	// ���屾�����MulticastSocketʵ��
	private MulticastSocket socket = null;
	private InetAddress broadcastAddress = null;
	private Scanner scan = null;
	// ��������������ݵ��ֽ�����
	byte[] inBuff = new byte[DATA_LEN];
	// ��ָ���ֽ����鴴��׼���������ݵ�DatagramPacket����
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	// ����һ�����ڷ��͵�DatagramPacket����
	private DatagramPacket outPacket = null;

	public void init() throws IOException {

		try {
			// �������ڷ��͡��������ݵ�MulticastSocket����
			// ��Ϊ��MulticastSocket������Ҫ���գ�������ָ���˿�
			socket = new MulticastSocket(BROADCAST_PORT);
			broadcastAddress = InetAddress.getByName(BROADCAST_IP);
			// ����socket����ָ���Ķ��㲥��ַ
			socket.joinGroup(broadcastAddress);
			// ���ñ�MulticastSocket���͵����ݱ������͵�����
			socket.setLoopbackMode(false);
			// ��ʼ�������õ�DatagramSocket,������һ������Ϊ0���ֽ�����
			outPacket = new DatagramPacket(new byte[0], 0, broadcastAddress, BROADCAST_PORT);
			// �����Ա�ʵ����run()������Ϊ�߳�����߳�
			new Thread(this).start();
			// ��������������
			scan = new Scanner(System.in);
			// ���϶�ȡ��������
			while (scan.hasNextLine()) {
				// �����������һ���ַ���ת���ֽ�����
				byte[] buff = scan.nextLine().getBytes();
				// ���÷����õ�DatagramPacket����ֽ�����
				outPacket.setData(buff);
				// �������ݱ�
				socket.send(outPacket);
			}
		} finally {
			socket.close();
		}
	}

	public void run() {
		try {
			System.out.println("�߳�����...");
			while(true){
				//��ȡSocket�е����ݣ����������ݷ���inPacket����װ���ֽ������
				System.out.println(inPacket.getData());
				System.out.println(new String(inBuff , 0 , inPacket.getLength()));
				socket.receive(inPacket);
				//��ӡ�����socket�ж�ȡ������
				System.out.println("������Ϣ��" + new String(inBuff , 0 , inPacket.getLength()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				if (socket != null) {
					//�ø�Socket�뿪�ö��IP�㲥��ַ
					socket.leaveGroup(broadcastAddress);
					//�رո�Socket����
					socket.close();
				}
				System.exit(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		new MulticastSocketTest().init();
	}

}
