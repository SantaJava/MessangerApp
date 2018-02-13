package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class BufferedReaderM {
	
	public static BufferedReader read(Socket socket) throws IOException {
		return new BufferedReader(new InputStreamReader(socket.getInputStream()));	
	}
}
