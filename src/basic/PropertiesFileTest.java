package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesFileTest {
	// db.properties파일을 읽어와 Properties객체로 처리하는 예제
	public static void main(String[] args) {
		// 읽어온 자료를 저장할 Properties객체 생성
		Properties prop = new Properties();
		
		// 읽어올 파일명을 이용한 File객체 생성
		File file = new File("res/db.properties");
		
		try {
			// 파일 내용을 읽기 위해 FileInputStream객체 생성
			FileInputStream fis = new FileInputStream(file);
			
			// Properties객체의 load()메서드를 이용하여 파일 내용을 읽어온다.
			prop.load(fis); // 파일 내용을 읽어서 key와 value값으로 분류한 후
							// Properties객체에 저장한다. 
							// (key: driver / value: oracle.jdbc.driver.OracleDriver)
			
			// 읽어온 정보 출력해보기
			
			// key값만 읽어와 Enumeration(iterator의 구버전)
			Enumeration<String> names = 
					(Enumeration<String>) prop.propertyNames();
			
			// key값 개수만큼 반복해서 값 출력해 보기
			while( names.hasMoreElements() ) { // 이터레이터의 hasNext()와 같은 기능
				String key = names.nextElement(); // 키 값 구하기
				String value = prop.getProperty(key); // 키 값으로 value 구하기
				System.out.println(key + " => " + value);
			}
			
			System.out.println("출력 끝");
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
