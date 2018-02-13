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
			while (true) { //내가 끊으면 예외처리. 상대방이 끊으면 더이상 데이터를 읽을 수 없어서 null
				String message = r.readLine();
				if (message == null) {
					break; }
					System.out.println(message);
					sb.append(message + "\t\n");
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally { //상대방이 끊거나 내가 나가거나 두가지 경우에 다 save()를 해준다.
			save();
		} //Sender에서 소켓을 닫아주므로 Reciever에서 닫아줄 필요 없음.
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
