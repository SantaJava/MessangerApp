package echo;

import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer { //accept만 실행시켜주는 socket. 
	public static void main(String[] args) {

		//Socket socket = null;
		//thread를 쓸 때 try with 를 쓸 수 없다. 
		try (ServerSocket server = new ServerSocket(10000)) {
			while (true) {
				Socket socket = server.accept(); // accept가 안들어오면 여기서 block.
				WorkThread work = new WorkThread(socket);			
				work.start();			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
