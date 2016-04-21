package mvn.wrapper.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

public class ServerListener implements WrapperListener {

	static ServerSocket server = null;
	volatile static boolean isRun = false;

	@Override
	public void controlEvent(int event) {
		if ((event == WrapperManager.WRAPPER_CTRL_LOGOFF_EVENT) && WrapperManager.isLaunchedAsService()) {
			// Ignore
		} else {
			WrapperManager.stop(0);
		}
	}

	@Override
	public Integer start(String[] arg0) {
		try {
			server = new ServerSocket(8888);
			isRun = true;
			System.out.println("Listener port: 8888");
			while (isRun) {
				try {
					if (server == null || server.isClosed()) {
						break;
					}
					Socket client = server.accept();
					System.out.println("有新的连接..." + client.toString());
					InputStream inputStream = client.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					String line = null;
					while ((line = reader.readLine()) != null) {
						if (line.trim().equalsIgnoreCase("exit")) {
							isRun = false;
							System.out.println("Server exit...");
							if (server != null) {
								server.close();
							}
							if (reader != null) {
								reader.close();
							}
							break;
						} else {
							System.out.println("Server: " + line);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("start method: exit app...");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public int stop(int arg0) {
		isRun = false;
		System.out.println("Hook...");
		if (server != null) {
			try {
				System.err.println("Server.Hook.Close...");
				FileOutputStream out = new FileOutputStream(new File("F:\\hook.txt"));
				out.write("Server.Hook.Close...".getBytes());
				out.flush();
				out.close();
				server.close();
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Server.Hook...server is null");
			System.exit(0);
		}
		return 0;
	}

}
