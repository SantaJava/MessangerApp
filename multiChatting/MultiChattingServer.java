package multiChatting;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiChattingServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(10000);
			while (true) {
				Socket socket = serverSocket.accept();
				Thread thread = new PerClientThread2(socket); //클라이언트 하나당 thread하나씩. 
				thread.start();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
