package echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	public static void main(String[] args) {
		// try (Socket socket = new Socket("127.0.0.1", 10000)) {
		// InputStream in = socket.getInputStream();
		// OutputStream out = socket.getOutputStream();
		//
		// String str = "Hello, Server";
		// out.write(str.getBytes());
		// out.flush();
		//
		// byte arr[] = new byte[100];
		// in.read(arr);
		// System.out.println(new String(arr));
		//
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }

		try (Socket socket = new Socket("127.0.0.1", 10000)) {

			// 수신용 reader 만들기
			BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 전송용 writer만들기
			PrintWriter w = new PrintWriter(socket.getOutputStream());
			Scanner sc = new Scanner(System.in);

			System.out.println("문자열 입력> ");
			String line = sc.nextLine();

			// 메세지 전송
			w.println(line);
			w.flush();

			// 메세지 수신
			String recieveLine = r.readLine();
			System.out.println("수신 메세지: " + recieveLine);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
