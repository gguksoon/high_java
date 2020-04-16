package chat.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import chat.inf.IChatClient;
import chat.inf.IChatServer;

// IChatServer인터페이스를 구현한 서버용 객체
public class ChatServer extends UnicastRemoteObject implements IChatServer {
	// 접속한 클라이언트 객체가 저장될 List
	ArrayList<IChatClient> clientList;
	
	public ChatServer() throws RemoteException {
		clientList = new ArrayList<IChatClient>();
	}
	
	public static void main(String[] args) {
		try {
			IChatServer server = new ChatServer();
			
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.bind("chat", server);
			
			System.out.println("서버 준비 완료...");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

	// 접속한 클라이언트 객체를 List에 추가하는 메서드
	@Override
	public void setClient(IChatClient client) throws RemoteException {
		clientList.add(client);
	}

	// 리스트에 등록된 모든 클라이언트에게 메시지를 전달하는 메서드
	@Override
	public void setMessage(String msg) throws RemoteException {
		System.out.println("전송할 메시지 : " + msg);
		for(IChatClient client : clientList) {
			client.setMessage(msg);
		}
	}

}
