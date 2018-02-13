package chatting;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SenderThread extends Thread {
	Socket socket;

	SenderThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			Scanner sc = new Scanner(System.in);
			PrintWriter w = new PrintWriter(socket.getOutputStream());

			while (true) {
				System.out.print(">");
				String line = sc.nextLine();
				if (line.equals("bye")) {
					
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
