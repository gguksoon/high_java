package rmi.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import rmi.vo.TestVO;

// RMI용  Interface
// RMI용 인터페이스는 Remote를 상속해야 한다.

public interface RemoteInterface extends Remote {
	// 이곳에 선언되는 메서드들을 원격지에서 사용할 수 있다.
	// 그래서 이 메서드들을 선언할 때 RemoteException를 throws하도록 선언한다.
	
	// 선언한 메서드의 매개변수는 클라이언트에서 서버쪽으로 보내는 자료가 되고,
	// 메서드의 반환값은 서버에서 처리한 결과를 클라이언트쪽으로 보내는 자료가 된다.
	public int doRemotPrint(String str) throws RemoteException;
	
	public void doPrintList(ArrayList<String> list) throws RemoteException;
	
	public void doPrintVo(TestVO vo) throws RemoteException;
	
}
