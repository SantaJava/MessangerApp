package echo;

import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer { //accept�� ��������ִ� socket. 
	public static void main(String[] args) {

		//Socket socket = null;
		//thread�� �� �� try with �� �� �� ����. 
		try (ServerSocket server = new ServerSocket(10000)) {
			while (true) {
				Socket socket = server.accept(); // accept�� �ȵ����� ���⼭ block.
				WorkThread work = new WorkThread(socket);			
				work.start();			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
