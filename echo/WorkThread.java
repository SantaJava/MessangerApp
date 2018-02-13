package echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkThread extends Thread {
	private Socket socket;

	public WorkThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter w = new PrintWriter(socket.getOutputStream());
			
			//메세지 수신
			String line = r.readLine();
			System.out.println("서버 수신 메세지: " + line);
			
			//메세지 전송
			w.println(line);
			w.flush(); //flush 빼먹지 않기.
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Util.close(socket);
		}
	}
}