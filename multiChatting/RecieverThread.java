package multiChatting;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class RecieverThread extends Thread {
	StringBuilder sb = new StringBuilder();
	Socket socket;
	
	public RecieverThread(Socket socket) {
		this.socket = socket;
		}

	public void run() {
		try {
			BufferedReader r = BufferedReaderM.read(socket);	
			while (true) { //���� ������ ����ó��. ������ ������ ���̻� �����͸� ���� �� ��� null
				String message = r.readLine();
				if (message == null) {
					break; }
					System.out.println(message);
					sb.append(message + "\t\n");
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally { //������ ���ų� ���� �����ų� �ΰ��� ��쿡 �� save()�� ���ش�.
			save();
		} //Sender���� ������ �ݾ��ֹǷ� Reciever���� �ݾ��� �ʿ� ����.
	}
	
	public void save() {
		try(FileWriter writer = new FileWriter("c:/temp/dialog.txt")){
			writer.write(sb.toString());
			writer.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
