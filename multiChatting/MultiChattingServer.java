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
				Thread thread = new PerClientThread2(socket); //Ŭ���̾�Ʈ �ϳ��� thread�ϳ���. 
				thread.start();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
