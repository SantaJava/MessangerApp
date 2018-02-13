package chatting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class RecieverThread extends Thread {
	Socket socket;

	public RecieverThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			BufferedReader r = BufferedReaderM.read(socket);

			while (true) {
				String message = r.readLine();
				if (message == null) {
					break;
				}
				System.out.println("수신: " + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} //Sender에서 소켓을 닫아주므로 Reciever에서 닫아줄 필요 없음.
	}
}
