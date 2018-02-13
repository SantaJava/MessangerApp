package multiChatting;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoomManager {
	Map<String, Room> map;
	
	private static RoomManager manager = new RoomManager();	
	
	private RoomManager() {
		map = new HashMap<>();
		map.put("애플", new Room("애플"));
		map.put("삼성", new Room("삼성"));
		map.put("구글", new Room("구글"));
	}
	
	public Room getRoom(String name) {
		return map.get(name);
	}
	
	public String getList() {
		String list = "";
		Set<String> keySet = map.keySet();
		//advanced for구문을 통해 가지고 있는 키 값을 전부 리턴해준다.
		for(String name: keySet) {
			list += name;
		}
		return list;
	}
	
	public static RoomManager getInstance() {
		return manager;
	}
}
