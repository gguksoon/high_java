package zipSearch.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import zipSearch.dao.IZipSearchDao;
import zipSearch.dao.ZipSearchDaoImpl;
import zipSearch.vo.ZipVO;

// RMI에서 공유될 class ==> extends UnicastRemoteObject가 추가되어야 한다.
// RMI에서는 굳이 싱글톤을 만들지 않아도 된다..
public class ZipSearchServiceImpl extends UnicastRemoteObject implements IZipSearchService {
	private IZipSearchDao dao;
	
	// 생성자
	public ZipSearchServiceImpl() throws RemoteException {
		dao = ZipSearchDaoImpl.getInstance();
	}
	
	@Override
	public List<ZipVO> getZipSearchDong(String dong) throws RemoteException {
		return dao.getZipSearchDong(dong);
	}

	@Override
	public List<ZipVO> getZipSearchCode(String code) throws RemoteException {
		return dao.getZipSearchCode(code);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
