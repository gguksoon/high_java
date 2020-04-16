package basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
	UDP방식 : 비연결 지향, 비신뢰성, 데이터가 순서대로 도착한다는 보장을 못한다.
			하지만 TCP보다 속도가 빠르다.
			(DatagramSocket과 DatagramPacket를 사용한다.)
		- DatagramSocket ==> 데이터의 송수신과 관련된 클래스(우체부)
		- DatagramPacket ==> 주고 받을 데이터와 관련된 클래스(소포)
						 ==> 수신을 위한 생성자와 송신을 위한 생성자를 제공한다.
						 
		- TCP는 스트림을 이용해서 데이터를 주고 받지만 UDP는 데이터그램을 이용해서 데이터를 주고 받는다.
	
	
*/
public class UDPServer {

	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 소켓을 생성한다.
			DatagramSocket socket = new DatagramSocket(8888);
			
			// 수신용 패킷 변수와 송신용 패킷 변수 선언
			DatagramPacket inpacket, outpacket;
			System.out.println("서버 실행 중...");
			
			while(true) {
				byte[] bMsg = new byte[512]; // 데이터가 저장될 byte형 배열 선언
				
				// 수신용 패킷 객체 생성 ==> 데이터가 저장될 byte형 배열과 길이를 설정한다.
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 데이터를 수신한다.(데이터가 올 때까지 기다린다.)
				// 수신된 데이터의 패킷 정보가 inpacket에 저장된다.
				socket.receive(inpacket);
				
				// 수신 받은 패킷에서 상대방의 주소, 포트번호등을 알 수 있다.
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				System.out.println("상대방의 IP와 포트번호: " + address + "(" + port + ")");
				
				// 상대방이 보낸 메시지 출력하기
				// 		==> 수신 받은 데이터는 byte형 배열이기 때문에 이것은 String으로 변환해서 사용한다.
				String msg = new String(inpacket.getData(), 0, inpacket.getLength());
				System.out.println("상대방이 보낸 메시지: " + msg);
				
				// --------------------------------------------------------------------
				
				/*
					상대방에게 메시지 보내기 (수신받은 데이터를 그대로 전송하기)
					
					송신용 패킷객체 생성
						==> 전송할 데이터가 저장된 byte형 배열,
							전송할 데이터의 길이(배열의 길이),
							상대방주소, 상대방포트번호
				*/
				byte[] sendMsg = msg.getBytes(); // String을 byte형 배열로 변환
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// 송신하기
				socket.send(outpacket);
				
			}
		} catch (Exception e) { }
		
	}

}






