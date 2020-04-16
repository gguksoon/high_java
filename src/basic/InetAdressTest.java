package basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

//InetAddress클래스 ==> IP 주소를 다루기 위한 클래스 
public class InetAdressTest {

	public static void main(String[] args) throws UnknownHostException {
		//www.naver.com 의 IP 정보 가져오기 
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		System.out.println("HostNAme: " + naverIp.getHostName());
		System.out.println("HostAddress " + naverIp.getHostAddress());
		System.out.println();
		
		//자신의 컴퓨터의 IP주소 가져오기 
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 HostName : " + localIp.getHostName());
		System.out.println("내 컴퓨터의 HostAddress "  + localIp.getHostAddress());
		System.out.println();
		
		// IP주소가 여러개인 호스트의 정보 가져오기 
		InetAddress[] navers = InetAddress.getAllByName("www.naver.com");
		for(InetAddress nIp : navers) {
			System.out.println(nIp.toString());
//			Naver는 IP를 2개를 가지고 사용한다 
//			www.naver.com/125.209.222.141
//			www.naver.com/210.89.164.90
		}
		
		
	}

}
