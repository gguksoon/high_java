package rmi.servermain;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import rmi.inf.RemoteInterface;
import rmi.vo.TestVO;

// RMI용 인터페이스를 구현하는 클래스
// 		==> UnicastRemoteObject를 상속하고 RMI용 인터페이스를 구현한다.
public class RemoteServer extends UnicastRemoteObject implements RemoteInterface{

	// 기본 생성자도 RemoteException을 throws해야 한다.
	public RemoteServer() throws RemoteException { } 
	
	public static void main(String[] args) {
		try {
			// RMI용 인터페이스를 구현한 클래스의 인스턴스 생성
			RemoteInterface inf = new RemoteServer();
			
			// 구현한 객체를 클라이언트에서 찾을 수 있도록 관리하는 Registry객체 생성
			// RMI용 기본 포트번호: 1099 
			Registry reg = LocateRegistry.createRegistry(8888);
			
			// Registry객체에 서버에서 제공하는 객체를 등록한다.
			// 형식) Registry객체변수.rebind("객체의 Alias", 등록할 객체변수);
			reg.rebind("server", inf);
			
			System.out.println("서버가 준비되었습니다.");
			System.out.println();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	// RMI용 인터페이스에서 선언한 메서드들을 구현한다.
	@Override
	public int doRemotPrint(String str) throws RemoteException {
		System.out.println("클라이언트에서 보내온 내용 : " + str);
		System.out.println("doRemotePrint()메서드 작업 끝...");
		System.out.println();
		return 200; // 클라이언트에게 보내는 값
	}

	@Override
	public void doPrintList(ArrayList<String> list) throws RemoteException {
		for(int i = 0; i < list.size(); i ++) {
			System.out.println(list.get(i));
		}
		System.out.println("doPrintList()메서드 작업 끝...");
		System.out.println();
	}

	@Override
	public void doPrintVo(TestVO vo) throws RemoteException {
		System.out.println("ID : " + vo.getTestId());
		System.out.println("NUM : " + vo.getTestNum());
		System.out.println("doPrintVo()메서드 작업 끝...");
		System.out.println();
	}

}
