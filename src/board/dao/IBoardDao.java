package board.dao;

import java.util.List;
import java.util.Map;

import board.vo.BoardVO;

public interface IBoardDao {

	/**
	 * BoardVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param boardVo - DB에 insert할 자료가 저장된 BoardVO객체
	 * @return - 성공 1, 실패 0
	 */
	public int insertBoard(BoardVO boardVo);
	
	
	/**
	 * 수정할 컬럼명과 수정할 데이터를 매개변수로 받아서 데이터를 수정하는 메서드
	 * @param params - 수정할 컬럼명과 데이터가 저장된 Map객체
	 * @return - 성공 1, 실패 0
	 */
	public int updateBoard(Map<String, String> params);
	
	
	/**
	 * boardNo를 매개변수로 받아서 해당 게시글을 삭제하는 메서드
	 * @param boardNo - 삭제할 게시글 번호
	 * @return - 성공 1, 실패 0
	 */
	public int deleteBoard(String boardNo);
	
	
	/**
	 * 전체 게시글 정보를 구성해서 반환하는 메서드
	 * @return - 전체 게시글 정보가 저장된 List객체
	 */
	public List<BoardVO> getAllBoard();
	
	
	/**
	 * boardNo을 매개변수로 받아서 해당 게시글의 객체를 반환하는 메서드
	 * @param boardNo - 검색할 회원 ID
	 * @return - 존재하면 boardNo, 없으면 null
	 */
	public BoardVO getBoard(int boardNo);


	/**
	 * 전체 게시글 중 board_title에 word가 속한 객체들을 반환하는 메서드
	 * @param word - 검색할 단어
	 * @return - title에 word가 속한 객체가 저장된 List
	 */
	public List<BoardVO> searchBoard(String word);
	
	
	/**
	 * 조회수를 증가시키는 메서드
	 * @param boardNo - 증가시킬 게시글의 글번호
	 */
	public void increaseCnt(String boardNo);
	

}
