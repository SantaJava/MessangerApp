package echo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {
	public static void main(String[] args) {

		try {
			InetAddress local = InetAddress.getLocalHost();
			System.out.println("내 컴퓨터의 IP주소는 : " + local.getHostAddress());
			InetAddress[] iaArr = InetAddress.getAllByName("www.naver.com");
			for (InetAddress remote : iaArr) {
				System.out.println("www.naver.com 의 IP주소는: " + remote.getHostAddress());
			}
		} catch (UnknownHostException e) { //java.netUnknownHostException net클래스에 있는 것 유의.
			e.printStackTrace();
		}
	}
}
