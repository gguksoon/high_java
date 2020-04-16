package board.service;

import java.util.List;
import java.util.Map;

import board.dao.BoardDaoImpl;
import board.dao.IBoardDao;
import board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {

	// 싱글톤==========================================
	private IBoardDao dao; // dao 불러오기
	private static BoardServiceImpl boardService; // 자신의 인스턴스 저장

	public BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		if(boardService == null)
			boardService = new BoardServiceImpl();
		return boardService;
	}
	// ==============================================
	
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int updateBoard(Map<String, String> params) {
		return dao.updateBoard(params);
	}

	@Override
	public int deleteBoard(String boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public List<BoardVO> getAllBoard() {
		return dao.getAllBoard();
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		return dao.getBoard(boardNo);
	}

	@Override
	public List<BoardVO> searchBoard(String word) {
		return dao.searchBoard(word);
	}

	@Override
	public void increaseCnt(String boardNo) {
		dao.increaseCnt(boardNo);
	}

	

}
