package basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {

	public static void main(String[] args) throws IOException {
		// TCP소켓 통신을 하기 위해 ServerSocket객체 생성한다. 
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 접속을 기다립니다.");
		/*
		 	accept()메서드는 client에서 연결 요청이 올 때까지 계속 기다린다. 
		 	연결 요청이 오면 Socket 객체를 생성해서 client의 Socket과 연결한다. 
		 */
		Socket socket = server.accept();
		
		//이 이후의 내용은 접속이 완료된 후에 처리할 내용을 기술한다. 
		System.out.println("클라이언트가 연결되었습니다.");
		System.out.println();
		
		System.out.println("접속한 클라이언트 정보");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("port번호 : " + socket.getPort());
		
		System.out.println();
		System.out.println("연결된 서버의 정보");
		System.out.println("서버의 IP 주소 : " + socket.getLocalAddress());
		System.out.println("서버의 port 번호 : " + socket.getLocalPort());
		System.out.println();

		// Client에게 메시지 보내기
		// ==>OutputStream 객체를 구성해서 전송한다.
		// ==>Socket의 getOutputStream()메서드를 이용하여 생성한다.
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		// 메시지 보내기
		dos.writeUTF("어서오세요. 반갑습니다.");
		System.out.println("메시지를 보냈습니다.");
		// 소켓과 연결 스트림을 닫는다.
		dos.close();
		socket.close();
		server.close();
	}

}
