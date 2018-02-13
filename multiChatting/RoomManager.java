package multiChatting;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoomManager {
	Map<String, Room> map;
	
	private static RoomManager manager = new RoomManager();	
	
	private RoomManager() {
		map = new HashMap<>();
		map.put("����", new Room("����"));
		map.put("�Ｚ", new Room("�Ｚ"));
		map.put("����", new Room("����"));
	}
	
	public Room getRoom(String name) {
		return map.get(name);
	}
	
	public String getList() {
		String list = "";
		Set<String> keySet = map.keySet();
		//advanced for������ ���� ������ �ִ� Ű ���� ���� �������ش�.
		for(String name: keySet) {
			list += name;
		}
		return list;
	}
	
	public static RoomManager getInstance() {
		return manager;
	}
}
