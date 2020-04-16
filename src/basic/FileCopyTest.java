package basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/*
 * 	"e:/D_Other/dog.png"파일을
 * 	"e:/D_Other/연습용"폴더에 'dog_복사본.png'라는 이름으로
 * 	복사하는 프로그램 작성하기.
 */
public class FileCopyTest {

	public static void main(String[] args) {
		
		try {
			// 복사할 파일 스트림 객체 생성 (입력용)
			FileInputStream input = new FileInputStream("e:/D_Other/dog.png");
			BufferedInputStream bis = new BufferedInputStream(input);
			
			// 복사될 파일 스트림 객체 생성 (출력용)
			FileOutputStream output = new FileOutputStream("e:/D_Other/연습용/dog_복사본.png");
			BufferedOutputStream bout = new BufferedOutputStream(output);
			
			int temp;
			
			// 파일 스트림으로 ==> 0.828초
//			long start = System.currentTimeMillis();
//			while( ( temp = input.read() ) != -1) {
//				output.write(temp);
//			}
//			long end = System.currentTimeMillis();
//			System.out.println("실행 시간: " + (end - start));
//			output.flush();
//			input.close();
//			output.close();
			
			// 버퍼 스트림으로 ==> 0.016초
			long start2 = System.currentTimeMillis();
			while( ( temp = bis.read() ) != -1) {
				bout.write(temp);
			}
			long end2 = System.currentTimeMillis();
			System.out.println("실행 시간: " + (end2 - start2));

			bout.flush();
			bis.close();
			bout.close();
			
			System.out.println("복사완료");
		
		} catch (FileNotFoundException e) {
			System.out.println("파일없음");
		} catch (IOException e) {
			System.out.println("입출력에러");
		} 
	}

}
