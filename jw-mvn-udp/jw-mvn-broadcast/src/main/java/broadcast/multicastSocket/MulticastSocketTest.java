package broadcast.multicastSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 * MulticastSocket实现了一个基于广播的多人聊天室， 程序只需要一个MulticastSocket,两条线程，其中
 * MulticastSocket既用于发送，也用于接收，其中一 条线程分别负责接受用户键盘输入，并向MulticastSocket
 * 发送数据，另一条线程则负责从MulticastSocket中读取数据。
 * 
 * @author wengqf
 *
 */
public class MulticastSocketTest implements Runnable {
	// 使用常量作为本程序的多点广播IP地址
	private static final String BROADCAST_IP = "224.0.0.1";
	// 使用常量作为本程序的多点广播目的的端口
	public static final int BROADCAST_PORT = 30000;
	// 定义每个数据报的最大大小为4K
	private static final int DATA_LEN = 4096;
	// 定义本程序的MulticastSocket实例
	private MulticastSocket socket = null;
	private InetAddress broadcastAddress = null;
	private Scanner scan = null;
	// 定义接收网络数据的字节数组
	byte[] inBuff = new byte[DATA_LEN];
	// 以指定字节数组创建准备接受数据的DatagramPacket对象
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	// 定义一个用于发送的DatagramPacket对象
	private DatagramPacket outPacket = null;

	public void init() throws IOException {

		try {
			// 创建用于发送、接收数据的MulticastSocket对象
			// 因为该MulticastSocket对象需要接收，所以有指定端口
			socket = new MulticastSocket(BROADCAST_PORT);
			broadcastAddress = InetAddress.getByName(BROADCAST_IP);
			// 将该socket加入指定的多点广播地址
			socket.joinGroup(broadcastAddress);
			// 设置本MulticastSocket发送的数据报被回送到自身
			socket.setLoopbackMode(false);
			// 初始化发送用的DatagramSocket,它包含一个长度为0的字节数组
			outPacket = new DatagramPacket(new byte[0], 0, broadcastAddress, BROADCAST_PORT);
			// 启动以本实例的run()方法作为线程体的线程
			new Thread(this).start();
			// 创建键盘输入流
			scan = new Scanner(System.in);
			// 不断读取键盘输入
			while (scan.hasNextLine()) {
				// 将键盘输入的一行字符串转换字节数组
				byte[] buff = scan.nextLine().getBytes();
				// 设置发送用的DatagramPacket里的字节数据
				outPacket.setData(buff);
				// 发送数据报
				socket.send(outPacket);
			}
		} finally {
			socket.close();
		}
	}

	public void run() {
		try {
			System.out.println("线程启动...");
			while(true){
				//读取Socket中的数据，读到的数据放在inPacket所封装的字节数组里。
				System.out.println(inPacket.getData());
				System.out.println(new String(inBuff , 0 , inPacket.getLength()));
				socket.receive(inPacket);
				//打印输出从socket中读取的内容
				System.out.println("聊天信息：" + new String(inBuff , 0 , inPacket.getLength()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				if (socket != null) {
					//让该Socket离开该多点IP广播地址
					socket.leaveGroup(broadcastAddress);
					//关闭该Socket对象
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
