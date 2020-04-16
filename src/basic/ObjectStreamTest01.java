package basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectStreamTest01 {

	public static void main(String[] args) {
		// Member의 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전"); 
		Member mem2 = new Member("홍길서", 30, "서울"); 
		Member mem3 = new Member("홍길남", 40, "대구"); 
		Member mem4 = new Member("홍길북", 50, "부산");
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(
					new FileOutputStream("e:/D_Other/memObj.bin")
				)
			);
			
			// 쓰기 작업
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			
			// 스트림 닫기
			oos.close();
			System.out.println("저장완료");
			
			//------------------------------------------------------------
			
			// 파일에 저장한 객체 읽어오기
			
			// 객체 입력용 스트림 생성
			ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(
					new FileInputStream("e:/D_Other/memObj.bin")
				)
			);
			
			// 읽어온 객체를 저장할 변수
			Object obj = null;
			
			while( (obj = ois.readObject()) != null ) {
				// 읽어온 데이터를 원래의 객체
				Member mem = (Member)obj;
				System.out.print("  이름: " + mem.getName());
				System.out.print("  나이: " + mem.getAge());
				System.out.print("  주소: " + mem.getAddr());
				System.out.println("\n=============================");
			}
			
			// 스트림 닫기
			ois.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

class Member implements Serializable {
	private static final long serialVersionUID = 1432605378974552445L;
	
	// transient ==> 직렬화가 되지 않을 멤버변수에 지정한다.
	// 				  직렬화가 되지 않는 멤버변수는 기본값으로 초기화된다.
	//				 (참조변수: null, 숫자변수: 0)
	private String name;
	private transient int age; // 직렬화에서 뺌(나이가 기본값이 됨..)
	private transient String addr;
	
	public Member(String name, int age, String addr) {
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
	
}