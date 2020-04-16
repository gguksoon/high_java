package mvc.dao;

import java.util.List;
import java.util.Map;

import mvc.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성하여
 * Service에 전달하는 DAO의 interface
 * 
 * @author 조민수
 *
 */
public interface IMemberDao {

	/**
	 * MemverVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memVO - DB에 insert할 자료가 저장된 MemberVO객체
	 * @return - 성공 1이상의 정수, 실패 0
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 수정할 컬럼명과 수정할 데이터를 매개변수로 받아서 데이터를 수정하는 메서드
	 * @param params - 수정할 컬럼명과 데이터가 저장된 Map객체
	 * @return - 성공 1, 실패 0
	 */
	public int updateMember(Map<String, String> params);
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원을 삭제하는 메서드
	 * @param memId - 삭제할 회원ID
	 * @return - 성공 1, 실패 0
	 */
	public int deleteMember(String memId);
		
	/**
	 * 전체 회원 정보를 구성해서 반환하는 메서드
	 * @return - 전체 회원 정보가 저장된 List객체
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원이 있는지 여부를 반환하는 메서드
	 * @param memId - 검색할 회원 ID
	 * @return - 존재하면 1, 없으면 0
	 */
	public int getMemberCount(String memId);
}
