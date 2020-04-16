package basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileStreamTest05 {
	/*
	 * 한글이 저장된 파일 읽어오기.
	 * (한글의 인코딩 방식을 지정해서 읽어온다.)
	 */
	public static void main(String[] args) {
		try {
			// InputStreamReader
			//	==> byte기반의 스트림을 문자기반의 스트림으로 변환한다.
			//	==> 객체를 생성할 때 파일의 인코딩 방식을 지정할 수 있다.
			//		- MS949: 윈도우의 기본 한글 인코딩 방식(ANSI)
			//		- UTF-8: 유니코드 UTF-8 인코딩 방식
			//		- US-ASCII: 영문 전용 인코딩 방식
			
			// UTF-8파일
			FileInputStream fin = new FileInputStream("e:/D_Other/test_utf8.txt");
			// ANSI파일
			FileInputStream fin2 = new FileInputStream("e:/D_Other/test_ansi.txt");
			

			InputStreamReader isr = new InputStreamReader(fin);
			InputStreamReader isr2 = new InputStreamReader(fin2);
			InputStreamReader isr3 = new InputStreamReader(fin2, "MS949");
			
			int c;
			
			while( (c = isr.read()) != -1 ) { 
				System.out.print((char)c);
			}
			
//			while( (c = isr2.read()) != -1 ) {
//				System.out.print((char)c);
//			}
			
			while( (c = isr3.read()) != -1 ) {
				System.out.print((char)c);
			}
			
			isr.close();
			isr2.close();
			isr3.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
