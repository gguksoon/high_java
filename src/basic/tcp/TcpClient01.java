package basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String serverIp = "localhost";
		// 자기 자신 컴퓨터를 나타내는 방법
		// ip : 127.0.0.1
		// 컴이름 : localhost
		
		System.out.println(serverIp + " 서버에 연결 중입니다.");
		
		// 소켓객체를 생성해서 연결을 요청한다.
		Socket socket = new Socket(serverIp, 7777);
		
		// 이 이후에는 서버에 연결될 후의 작업을 기술한다.
		System.out.println("서버에 연결되었습니다...");
		System.out.println();
		
		System.out.println("연결된 서버 정보");
		System.out.println("IP 주소: " + socket.getInetAddress().getHostAddress());
		System.out.println("Port 번호: " + socket.getPort());
		System.out.println();
		System.out.println("접속한 클라이언트 정보");
		System.out.println("IP 주소: " + socket.getLocalAddress());
		System.out.println("Port 번호: " + socket.getPort());
		System.out.println();
		
		// 서버에서 보내온 메시지를 받기 위해 InputStream객체를 생성한다.
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		System.out.println("서버로부터 받은 메시지 : " + dis.readUTF());
		
		System.out.println("연결을 종료합니다...");
		
		// 소켓과 스트림 닫기
		dis.close();
		socket.close();
		
		
		
	}

}
