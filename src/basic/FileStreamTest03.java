package basic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileStreamTest03 {

	public static void main(String[] args) {
		
		try {
			// 문자 기반의 파일 입력용 스트림 객체 생성
			FileReader fr = new FileReader("e:/D_Other/test.txt");
			
			int c;
			
			while((c = fr.read()) != -1) {
				System.out.print((char)c);
			}
		} catch (FileNotFoundException e) {	
		} catch (IOException e ) {	
		}
	}

}
