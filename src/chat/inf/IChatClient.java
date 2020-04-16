package chat.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;

// 클라이언트용 인터페이스 (서버쪽에서 사용할 인터페이스)
public interface IChatClient extends Remote {
	// 서버가 보내온 메시지를 출력하는 메서드
	public void setMessage(String msg) throws RemoteException;
	
}
