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
			
			//�޼��� ����
			String line = r.readLine();
			System.out.println("���� ���� �޼���: " + line);
			
			//�޼��� ����
			w.println(line);
			w.flush(); //flush ������ �ʱ�.
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Util.close(socket);
		}
	}
}