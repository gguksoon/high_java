package basic.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import basic.util.BuildedSqlMapClient;
import basic.mvc.vo.MemberVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MemberDaoImpl implements IMemberDao {

	// 싱글톤==========================================
	private static MemberDaoImpl memDao;
	private SqlMapClient smc;
	
	private MemberDaoImpl() { 
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static MemberDaoImpl getInstance() {
		if(memDao == null)
			memDao = new MemberDaoImpl();
		return memDao;
	}
	// ==============================================
	
	@Override
	public int insertMember(MemberVO memVo) {
		
		int cnt = 0;
		
		try {
			Object obj = smc.insert("myMember.insertMember", memVo);
			if(obj == null) { // insert 성공 여부 확인
				cnt = 1;
			} else {
				cnt = 0;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int updateMember(Map<String, String> params) {
		
		int cnt = 0;
		
		try {
			cnt = smc.update("myMember.updateMember", params);		
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		try {
			cnt = smc.delete("myMember.deleteMember", memId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}
	
	@Override
	public List<MemberVO> getAllMember() {
		
		List<MemberVO> memList = null;
		
		try {
			memList = smc.queryForList("myMember.getMemberAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		
		int count = 0;
		
		try {
			MemberVO memVo = (MemberVO)smc.queryForObject("myMember.getMember", memId);
			
			if(memVo != null) {
				count = 1;
			} 
			
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} 
		
		return count;
	}



}
