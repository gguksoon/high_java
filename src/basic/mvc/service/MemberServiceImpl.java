package basic.mvc.service;

import java.util.List;
import java.util.Map;

import basic.mvc.dao.IMemberDao;
import basic.mvc.dao.MemberDaoImpl;
import basic.mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	// 싱글톤==========================================
	private IMemberDao dao; // dao 불러오기
	private static MemberServiceImpl memService; // 자신의 인스턴스 저장

	public MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(memService == null)
			memService = new MemberServiceImpl();
		return memService;
	}
	// ==============================================
	
	

	@Override
	public int insertMember(MemberVO memVo) {
		return dao.insertMember(memVo);
	}

	@Override
	public int updateMember(Map<String, String> params) {
		return dao.updateMember(params);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}
	
	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}


}
