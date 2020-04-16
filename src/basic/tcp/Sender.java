package basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

// 이 클래스는 소켓을 통해서 메시지를 보내는 역할을 담당한다.
public class Sender extends Thread {
	Socket socket;
	DataOutputStream dos;
	String name;
	
	// 생성자
	public Sender(Socket socket) {
		this.socket = socket;
		name = "[" + socket.getInetAddress() + ":" + socket.getPort() + "]";
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		while(dos != null) {
			try {
				dos.writeUTF(name + " " + sc.nextLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
