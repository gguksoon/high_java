package basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamTest02 {

	public static void main(String[] args) {

		try {
			// 파일 출력용 스트림 객체 생성
			// 방법1 ==> 처리할 파일을 문자열로 지정하는 방법
//			FileOutputStream fos = new FileOutputStream("e:/D_Other/out.txt");
			
			// 방법2 ==> 처리할 파일을 File객체로 지정하는 방법
			File file = new File("e:/D_Other/out.txt");
			FileOutputStream fos = new FileOutputStream(file);
			
			for(char i = 'a'; i <= 'z'; i++) {
				fos.write(i);
			}
			System.out.println("출력 끝...");
			fos.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("파일없음");
		} catch (IOException e) {
			System.out.println("입출력에러");
		}
	}

}
