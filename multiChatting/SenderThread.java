package multiChatting;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SenderThread extends Thread {
	Socket socket;
	String name;

	SenderThread(Socket socket, String userName) {
		this.socket = socket;
		this.name = userName;
	}

	public void run() {
		try {
			Scanner sc = new Scanner(System.in);
			PrintWriter w = new PrintWriter(socket.getOutputStream());
			w.println(name);
			w.flush();
			
			while (true) {
				String line = sc.nextLine();
				if (line.equals("bye")) {
					StringBuilder build = new StringBuilder();
					break;
				}
				w.println(line);
				w.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally { //Sender에서 socket을 닫아준다.
			Util.close(socket);
		}
	}
}
