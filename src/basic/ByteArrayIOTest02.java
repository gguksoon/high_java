package basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {

		byte[] inSrc = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4]; // 데이터 입출력에 사용할 배열
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
	
		try {
			// available() => 읽어올 수 있는 byte수를 반환한다.
			while(input.available() > 0) {
				
//				input.read(temp); // 입력
//				output.write(temp); // 출력
				// ==> 위 주석친 방법대로 입출력 시 입출력에 쓰레기값이 함께 출력된다.
				
				// read(byte배열); ==> 실제 읽어온 byte수를 반환한다.
				int len = input.read(temp);
				
				// temp배열의 내용 중에서 0번째부터 읽어온 len개수만큼 출력한다.
				output.write(temp, 0, len);
				
				System.out.println("temp => " + Arrays.toString(temp));
				
			}
			outSrc = output.toByteArray();
			System.out.println("inSrc => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc));
		} catch (IOException e) { }
		
		try {
			input.close();
			output.close();
		} catch (IOException e) { }
		
	}

}
