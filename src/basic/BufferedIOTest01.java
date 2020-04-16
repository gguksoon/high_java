package basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 바이트 기반 Bufferd 스트림 예제
		// 입출력의 효율을 높이기 위해서 Buffered 스트림을 사용한다.
		
		try {
			// 기반 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("e:/D_Other/bufferedTest.txt");
			
			// 버퍼의 크기가 5인 Buffered스트림 객체 생성
			// 버퍼의 크기를 지정하지 않으면 기본적으로 8192byte(8kb)가 된다.
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);
			
			for(char i = '1'; i <= '9'; i++) { // 출력
				bout.write(i);
			}
			bout.flush(); 	// 버퍼에 남아있는 데이터를 모두 출력시켜서 버퍼를 비운다.
							// flush를 해주지 않으면 12345가 출력이 된다.
							// 		==> 6부터는 버퍼가 가득차지 않아서 일을 안함
							// flush를 해주면 123456789가 출력이 된다.
			
			System.out.println("작업끝...");
			
			bout.close(); 	// 보조스트림을 닫으면 기반 스트림은 자동으로 닫힌다.
			
		} catch (IOException e) { }
		
		
	}

}
