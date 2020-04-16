package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import util.DBUtil;

/*
 *  문제: LPROD테이블에 새로운 데이터 추가하기
 *  
 *  - 조건1) lprod_gu와 lprod_nm은 직접 입력 받아 처리하고,
 *  		lprod_id는 현재 등록된 lprod_id중 제일 큰 값보다 1 증가된 값으로 한다.
 *  - 조건2) lprod_gu가 중복되는지 검사 후 처리한다.
 */
public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver"); // DBUtil을 만듦으로써 필요없어짐
//			
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe",
//					"JMS", "java");
			conn = DBUtil.getConnection();
			
			// ====================================================================
			
			System.out.println("[LPROD 테이블에 새로운 데이터 추가하기]");
			
			// lprod_id
			String sqlId = "SELECT MAX(LPROD_ID) FROM LPROD";
			pstmt = conn.prepareStatement(sqlId);
			rs = pstmt.executeQuery();
			int maxId = 0;
			if(rs.next()){
				maxId = rs.getInt(1);
			}
			int id = maxId + 1; // 저장될 id
			
			// --------------------------------------------------------------------
			
			// lprod_gu
			int count = 0;
			String gu = ""; // 저장될 gu
			do {
				System.out.print("LPROD_GU(상품코드)를 입력하세요.: ");
				gu = sc.nextLine().toUpperCase();
				
				String sqlGu = "SELECT COUNT(*) FROM LPROD WHERE LPROD_GU = ?";
				pstmt = conn.prepareStatement(sqlGu);
				pstmt.setString(1, gu);
				rs = pstmt.executeQuery();
				System.out.println("count: " + count);
				if(rs.next()) {
					count = rs.getInt(1);
				}
				if(count == 1) {
					System.out.println("상품코드 \"" + gu + "\"는 이미 등록된 코드입니다.");
					System.out.println("다른 상품코드를 입력하세요.");
				}
			} while(count == 1);
			
			// --------------------------------------------------------------------
			
			// lprod_nm
			System.out.print("LPROD_NM(상품명)을 입력하세요.: ");
			String nm = sc.nextLine();
			
			// ====================================================================
			
			// 자료 입력하기
			String sql = "INSERT INTO LPROD VALUES(?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			// ====================================================================
			
			System.out.println("반환 값: " + cnt);
			if(cnt > 0) {
				System.out.println("INSERT 성공");
			} else {
				System.out.println("INSERT 실패");
			}
			
			
//		} catch (ClassNotFoundException e) { // DBUtil을 만듦으로써 필요없어짐
//			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try { rs.close(); } catch (SQLException e) { }
			}
			if(pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) { }
			}
			if(conn != null) {
				try { conn.close(); } catch (SQLException e) { }
			}
		}

	}

}
