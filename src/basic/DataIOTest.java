package basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {
		try {
			// DataOutputStream은 출력용 데이터를 자료형에 맞게 출력한다.
			DataOutputStream dos = 
					new DataOutputStream( new FileOutputStream("e:/D_Other/dataTest.dat"));
			
			dos.writeInt(200);			// 정수형으로 데이터 출력
			dos.writeFloat(132.456f); 	// 실수형으로 데이터 출력
			dos.writeBoolean(false);	// 논리형으로 데이터 출력
			
			System.out.println("출력 끝...");
			dos.close();
			
			//==================================================================
			
			// 출력한 자료 읽어오기
			DataInputStream dis =
					new DataInputStream(new FileInputStream("e:/D_Other/dataTest.dat"));
			
			System.out.println("정수형 자료: " + dis.readInt());
			System.out.println("실수형 자료: " + dis.readFloat());
			System.out.println("논리형 자료: " + dis.readBoolean());
			
			dis.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
