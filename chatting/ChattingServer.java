package chatting;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ChattingServer {
	public static void main(String[] args) {
		Socket socket = null;
		try (ServerSocket serverSocket = new ServerSocket(9001)) {
			socket = serverSocket.accept();
			Thread thread1 = new SenderThread(socket);
			Thread thread2 = new RecieverThread(socket);
			thread1.start();
			thread2.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
