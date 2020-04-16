package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;	// select문을 사용하기 때문에 필요
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"JMS", "java");
			
			System.out.println("은행 정보 추가하기");
			System.out.println();
			
			// --> 기본키값이 중복되면 에러가 발생한다.
			// 방지하기 위해서는 아래와 같이 수행하여 반환값이 1인지 확인한다.
			// SELECT COUNT(*) FROM BANKINFO WHERE BANK_NO = 입력값;
			int count = 0;
			String bankNo = null;
			do {
				System.out.print("계좌 번호: ");
				bankNo = sc.nextLine();
				// 계좌번호 중복 여부 검사
				String sql2 = "SELECT COUNT(*) FROM BANKINFO WHERE BANK_NO = ?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, bankNo);
				rs = pstmt2.executeQuery();
				
				// select문의 실행 결과가 항상 1개(튜플이 1개)이면 while문이 필요없다.
				if(rs.next()){
					count = rs.getInt(1);
				}
				if(count == 1) {
					System.out.println("계좌번호 \"" + bankNo + "\"는 이미 등록된 번호입니다.");
					System.out.println("다른 계좌번호를 입력하세요.");
				}
			} while(count == 1);
			System.out.print("은행명: ");
			String bankName = sc.nextLine();
			
			System.out.print("예금주 명: ");
			String bankUserName = sc.nextLine();
			
			// 쿼리문 =================================================================
			
//			// Statement객체를 이용한 처리
//			String sql = "INSERT INTO Bankinfo(bank_no, bank_name, bank_user_name, bank_date) "
//					+ "values('" + bankNo + "', '" + bankName + "', '" + bankUserName + "', " + "sysdate)";
//			
//			System.out.println(sql);
//			
//			stmt = conn.createStatement();
//			
//			int cnt = stmt.executeUpdate(sql);
			
			// ---------------------------------------------------------------------
			
			// PreparedStatement객체를 이용한 처리
			// 쿼리문의 물음표(?)는 나중에 데이터가 들어갈 자리를 의미한다.
			String sql = "INSERT INTO Bankinfo(bank_no, bank_name, bank_user_name, bank_date) "
					+ "values(?, ?, ?, sysdate)";
			
			// PreparedStatement객체를 생성할 때 쿼리문을 매개값으로 넣어준다.
			pstmt = conn.prepareStatement(sql);
			
			// 물음표(?)자리에 들어갈 데이터를 세팅한다.
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, bankUserName);
			
			// 데이터 셋팅이 완료되면 쿼리문을 실행한다.
			int cnt = pstmt.executeUpdate();
			
			// ====================================================================
			
			System.out.println("반환 값: " + cnt);
			if(cnt > 0) {
				System.out.println("INSERT 작업 성공!!");
			} else {
				System.out.println("INSERT 작업 실패!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try { rs.close(); } catch (SQLException e) { }
			}
			if(stmt != null) {
				try { stmt.close(); } catch (SQLException e) { }
			}
			if(pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) { }
			}
			if(pstmt2 != null) {
				try { pstmt2.close(); } catch (SQLException e) { }
			}
			if(conn != null) {
				try { conn.close(); } catch (SQLException e) { }
			}
		}
		
	}

}
