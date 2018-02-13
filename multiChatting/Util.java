package multiChatting;

import java.io.IOException;
import java.net.Socket;

public class Util {
	public static void close(Socket s) {
		try {
			s.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
