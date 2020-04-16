package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 *  문제1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다 
 *  	  lprod_id가 큰 자료들을 출력하시오.
 *  문제2) 사용자로부터 lprod_id값을 2개 입력 받아서 두 값 중 작은 값부터
 *  	    큰 값 사이의 자료들을 출력하시오.
 */
public class JdbcTest02 {

	private Scanner sc = new Scanner(System.in);
	
	// 생성자
	public JdbcTest02() {
		// 드라이버 로딩 ==> 이 작업은 한 프로그램에서 한번만 실행해도 된다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new JdbcTest02().ex1(); // 문제1
		new JdbcTest02().ex2(); // 문제2
	}
	
	public void ex1() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			System.out.print("LPROD_ID값 입력: ");
			int num = Integer.parseInt(sc.nextLine());
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"JMS",
					"java");
			String sql = "SELECT * " +
						   "FROM LPROD " + 
					      "WHERE LPROD_ID > " + num;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);

			System.out.println("------------------------------------");
			while(rs.next()) {
				System.out.println("LPROD_ID: " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU: " + rs.getString("LPROD_GU"));
				System.out.println("LPROD_NM: " + rs.getString("LPROD_NM"));
				System.out.println("------------------------------------");
			}
			System.out.println("출력 끝..");
		} catch (SQLException e) {
			
		} finally {
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

	public void ex2() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			System.out.print("LPROD_ID값 2개 입력: ");
			int num = sc.nextInt();
			int num2 = sc.nextInt();
			int max = Math.max(num, num2);
			int min = Math.min(num, num2);
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"JMS",
					"java");
			String sql = "SELECT * " +
						   "FROM LPROD " + 
					      "WHERE LPROD_ID BETWEEN " + min + " AND " + max;
			
			stmt = conn.createStatement();
			
			// executeQuery()메서드 ==> 쿼리문이 'select'문일 경우에 사용
			//					  ==> 반환값: ResultSet객체
			// executeUpdate()메서드 ==> 쿼리문이 'select'문이 아닐 경우에 사용
			//					   ==> 반환값: 정수(작업 성공 레코드 수)
			rs = stmt.executeQuery(sql);

			System.out.println("------------------------------------");
			while(rs.next()) {
				System.out.println("LPROD_ID: " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU: " + rs.getString("LPROD_GU"));
				System.out.println("LPROD_NM: " + rs.getString("LPROD_NM"));
				System.out.println("------------------------------------");
			}
			System.out.println("출력 끝..");
		} catch (SQLException e) {
			
		} finally {
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
