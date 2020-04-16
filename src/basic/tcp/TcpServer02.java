package basic.tcp;

import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {

	// 데이터를 받는 클래스와 데이터를 보내는 클래스를 따로 구성하고
	// 이 클래스들은 쓰레드로 작동되도록 한다.
	public static void main(String[] args) {
		// 서버를 만들고 클라이언트가 접속하면 소켓을 만들어서
		// 데이터를 받는 클래스와 데이터를 보내는 클래스의
		// 인스턴스를 만들 때 이 소켓을 매개변수로 넣어준다.
		try {
			ServerSocket server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다.");
			
			Socket socket = server.accept();
			
			// 접속이 성공하면...
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
