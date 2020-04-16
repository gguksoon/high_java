package zipSearch.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import zipSearch.service.IZipSearchService;
import zipSearch.service.ZipSearchServiceImpl;

public class ZipSearchServerMain {

	public static void main(String[] args) {
		try {
			IZipSearchService zipService = new ZipSearchServiceImpl();
			
			Registry reg = LocateRegistry.createRegistry(9988);
			
			// (클라이언트에서 찾을이름, zipService객체)
			reg.rebind("zipService", zipService);
			// 나머지 서비스들도 등록
			
			System.out.println("서버 준비 완료...");
					
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
