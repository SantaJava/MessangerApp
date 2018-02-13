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
				System.out.println("����: " + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} //Sender���� ������ �ݾ��ֹǷ� Reciever���� �ݾ��� �ʿ� ����.
	}
}
