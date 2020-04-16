package basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileStreamTest04 {

	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일에 저장하기
		
		try {
			// 바이트기반의 스트림을 문자기반의 스트림으로 변환해 주는 객체
			InputStreamReader isr = new InputStreamReader(System.in);
			
			// 문자 기반의 파일 출력용 스트림 객체 생성
			FileWriter fw = new FileWriter("e:/D_Other/testChar.txt");
			
			int c;
			
			System.out.println("아무 내용이나 입력하세요.");
			System.out.println("입력의 종료는 Ctrl + Z입니다.");
			
			while((c = isr.read()) != -1) {
				fw.write(c); // 콘솔에서 입력받은 값을 파일에 출력한다.
			}
			fw.flush(); // 출력버퍼의 내용을 모두 출력하고 버퍼를 비운다.
			
			isr.close();
			fw.close();
		} catch (IOException e){
			
		}
		
	}

}
