package util;

import java.sql.Connection;
import java.sql.DriverManager;

// JDBC드라이버를 로딩하고 Connection객체를 생성하는 메서드로 구성된 class
public class DBUtil {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}
	
	// 객체 생성하지 않고 만들 수 있도록 static 사용
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"JMS", "java");
		} catch (Exception e) {
			System.out.println("오라클 연결 실패!!!");
			e.printStackTrace();
		}
		return conn;
	}
}
