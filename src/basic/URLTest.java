package basic;

import java.net.MalformedURLException;
import java.net.URL;

//URL클래스 ==> 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 관리하는 클래스 (인터넷 객체에서 필요한 것을 찾아내는 것)
public class URLTest {

	public static void main(String[] args) throws MalformedURLException {
		// ==> http://ddit.or.kr:80/index.html?ttt=123
		URL url = new URL("http","ddit.or.kr",80,"index.html?ttt=123");
		System.out.println("protocol : " + url.getProtocol());
		System.out.println("host :" + url.getHost());
		System.out.println("file : " + url.getFile());
		System.out.println("query : " + url.getQuery());
		System.out.println("path : " + url.getPath());
		System.out.println("port : " + url.getPort());
		
		System.out.println();
		System.out.println(url.toExternalForm());
		//일반적으로 사용하는 인터넷 주소 만드는 방법 
		System.out.println(url.getProtocol() + "://" +url.getHost()+":" + url.getPort() + "/" + url.getPath()+ "?" + url.getQuery());
	}

}
