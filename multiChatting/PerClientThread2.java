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
	Room room; // �濡 ���� �� ������ �ǰ�, �濡�� ���ö� null�� �ȴ�.
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
			name = reader.readLine(); // �̸� ����
			sendRoomList(); // ���� ����.

			while (true) {
				String line = reader.readLine(); // �޼��� ����
				if (line == null) // �� ��� ����
					break;
				if (line.charAt(0) == '#') { // ����ΰ��
					doCommand(line);
				} else { // ��ȭ�� ���
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
	 *            #enter, #leave, #bye �����ؼ� ó��
	 */
	public void doCommand(String command) {
		// #enter, #leave, #bye, #list�����ϱ�
		String arg = null;
		if (command.startsWith("#enter")) { // ������ �ϳ��� �� �ִ� �κ��� ó���ϱ� ����.
			String[] tokens = command.split(" ");
			command = tokens[0];
			arg = tokens[1]; // �� �̸��� �ش�.
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
		// case "#bye": //������ ����ɶ� �������ϱ� ��� ����.
		// break;
		default: // �ٸ� ����� ���� ��.
			sendResponse("Fail: " + command + "�� �߸��� ����Դϴ�.");
		}
	}

	public void enterRoom(String roomName) {
		room = manager.getRoom(roomName); //����Ʈ ����
		if (room != null) { // ��ȭ�� �̸��� ��Ȯ�� ��.
			room.enter(writer, name);
			sendResponse("OK: " + roomName + "�� ����");
			sendResponse(" ���� " +room.list.size());
		} else { // ��ȭ�� �̸��� �߸��Ǿ��� ���.
			sendResponse("Fail: " + roomName + "���� �����ϴ�.");
		}
	}

	public void leaveRoom() {
		if (room != null) { // �� �̸��� ��Ȯ�ϸ�
			room.leave(writer, name);
			room = null;
			sendResponse("Ok: ��ȭ�� ����");
		} else {
			sendResponse("Fail: ��ȭ�濡 �������� �ʾҽ��ϴ�.");
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
		writer.println("����    : " + list);
		writer.println(" ���� �ֽ��ϴ�.");
		writer.println(" [ #enter ���̸� | #list | #leave ] �� �ϳ��� �Է����ּ���.");
		writer.flush();
	}
}
