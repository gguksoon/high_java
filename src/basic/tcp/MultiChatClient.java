package basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {

	public static void main(String[] args) {
		new MultiChatClient().clientStart();
	}
	
	// 클라이언트 시작 메서드
	public void clientStart() {
		Socket socket = null;
		try {
			String serverIp = "localhost";
			socket = new Socket(serverIp, 7777);
			System.out.println("서버에 연결되었습니다.");
			
			// 메시지 전송용  Thread객체 생성
			ClientSender cSender = new ClientSender(socket);
			
			// 메시지 수신용 Thread객체 생성
			ClientReceiver cReceiver = new ClientReceiver(socket);
			
			// Thread 실행
			cSender.start();
			cReceiver.start();
			
		} catch (Exception e) {	}
	}
	
	// -----------------------------------------
	// 메시지 전송용 Thread
	class ClientSender extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		String name;
		Scanner sc = new Scanner(System.in);
		
		// 생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			try {
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				
				if(out != null) {
					// 프로그램이 처음 시작하면 대화명을 입력받아 서버로 전송하고
					// 대화명 중복여부를 feedback으로 받아서 확인한다.
					System.out.println("대화명 입력 : ");
					String name = sc.next();
					while(true) {
						out.writeUTF(name);
						String feedback = in.readUTF();
						if("이름중복".equals(feedback)) {
							System.out.println(name + "은 이름이 중복됩니다. 다른 대화명을 입력하세요.");
							System.out.println("대화명 입력 : ");
							name = sc.next();
						} else { // 대화명이 중복되지 않으면
							sc.nextLine(); // 입력버퍼 비우기
							this.name = name;
							System.out.println(name + "이름으로 대화방에 입장하셨습니다.");
							break;
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} // 생성자 끝...
		
		@Override
		public void run() {
			try {
				while(out != null) {
					// 키보드로 입력받은 데이터를 서버로 전송
					out.writeUTF("[" + name + "] " + sc.nextLine());
				}
			} catch (Exception e) {	}
		}
	}
	
	// -------------------------------------------------------
	
	class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		
		// 생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				in = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {	}
		}
		
		@Override
		public void run() {
			while(in != null) {
				try {
					// 서버로부터 수신한 메시지를 출력한다.
					System.out.println(in.readUTF());
				} catch (Exception e) {	}
			}
		}
	}
	
}
