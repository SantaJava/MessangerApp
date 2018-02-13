package echo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {
	public static void main(String[] args) {

		try {
			InetAddress local = InetAddress.getLocalHost();
			System.out.println("�� ��ǻ���� IP�ּҴ� : " + local.getHostAddress());
			InetAddress[] iaArr = InetAddress.getAllByName("www.naver.com");
			for (InetAddress remote : iaArr) {
				System.out.println("www.naver.com �� IP�ּҴ�: " + remote.getHostAddress());
			}
		} catch (UnknownHostException e) { //java.netUnknownHostException netŬ������ �ִ� �� ����.
			e.printStackTrace();
		}
	}
}
