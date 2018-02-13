package multiChatting;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Room {
	List<PrintWriter> list;
	
	String name;
	PrintWriter writer;
	
	public Room(String name) {
		this.name = name;
		this.list = Collections.synchronizedList(new ArrayList<PrintWriter>());
	}
	
	public void enter(PrintWriter w, String name) {
		this.writer = w; 
		list.add(w);
		w.println( " #" + name + "님이 [" + this.name + "] 방에 들어오셨습니다.");
	}
	
	public void leave(PrintWriter w, String name) {
		list.remove(w);
		w.println(" #" + name + "님이 방에서 나가셨습니다.");		
	}
	
	public void sendAll(String msg) {
		msg = String.format("[%s(%d명)] %s",name,list.size(),msg);
		
		for (PrintWriter writer : list) {
			if(this.writer != writer) {
			writer.println(msg);
			writer.flush();
			}
		}		
	}
}
