package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
	회원정보를 관리하는 프로그램을 작성하시오.
	(오라클의 'MYMEMBER'테이블을 이용한다.)
	
	아래 메뉴의 기능을 모두 구현하시오.
	(CRUD 기능 구현하기)
	
	메뉴예시)
	----------------
	1. 자료 추가(C)		-- INSERT
	2. 자료 삭제(D)		-- DELETE
	3. 자료 수정(U)		-- UPDATE
	4. 전체 자료 출력(R)	-- SELECT
	0. 종료
	----------------
	
	- 자료 삭제는 회원ID를 입력 받아 삭제한다.

*/
public class JdbcTest05 {

	private Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		new JdbcTest05().menu();
	}
	
	void menu() {
		while(true) {
			System.out.println("[MYMEMBER 관리]");
			System.out.println("================");
			System.out.println("1. 자료 추가");
			System.out.println("2. 자료 삭제");
			System.out.println("3. 자료 수정");
			System.out.println("4. 전체 출력");
			System.out.println("0. 종료");
			System.out.println("================");
			System.out.print(" > ");
			String menu = sc.nextLine();
			switch(menu) {
				case "1":
					memInsert();
					break;
				case "2":
					memDelete();
					break;
				case "3":
					memUpdate();
					break;
				case "4":
					memPrintAll();
					break;
				case "0":
					return;
			}
		}
	}

	private void memInsert() {
		
		// 입력받은 값들이 저장될 변수
		String id = "";
		String name = "";
		String tel = "";
		String addr = "";
		
		System.out.println("메뉴로 돌아가려면 '-1'을 입력하세요.");
		// id
		int count = 0;
		do {
			System.out.print("ID: ");
			id = sc.nextLine();
			if(id.equals("-1")) return;
			count = idCheck(id);
			if(count == 1) {
				System.out.println("\"" + id + "\"는 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while(count == 1);
		
		// name
		System.out.print("NAME: ");
		name = sc.nextLine();
		if(name.equals("-1")) return;
		
		// tel
		System.out.print("TEL: ");
		tel = sc.nextLine();
		if(tel.equals("-1")) return;
		
		// addr
		System.out.print("ADDR: ");
		addr = sc.nextLine();
		if(addr.equals("-1")) return;
		
		// -----------------------------------------------------------------
		
		Connection conn = util.DBUtil.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO MYMEMBER " + 
					"VALUES(?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("추가에 성공했습니다.");
			} else {
				System.out.println("추가에 실패했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try { pstmt.close(); } catch(SQLException e) { }
			}
			if(conn != null) {
				try { conn.close(); } catch(SQLException e) { }
			}
		}
		
		
	}

	private void memDelete() {
		int count = 1;
		String id = "";
		do {
			memPrintAll();
			System.out.println("삭제할 아이디를 입력하세요.(메뉴로 돌아가려면 '-1'을 입력하세요.)");
			System.out.print("ID: ");
			id = sc.nextLine();
			if(id.equals("-1")) return;
			count = idCheck(id);
			if(count == 0) {
				System.out.println("\"" + id + "\"는 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while(count == 0);
		
		Connection conn = util.DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("삭제에 성공했습니다.");
			} else {
				System.out.println("삭제에 실패했습니다.");
			}
			
		} catch (SQLException e) {
			
		}
	}

	private void memUpdate() {
		int count = 1;
		String id = "";
		do {
			memPrintAll();
			System.out.println("정보를 수정할 아이디를 입력하세요.(메뉴로 돌아가려면 '-1'을 입력하세요.)");
			System.out.print("ID: ");
			id = sc.nextLine();
			if(id.equals("-1")) return;
			count = idCheck(id);
			if(count == 0) {
				System.out.println("\"" + id + "\"는 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while(count == 0);
		
		memPrint(id);
		
		System.out.println(" 수정할 정보를 입력하세요. (메뉴로 돌아가려면 \"-1\"를 입력하세요.)");
		System.out.println("1. 이름");
		System.out.println("2. 전화번호");
		System.out.println("3. 주소");
		String num = sc.nextLine();
		
		Connection conn = util.DBUtil.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String name = "";
			String tel = "";
			String addr = "";
			String sql = "";
			switch(num) {
				case "1": // name
					System.out.println("메뉴로 돌아가려면 \"-1\"를 입력하세요.");
					System.out.print("변경할 이름: ");
					name = sc.nextLine();
					if(name.equals("-1")) return;
					sql = "UPDATE MYMEMBER SET MEM_NAME = ? WHERE MEM_ID = ?";
					break;
				case "2": // tel
					System.out.println("메뉴로 돌아가려면 \"-1\"를 입력하세요.");
					System.out.print("변경할 전화번호: ");
					tel = sc.nextLine();
					if(tel.equals("-1")) return;
					sql = "UPDATE MYMEMBER SET MEM_TEL = ? WHERE MEM_ID = ?";
					break;
				case "3": // addr
					System.out.println("메뉴로 돌아가려면 \"-1\"를 입력하세요.");
					System.out.print("변경할 주소: ");
					addr = sc.nextLine();
					if(addr.equals("-1")) return;
					sql = "UPDATE MYMEMBER SET MEM_ADDR = ? WHERE MEM_ID = ?";
					break;
			}
			pstmt = conn.prepareStatement(sql);
			if(num.equals("1")) pstmt.setString(1, name);
			else if(num.equals("2")) pstmt.setString(1, tel);
			else if(num.equals("3")) pstmt.setString(1, addr);
			pstmt.setString(2, id);

			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("변경에 성공했습니다.");
				memPrint(id);
			} else {
				System.out.println("변경에 실패했습니다.");
			}
			
		} catch (SQLException e) {
			System.out.println("sql에러");
		} finally {
			if(pstmt != null) {
				try { pstmt.close(); } catch(SQLException e) { }
			}
			if(conn != null) {
				try { conn.close(); } catch(SQLException e) { }
			}
		}
		
		
		
		
		
		
	}
	
	private void memPrint(String id) {
		Connection conn = util.DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			String sql = "SELECT * FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			System.out.println("==========================================================================");
			System.out.printf(" %-15s %-15s %-20s %-15s\n",
					"MEM_ID", "MEM_NAME", "MEM_TEL", "MEM_ADDR");
			System.out.println("==========================================================================");
			if(rs.next()) {
				System.out.printf(" %-15s %-15s %-20s %-15s\n", 
						rs.getString("MEM_ID"), rs.getString("MEM_NAME"),
						rs.getString("MEM_TEL"), rs.getString("MEM_ADDR"));
			}
			System.out.println("==========================================================================");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) { 
				try { rs.close(); } catch(SQLException e) { }
			}
			if(pstmt != null) {
				try { pstmt.close(); } catch(SQLException e) { }
			}
			if(conn != null) {
				try { conn.close(); } catch(SQLException e) { }
			}
		}
	}

	private void memPrintAll() {
//		Connection conn = util.DBUtil.getConnection();
//		Connection conn = util.DBUtil2.getConnection();
		Connection conn = util.DBUtil3.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
	
		try {
			String sql = "SELECT * FROM MYMEMBER";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("==========================================================================");
			System.out.printf(" %-15s %-15s %-20s %-15s\n",
					"MEM_ID", "MEM_NAME", "MEM_TEL", "MEM_ADDR");
			System.out.println("==========================================================================");
			while(rs.next()) {
				System.out.printf(" %-15s %-15s %-20s %-15s\n", 
						rs.getString("MEM_ID"), rs.getString("MEM_NAME"),
						rs.getString("MEM_TEL"), rs.getString("MEM_ADDR"));
			}
			System.out.println("==========================================================================");
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	private int idCheck(String id) {
		Connection conn = util.DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT COUNT(*) FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			System.out.println("SQL에러 발생");
			return 0;
		} finally {
			if(rs != null) { 
				try { rs.close(); } catch(SQLException e) { }
			}
			if(pstmt != null) {
				try { pstmt.close(); } catch(SQLException e) { }
			}
			if(conn != null) {
				try { conn.close(); } catch(SQLException e) { }
			}
		}
	}
	
}
