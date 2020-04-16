package basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {

	public static void main(String[] args) {

		byte[] inSrc = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		/*ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();*/
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int data; // 읽어온 자료를 저장할 변수
		
		// read()메서드는 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while((data = input.read()) != -1) {
			// 읽어온 자료를 출력하기
			output.write(data);
		}
		
		// 출력된 스트림값들을 배열로 변환하기
		outSrc = output.toByteArray();
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
		try {
			input.close();
			output.close();
		} catch (IOException e) { }
	}

}
