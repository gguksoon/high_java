package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * JDBC(Java DataBase Connectivity)
 * 
 * - 작업 순서
 * 0. 현재 프로젝트에 해당 DB의 JDBC드라이버 라이브러리를 등록해 놓는다.
 * 		(Build Path에 등록한다.)
 *	자바프로젝트 우클릭 => Build Path => Configure Build Path => Libraries =>
 *	Add External JARs => E:\C_Lib\ibatis에 있는 ojdbc6.jar 열기
 *
 * 1. 드라이버 로딩 ==> JDBC드라이버를 메모리에 적재
 * 		Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * 2. 해당 DB시스템에 접속하기 ==> 접속이 성공되면 Connection객체가 생성된다.
 * 		DriverManager.getConnection()메서드를 이용한다.
 * 
 * 3. 질의 작업 ==> SQL명령을 실행한다.
 * 		Statement객체 또는 PreparedStatement객체를 이용하여 SQL문장을 실행한다.
 * 		(Statement, PreparedStatement는 Connection객체를 이용하여 생성한다.)
 * 
 * 4. 질의 결과를 받아서 처리한다.
 * 	1) SQL문이 select일 경우 ==> ResultSet객체가 만들어진다.
 * 		- ResultSet객체에는 select문을 실행한 결과가 저장된다.
 * 	2) SQL문이 insert, update, delete등 일 경우 ==> 정수값이 반환된다.
 * 		- 정수값은 보통 실행에 성공한 레코드 수를 의미한다.
 * 
 * 5. 자원 정리 ==> 사용했던 객체들을 모두 닫아준다.
 * 
 */


public class JdbcTest01 {

	public static void main(String[] args) {
		// DB작업에 필요한 객체 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;	// 쿼리문이 select문일 경우에만 필요
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB접속 ==> Connection객체 생성
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", // @뒤에는 오라클시스템에 따라 바꿔주면 됨(SQL Developer의 +를 클릭했을 때 나옴)
					"JMS", // 사용자 아이디
					"java"); // 비밀번호
			
			// 3. Statement객체 생성 ==> Connection객체 이용
			String sql = "SELECT * FROM LPROD"; // 실행할 SQL문장
			stmt = conn.createStatement(); // Statement객체 생성
			
			// 4. SQL문을 Statement객체를 이용해서 실행하고 그 결과를 ResultSet객체에 저장
			// executeQuery()메서드 ==> 쿼리문이 'select'문일 경우에 사용
			//					  ==> 반환값: ResultSet객체
			// executeUpdate()메서드 ==> 쿼리문이 'select'문이 아닐 경우에 사용
			//					   ==> 반환값: 정수(작업 성공 레코드 수)
			rs = stmt.executeQuery(sql);
			
			// 4-1. ResultSet객체에 저장되어 있는 자료를 반복문과 next()메서드를 이용하여 차례로 읽어와 처리
			
			// rs.next() ==> ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드 위치로 이동시키고,
			//				  그 곳에 자료가 있으면 true, 없으면 false를 반환한다.
			while(rs.next()) {
				// 현재 ResultSet객체의 포인터가 가리키는 곳의 자료를 읽어와서 처리하는 작업을 기술한다.
				
				// 자료를 읽어오는 방법
				// 형식1) rs.get자료형이름("컬럼명");
				// 형식2) rs.get자료형이름(컬럼번호);
				// 형식3) rs.get자료형이름("컬럼의 엘리어스명");
				System.out.print("LPROD_ID: " + rs.getInt("LPROD_ID"));
				System.out.print(", LPROD_GU: " + rs.getString(2)); // 숫자는 컬럼 번호를 의미(1번부터 시작)
				System.out.print(", LPROD_NM: " + rs.getString(3));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원 정리 ==> 가장 늦게 열린 것 부터 닫아주기
			if(rs != null) { 
				try { rs.close(); } catch(SQLException e) { }
			}
			if(stmt != null) {
				try { stmt.close(); } catch(SQLException e) { }
			}
			if(conn != null) {
				try { conn.close(); } catch(SQLException e) { }
			}
			
		}
	}

}
