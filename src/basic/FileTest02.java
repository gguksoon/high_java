package basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * 	특정한 디렉토리를 지정해주면 해당 폴더에 속해 있는 모든 파일과 폴더 정보를 출력해보자.
 * 	ex) DOS의 dir명령과 같은 기능
 *  	2019-04-15  오전 09:22    <DIR>          .
 * 		2019-04-15  오전 09:22    <DIR>          ..
 * 		2016-11-09  오후 03:59               312 API들.txt
 * 		2017-01-09  오전 10:37            21,504 자바관련설치.hwp
 *      		        2개 파일              21,816 바이트
 *               		2개 디렉터리  721,537,327,104 바이트 남음
 */
public class FileTest02 {

	public static void main(String[] args) {
		File testFile = new File("E:\\B_Util\\4.Oracle\\app\\oracle");
		new FileTest02().displayFileList(testFile);
	}
	
	// 지정된 디렉토리에 포함된 파일과 디렉토리 목록을 보여주는 메서드
	void displayFileList(File dir) {
		if(dir.isFile()){
			System.out.println("해당 정보는 디렉토리가 아닙니다.");
			System.out.println("작업을 종료합니다.");
			return;
		}
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리 내용");
		File[] files = dir.listFiles(); // 디렉토리 안의 파일 목록 읽어오기(File객체 형태로)
		
		// 목록 중 디렉토리가 들어있는 배열의 첨자가 저장될 List 선언
		ArrayList<Integer> subList = new ArrayList<>();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		
		for(int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();
			String attr = ""; // 파일의 속성(읽기, 쓰기, 히든, 디렉토리 구분)
			String size = ""; // 파일의 크기
			
			if(files[i].isDirectory()) { // 디렉토리일 경우
				attr = "<DIR>";
				subList.add(i); // List에 디렉토리가 들어있는 첨자번호 추가
			} else { // 파일일 경우
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : "";
				attr += files[i].canWrite() ? "W" : "";
				attr += files[i].isHidden() ? "H" : "";
			}
			String strDate = df.format(new Date(files[i].lastModified())); 
			System.out.printf("%s %5s %12s %s\n", strDate, attr, size, fileName);
		}
		
		// 해당 폴더안에 있는 폴더의 개수 구하기 ==> List의 개수와 같다.
		int dirNum = subList.size();
		
		// 해당 폴더안에 있는 파일의 개수 구하기 ==> 전체 목록 개수 - 폴더 개수
		int fileNum = files.length - dirNum;
		
		System.out.println("\t\t" + fileNum + "개의 파일, " + dirNum + "개의 디렉토리");
		System.out.println("------------------------------------------------------------------");
		
		// 하위 디렉토리의 내용을 출력하기 위해 재귀호출을 이용한다.
		for(int i = 0; i < subList.size(); i++){
			displayFileList(files[subList.get(i)]);
		}
		

	}

}
