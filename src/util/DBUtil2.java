package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

// res폴더 생성(source folder) 후 db.properties(untitled text file) 생성

// db.properties파일의 내용으로 설정하는방법
// 방법1) Properties객체 이용
public class DBUtil2 {
	static Properties prop; // Properties객체 변수 선언;
	
	static {
		prop = new Properties();
		File f = new File("res/db.properties");
		
		try {
			FileInputStream fis = new FileInputStream(f);
			prop.load(fis); // 파일 읽기
			
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
			
			
		} catch (IOException e) {
			System.out.println("입출력 오류");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe",
//					"JMS", "java");
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));
		} catch (Exception e) {
			System.out.println("오라클 연결 실패!!!");
			e.printStackTrace();
		}
		return conn;
	}

}
