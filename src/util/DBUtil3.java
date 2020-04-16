package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

/*
 * 	ResourceBundle객체 ==> 파일의 확장자가 '.properties'인 파일의 내용을 읽어와
 * 						 key값과 value값을 분리해서 그 정도를 갖고 있는 객체 
 *					 ==> 읽어올 파일은 'key값=value값' 형태로 되어 있어야 한다. 
 */

// db.properties의 내용을 읽어와 설정하기
// 방법2) ResourceBundle객체 이용
public class DBUtil3 {
	static ResourceBundle bundle;
	
	static {
		// ResourceBundle객체 생성하기
		// ==> ResourceBundle.getBunder("파일명")와 같이 생성하는데
		//		'파일명'을 지정할 때 확장자('.properties')는 붙이지 않는다.
		//		그 이유는 확장자는 항상 .properties이기 때문이다.
		bundle = ResourceBundle.getBundle("db"); // 객체생성(res폴더의 db.properties)
		
		try {
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			// 값 읽기: bundle.getString("key값");
			Class.forName(bundle.getString("driver"));
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패!!!");
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
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
		} catch (Exception e) {
			System.out.println("오라클 연결 실패!!!");
			e.printStackTrace();
		}
		return conn;
	}

}
