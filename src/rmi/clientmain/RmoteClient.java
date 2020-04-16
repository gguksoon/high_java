package rmi.clientmain;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import rmi.inf.RemoteInterface;
import rmi.vo.TestVO;

// 클라이언트 쪽의 프로젝트에도 서버의 '인터페이스'와 'VO객체'가 있는
// 패키지 구조가 같게 만들어져 있어야 한다.
// 이 패키지 안에는 해당 '인터페이스'와 'VO클래스'가 있어야 한다.
public class RmoteClient {

	public static void main(String[] args) {
		try {
			// 서버에서 등록한 객체를 찾기 위해서 Registry객체를 생성한 후
			// 이 Registry객체를 이용해서 사용할 객체를 불러온다.
			// 형식) LocateRegistry.getRegistry("서버의IP주소", 포트번호);
			Registry reg = LocateRegistry.getRegistry("localhost", 8888);
			
			// 서버에서 등록한 'Alias명'으로 사용할 객체를 불러온다.
			// 형식) Registry객체변수.lookup("서버에등록된Alias명");
			RemoteInterface inf = (RemoteInterface) reg.lookup("server");
			
			// 이제부터는 불러온 객체의 메서드를 호출해서 사용할 수 있다.
			int a = inf.doRemotPrint("안녕하세요...");
			System.out.println("반환값: " + a);
			System.out.println("----------------------");
			
			ArrayList<String> nameList = new ArrayList<String>();
			nameList.add("홍길동");
			nameList.add("일지매");
			nameList.add("이순신");
			nameList.add("성춘향");
			inf.doPrintList(nameList);
			System.out.println("----------------------");
			
			TestVO tVo = new TestVO();
			tVo.setTestId("dditMan");
			tVo.setTestNum(123);
			inf.doPrintVo(tVo);
			System.out.println("----------------------");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
