package multiChatting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import echo.Util;

public class PerClientThread2 extends Thread {
	Socket socket;
	PrintWriter writer;
	RoomManager manager;
	Room room; // 방에 들어갔을 때 설정이 되고, 방에서 나올때 null이 된다.
	String name;

	PerClientThread2(Socket socket) {
		this.socket = socket;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			manager = RoomManager.getInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			name = reader.readLine(); // 이름 수신
			sendRoomList(); // 방목록 전송.

			while (true) {
				String line = reader.readLine(); // 메세지 수신
				if (line == null) // 방 목록 전송
					break;
				if (line.charAt(0) == '#') { // 명령인경우
					doCommand(line);
				} else { // 대화인 경우
					sendDialog(name, line);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			leaveRoom();
			Util.close(socket);
		}
	}

	public void sendResponse(String msg) {
		writer.println(msg);
		writer.flush();
	}

	/**
	 * @param command
	 *            #enter, #leave, #bye 구분해서 처리
	 */
	public void doCommand(String command) {
		// #enter, #leave, #bye, #list구분하기
		String arg = null;
		if (command.startsWith("#enter")) { // 정보가 하나가 더 있는 부분을 처리하기 위해.
			String[] tokens = command.split(" ");
			command = tokens[0];
			arg = tokens[1]; // 방 이름에 해당.
		}

		switch (command) {
		case "#enter":
			enterRoom(arg);
			break;
		case "#leave":
			leaveRoom();
			break;
		case "#list":
			sendRoomList();
			break;
		// case "#bye": //어차피 종료될때 나가지니까 상관 없다.
		// break;
		default: // 다른 명령을 쳤을 때.
			sendResponse("Fail: " + command + "은 잘못된 명령입니다.");
		}
	}

	public void enterRoom(String roomName) {
		room = manager.getRoom(roomName); //리스트 생성
		if (room != null) { // 대화방 이름이 정확할 때.
			room.enter(writer, name);
			sendResponse("OK: " + roomName + "방 입장");
			sendResponse(" 현재 " +room.list.size());
		} else { // 대화방 이름이 잘못되었을 경우.
			sendResponse("Fail: " + roomName + "방이 없습니다.");
		}
	}

	public void leaveRoom() {
		if (room != null) { // 방 이름이 정확하면
			room.leave(writer, name);
			room = null;
			sendResponse("Ok: 대화방 퇴장");
		} else {
			sendResponse("Fail: 대화방에 입장하지 않았습니다.");
		}
	}

	public void sendDialog(String name, String msg) {
		if (room != null) {
			room.sendAll(name + ">" + msg);
		}
	}

	public void sendRoomList() {
		String list = manager.getList();
		writer.println("==============");
		writer.println("현재    : " + list);
		writer.println(" 방이 있습니다.");
		writer.println(" [ #enter 방이름 | #list | #leave ] 중 하나를 입력해주세요.");
		writer.flush();
	}
}
