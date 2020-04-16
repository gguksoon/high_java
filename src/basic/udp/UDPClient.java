package basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 수신용, 송신용 패킷변수 선언
		DatagramPacket inpacket, outpacket;
		
		// 수신받은 데이터가 저장될 byte형 배열 선언
		byte[] bMsg = new byte[512];
		
		try {
			// 송수신을 담당하는 UDP소켓 생성
			DatagramSocket socket = new DatagramSocket();
			
			// 받을 곳의 주소 객체 생성(송신할 곳)
			InetAddress address = InetAddress.getByName("localhost");
			
			while(true) {
				// 전송할 메시지 입력
				System.out.println("보낼 메시지 입력: ");
				String msg = sc.nextLine();
				
				if("/end".equals(msg)){
					break;
				}
				
				// 전송할 패킷 생성
				outpacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, 8888);
				
				// 전송
				socket.send(outpacket);
				
				// 서버에서 온 데이터 받아서 출력하기
				
				// 수신용 패킷 객체 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 수신하기
				socket.receive(inpacket);
				
				System.out.println("서버 응답: " + new String(inpacket.getData()));
				System.out.println();
			}
			System.out.println("통신 끝");
			socket.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
