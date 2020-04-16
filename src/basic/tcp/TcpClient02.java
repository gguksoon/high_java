package basic.tcp;

import java.net.Socket;

public class TcpClient02 {

	public static void main(String[] args) {

		try {
//			Socket socket = new Socket("127.0.0.1", 7777);
//			Socket socket = new Socket("192.168.0.117", 7777); // 소리누나 주소
			Socket socket = new Socket("192.168.0.167", 7777); // 소리누나 주소
			System.out.println("서버에 연결되었습니다...");
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
