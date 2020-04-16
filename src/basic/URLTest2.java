package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class URLTest2 {
	
	//URLConnection ==> 어플리케이션과 URL간의 통신연결을 위한 추상클래스
	public static void main(String[] args) throws IOException {
		//특정 서버의 정보와 파일 내용 출력하기 
		URL url = new URL("https://www.naver.com/index.html");
		
		//URLConnection 객체 생성
		 URLConnection urlCon = url.openConnection();
		//Header정보 출력하기. 정보를 가져올떄는 보통 Map을 이용한다.
		 Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		 Iterator<String> it = headerMap.keySet().iterator();
		 while(it.hasNext()) {
			 String key = it.next();
			 System.out.println(key + " : " + headerMap.get(key));
		 }
		 System.out.println("============================================");
		 
		 //해당 호스트의 페이지 내용 가져오기 
		 /*
		 //방법1 ==> URLConnection의 getInputStream()메서드 이용하기 
		 //파일을 읽어오기 위한 스트림 객체 생성 
		 InputStream is = urlCon.getInputStream();
		 InputStreamReader isr = new InputStreamReader(is, "utf-8");
		 BufferedReader br = new BufferedReader(isr);
		 
		 //내용을 읽어와 출력하기
		 while(true) {
			 String str = br.readLine(); //한줄씩 읽기 
			 if(str==null) break;
			 System.out.println(str);
		 }
		 br.close();//스트림 닫기
		 */
		
		 //방법 2 ==> URL객체의 openStream()메서드 이용하기 
		 InputStream is = url.openStream();
		 InputStreamReader isr = new InputStreamReader(is, "utf-8");
		 BufferedReader br = new BufferedReader(isr);
		 
		 //내용을 읽어와 출력하기
		 while(true) {
			 String str = br.readLine(); //한줄씩 읽기 
			 if(str==null) break;
			 System.out.println(str);
		 }
		 br.close();//스트림 닫기
	}

}






















