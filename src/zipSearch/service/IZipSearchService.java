package zipSearch.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import zipSearch.vo.ZipVO;

// RMI에서 사용할 Interface ==> dao와 같게 만들고 메서드들의 이름 뒤에 throws RemoteException만 붙인다.
public interface IZipSearchService extends Remote{
	
	/**
	 * 동이름으로 우편번호를 검색하는 메서드
	 * @param dong 검색할 동이름
	 * @return 검색된 결과가 저장된 List
	 */
	public List<ZipVO> getZipSearchDong(String dong) throws RemoteException;
	
	/**
	 * 우편번호로 우편번호를 검색하는 메서드
	 * @param code 검색할 우편번호
	 * @return 검색된 결과가 저장된 List
	 */
	public List<ZipVO> getZipSearchCode(String code) throws RemoteException;
}
