package zipSearch.dao;

import java.util.List;

import zipSearch.vo.ZipVO;

public interface IZipSearchDao {

	/**
	 * 동이름으로 우편번호를 검색하는 메서드
	 * @param dong 검색할 동이름
	 * @return 검색된 결과가 저장된 List
	 */
	public List<ZipVO> getZipSearchDong(String dong);
	
	/**
	 * 우편번호로 우편번호를 검색하는 메서드
	 * @param code 검색할 우편번호
	 * @return 검색된 결과가 저장된 List
	 */
	public List<ZipVO> getZipSearchCode(String code);
	
}
