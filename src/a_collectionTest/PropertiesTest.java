package a_collectionTest;

import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		/*
		 * Properties는 Map보다 축소된 기능의 객체라고 할 수 있다.
		 * 	- Map은 모든 형태의 데이터를 key와 value값으로 사용할 수 있지만,
		 *	  Properties는 key와 value값으로 String만 사용할 수 있다.
		 * 	- Map은 put(), get()메서드를 이용하여 데이터를 입출력하지만,
		 * 	  Properties는  setProperty(), getProperty()메서드를 이용하여
		 * 	    데이터를 입출력한다.
		 */
		
		Properties prop = new Properties();
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("tel", "010-1111-1111");
		prop.setProperty("addr", "대전시");
		
		System.out.println("prop => " + prop);
		
		System.out.println("＊ 이　　름: " + prop.getProperty("name"));
		System.out.println("＊ 전화번호: " + prop.getProperty("tel"));
		System.out.println("＊ 주　　소: " + prop.getProperty("addr"));
		
		// Properties는 파일에 저장하고 불러오는 기능들이 있다.
		// 나중에 DB를 사용할 때 배울예정
		
	}

}
