package basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {

	public static void main(String[] args) {
		// 문자 기반 Bufferd 스트림 예제
		try {
			// 이클립스 환경에서 실행되는 자바프로그램의 현재 위치는
			// 해당 '프로젝트 폴더'가 현재 위치가 된다.
			FileReader fr = new FileReader("./src/basic/ByteArrayIOTest01.java");
			
			BufferedReader br = new BufferedReader(fr);
			
			String temp = "";
			
			// readLine() ==> 한 라인을 읽어온다. 읽어올 자료가 없으면 null을 반환
			for(int i = 1; (temp = br.readLine()) != null; i++) {
				System.out.printf("%4d: %s\n", i, temp);
			}
			
			br.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
