package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {

	// 싱글톤==========================================
	private static BoardDaoImpl boardDao;
	private SqlMapClient smc;
	
	private BoardDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static BoardDaoImpl getInstance() {
		if(boardDao == null)
			boardDao = new BoardDaoImpl();
		return boardDao;
	}
	// ==============================================
	
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		
		int cnt = 0;
		
		try {
			Object obj = smc.insert("jdbcBoard.insertBoard", boardVo);
			if(obj == null) {
				cnt = 1;
			} 
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	
	// fieldName: 수정할 속성
	// editData: 수정할 데이터
	// boardNo: 게시글 번호
	@Override
	public int updateBoard(Map<String, String> params) {
		int cnt = 0;
		
		try {
			cnt = smc.update("jdbcBoard.updateBoard", params);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int deleteBoard(String boardNo) {
		
		int cnt = 0;
		
		try {
			cnt = smc.delete("jdbcBoard.deleteBoard", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		List<BoardVO> boardList = null;
		
		try {
			boardList = smc.queryForList("jdbcBoard.getBoardAll");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return boardList;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		BoardVO boardVo = null; // 게시글 1개를 저장할 객체
		
		try {
			boardVo = (BoardVO)smc.queryForObject("jdbcBoard.getBoard", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardVo;
	}

	@Override
	public List<BoardVO> searchBoard(String word) {
		List<BoardVO> boardList = null;
		
		try {
			boardList = smc.queryForList("jdbcBoard.searchBoard", word);
		} catch (SQLException e) {
			e.printStackTrace();
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
