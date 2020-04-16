package chat.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import chat.inf.IChatClient;
import chat.inf.IChatServer;

// 서버쪽에서 사용할 객체
// IChatClient 인터페이스를 구현한 클라이언트용 객체
public class ChatClient extends UnicastRemoteObject implements IChatClient {

	public ChatClient() throws RemoteException { }
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			IChatClient client = new ChatClient();
			
			Registry reg = LocateRegistry.getRegistry("localhost", 1099);
			IChatServer server = (IChatServer) reg.lookup("chat");
			
			// 서버에 접속되면 현재의 클라이언트 객체를 서버의 클라이언트가 저장되는 List에 추가한다.
			server.setClient(client);
			
			while(true) {
				System.out.println("메시지 입력: ");
				String msg = sc.nextLine();
				server.setMessage(msg);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	// 서버가 보내온 메시지를 출력하는 메서드
	@Override
	public void setMessage(String msg) throws RemoteException {
		System.out.println(msg);
	}
	
	
}
