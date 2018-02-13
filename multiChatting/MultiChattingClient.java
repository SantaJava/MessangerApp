package multiChatting;

import java.net.Socket;
import java.util.Scanner;

public class MultiChattingClient {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("user name > ");
		String userName = sc.nextLine();
		try {
			Socket socket = new Socket("127.0.0.1", 10000);
			Thread thread1 = new SenderThread(socket, userName);
			Thread thread2 = new RecieverThread(socket);
			thread1.start();
			thread2.start();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
