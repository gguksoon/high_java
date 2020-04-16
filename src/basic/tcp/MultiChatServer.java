package basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiChatServer {

	// 대화명과 클라이언트의 Socket을 저장할 대화방(HashMap) 정의
	Map<String, Socket> clients;
	
	// 생성자
	public MultiChatServer() {
		clients = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	// 서버를 시작하는 메서드
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
				socket = server.accept();
				System.out.println("**" + socket.getInetAddress() + "에서 접속했습니다.");
				
				// 서버에서 클라이언트로 메시지를 전송할 쓰레드 생성
				ServerReceiver th = new ServerReceiver(socket);
				th.start();
			} 
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(server != null) {
				try { server.close(); } catch (IOException e) { }
			}
		}
	}
	
	// 대화방에 있는 전체 사용자에게 메시지를 전송하는 메서드
	public void sendToAll(String msg) {
		// 대화방에 접속한 사용자의 대화명 리스트 추출
		Iterator<String> it = clients.keySet().iterator();
		
		while(it.hasNext()) {
			try {
				String name = it.next(); // 대화명 구하기 즉, key값 구하기
				
				// 대화명에 해당하는 소켓을 이용한 OutputStream객체 생성
				DataOutputStream out = 
						new DataOutputStream(clients.get(name).getOutputStream());
						
				out.writeUTF(msg); // 메시지 전송
				
			} catch (Exception e) {	}
		}
	}
	
	public static void main(String[] args) {
		new MultiChatServer().serverStart();
	}
	
	// 서버에서 클라이언트로 메시지를 전송할 Thread
	class ServerReceiver extends Thread {
		// 대화방 변수에 접근하기 위해 Inner Class로 작성
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				// 클라이언트 소켓에서 데이터를 수신받기 위한 InputStream객체 생성
				in = new DataInputStream(socket.getInputStream());
				
				// 클라이언트 소켓에서 데이터를 송신하기 위한 OutputStream객체 생성
				out = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) { }
		}
		
		@Override
		public void run() {
			String name = "";
			try {
				while(true) { 
					// 서버에서는 최초에 클라이언트가 보낸 대화명을 받아서
					// 대화명의 중복 여부를 feedBack으로 클라이언트에게 보내준다.
					name = in.readUTF();
					
					if(clients.containsKey(name)) { // 대화명이 중복되면 
						out.writeUTF("이름중복");
					} else { // 중복되지 않으면..
						out.writeUTF("OK");
						break;
					}
				}
				
				// 이 부분으로 넘어오면 중복되지 않는다는 의미임
				// 대화명을 받아서 전체의 클라이언트에게 대화방 참여 메시지를 보낸다.
				sendToAll("[" + name + "]님이 들어오셨습니다." );
				
				// 대화명과 클라이언트의 Socket객체를 대화방 Map에 추가한다.
				clients.put(name, socket);
				System.out.println("현재 접속자 수: " + clients.size());
				
				// 한 클라이언트가 전송한 메시지를 받아서 전체 클라이언트에게 메시지를 보낸다.
				while(in != null) {
					sendToAll(in.readUTF());
				}
				
			} catch (Exception e) { 
				 
			} finally {
				// 이 finally절이 실행된다는 것은 클라이언트가 빠져나간 것을 의미한다.
				sendToAll("[" + name + "]님이 나가셨습니다.");
				
				// 대화방 Map에서 해당 대상자를 삭제한다.
				clients.remove(name);
				System.out.println("현재 접속자수: " + clients.size());
			}
		}
			
	}

}
