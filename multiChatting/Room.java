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
		w.println( " #" + name + "���� [" + this.name + "] �濡 �����̽��ϴ�.");
	}
	
	public void leave(PrintWriter w, String name) {
		list.remove(w);
		w.println(" #" + name + "���� �濡�� �����̽��ϴ�.");		
	}
	
	public void sendAll(String msg) {
		msg = String.format("[%s(%d��)] %s",name,list.size(),msg);
		
		for (PrintWriter writer : list) {
			if(this.writer != writer) {
			writer.println(msg);
			writer.flush();
			}
		}		
	}
}
