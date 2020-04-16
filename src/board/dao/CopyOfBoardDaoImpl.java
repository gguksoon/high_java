/*package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.vo.BoardVO;

public class CopyOfBoardDaoImpl implements IBoardDao {

	// 싱글톤==========================================
	private static CopyOfBoardDaoImpl boardDao;
	
	private CopyOfBoardDaoImpl() { }
	
	public static CopyOfBoardDaoImpl getInstance() {
		if(boardDao == null)
			boardDao = new CopyOfBoardDaoImpl();
		return boardDao;
	}
	// ==============================================
	
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "INSERT INTO JDBC_BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, 0, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e2) { }
			if(conn != null) try { conn.close(); } catch(SQLException e2) { }
		}
		return cnt;
	}

	
	// fieldName: 수정할 속성
	// editData: 수정할 데이터
	// boardNo: 게시글 번호
	@Override
	public int updateBoard(Map<String, String> params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "UPDATE JDBC_BOARD SET "
					+ params.get("fieldName") + " = ? WHERE BOARD_NO = ?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, params.get("editData"));
			pstmt.setString(2, params.get("boardNo"));
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e2) { }
			if(conn != null) try { conn.close(); } catch(SQLException e2) { }
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(String boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "DELETE FROM JDBC_BOARD WHERE BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e2) { }
			if(conn != null) try { conn.close(); } catch(SQLException e2) { }
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
	
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM JDBC_BOARD";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO boardVo = new BoardVO();
				
				boardVo.setBoard_no(rs.getInt("BOARD_NO"));
				boardVo.setBoard_title(rs.getString("BOARD_TITLE"));
				boardVo.setBoard_writer(rs.getString("BOARD_WRITER"));
				boardVo.setBoard_date(rs.getString("BOARD_DATE"));
				boardVo.setBoard_cnt(rs.getInt("BOARD_CNT"));
				boardVo.setBoard_content(rs.getString("BOARD_CONTENT"));
				
				boardList.add(boardVo);
			}
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch(SQLException e) { }
			if(stmt != null) try { stmt.close(); } catch(SQLException e) { }
			if(conn != null) try { conn.close(); } catch(SQLException e) { }
			
		}
		return boardList;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardVO boardVo = null; // 게시글 1개를 저장할 객체
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM JDBC_BOARD WHERE BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardVo = new BoardVO();
				
				boardVo.setBoard_no(rs.getInt("BOARD_NO"));
				boardVo.setBoard_title(rs.getString("BOARD_TITLE"));
				boardVo.setBoard_writer(rs.getString("BOARD_WRITER"));
				boardVo.setBoard_date(rs.getString("BOARD_DATE"));
				boardVo.setBoard_cnt(rs.getInt("BOARD_CNT"));
				boardVo.setBoard_content(rs.getString("BOARD_CONTENT"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardVo;
	}

	@Override
	public List<BoardVO> searchBoard(String word) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM JDBC_BOARD WHERE BOARD_TITLE LIKE ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + word + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO boardVo = new BoardVO();
				
				boardVo.setBoard_no(rs.getInt("BOARD_NO"));
				boardVo.setBoard_title(rs.getString("BOARD_TITLE"));
				boardVo.setBoard_writer(rs.getString("BOARD_WRITER"));
				boardVo.setBoard_date(rs.getString("BOARD_DATE"));
				boardVo.setBoard_cnt(rs.getInt("BOARD_CNT"));
				boardVo.setBoard_content(rs.getString("BOARD_CONTENT"));
				
				boardList.add(boardVo);
			}
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch(SQLException e) { }
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) { }
			if(conn != null) try { conn.close(); } catch(SQLException e) { }
			
		}
		return boardList;
	}

	@Override
	public void increaseCnt(String boardNo) {
		BoardVO board = getBoard(Integer.parseInt(boardNo));
		Map<String, String> params = new HashMap<>();
		params.put("boardNo", boardNo);
		params.put("fieldName", "BOARD_CNT");
		params.put("editData", "" + (board.getBoard_cnt() + 1));
		
		updateBoard(params);
	}

}
*/