package basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileStreamTest01 {

	public static void main(String[] args) {
		// 파일의 내용을 읽어서 콘솔에 출력하기
		try {
			// 파일과 연결된 바이트 스트림객체 생성
			FileInputStream fis = new FileInputStream("e:/D_other/test.txt");

			int c; // 읽어온 데이터를 저장할 변수
			
			while((c = fis.read()) != -1){
				// 읽어온 데이터 콘솔에 출력하기
				System.out.print((char)c);
			}
			/*
			 * byte기반은 한글을 출력할 때 한글을 1byte씩 쪼개서 출력하므로
			 * 이상한 값이 나온다 (안녕하세요 => ¾È³çÇÏ¼¼¿ä)
			 */
			fis.close(); // 작업 완료 후 스트림을 닫는다.
			
		} catch (FileNotFoundException e){
			System.out.println("해당 파일이 없습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("입출력 오류입니다.");
		}
	}

}
