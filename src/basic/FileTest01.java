package basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		
		// <<File객체 만들기 연습>>
		
		/**
		1) new File(String 파일 또는 경로)
		   ==> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의
			      구분 문자는 '\'를 사용하거나 '/'를 사용할 수 있다.
		*/
		System.out.println("<file1>");
//		File file1 = new File("E:/D_Other/file.txt");
		File file1 = new File("E:\\D_Other\\file.txt");
		System.out.println("파일명: " + file1.getName());
		System.out.println("파일 여부: " + file1.isFile());
		System.out.println("디렉토리 여부: " + file1.isDirectory());
		
		System.out.println("\n===================================\n");
		
		System.out.println("<file2>");
		File file2 = new File("e:/D_Other");
		System.out.println("파일명: " + file2.getName());
		System.out.println("파일 여부: " + file2.isFile());
		System.out.println("디렉토리 여부: " + file2.isDirectory());
		
		System.out.println("\n===================================\n");
		
		/**
		2) new File(File parent, String child);
		   ==> 'parent'디렉토리 안에 있는 'child'파일을 갖는다.
		*/
		System.out.println("<file3>");
		File file3 = new File(file2, "file.txt");
		System.out.println("파일명: " + file3.getName());
		System.out.println("파일 여부: " + file3.isFile());
		System.out.println("디렉토리 여부: " + file3.isDirectory());
		
		System.out.println("\n===================================\n");
		
		/**
		3) new File(String parent, String child);
		   ==> 'parent'디렉토리 안에 있는 'child'파일을 갖는다.
		*/
		System.out.println("<file4>");
		File file4 = new File("e:/D_Other", "file.txt");
		System.out.println("파일명: " + file4.getName());
		System.out.println("파일 여부: " + file4.isFile());
		System.out.println("디렉토리 여부: " + file4.isDirectory());
		
		System.out.println("\n===================================\n");
		
		
		
		// <<디렉토리(폴더) 만들기>>
		/**
		1) mkdir()
		   ==> File객체의 전체 경로 중 마지막 위치에 지정한 디렉토리를 만든다.
		   ==> 반환값: 만들기 성공(true), 실패(false)
		   ==> 중간의 경로가 모두 만들어져 있어야 마지막 위치의 디렉토리를 만들 수 있다.
		2) mkdirs()
		   ==> 중간의 경로가 만들어져 있지 않으면 중간의 경로에 해당하는 디렉토리들을 만든 후
		              마지막 위치의 디렉토리를 만든다.
		*/
		File file5 = new File("e:/D_Other/연습용");
		
		if(!file5.exists()){ // 폴더의 경로가 존재하는지 확인
			if(file5.mkdir()) { // 만들기 성공인지 검사
				System.out.println(file5.getName() + " 폴더 만들기 성공");
			} else {
				System.out.println(file5.getName() + " 폴더 만들기 실패"); // 이미 있으면 실패됨
			}
		} else {
			System.out.println("\"" + file5.getName() + "\" 폴더가 이미 존재합니다.");
		}
		
		System.out.println("\n===================================\n");
		
		File file6 = new File("e:/D_Other/test/java/src"); 
//		if(file6.mkdir()) {
		if(file6.mkdirs()) {
			System.out.println("\"" + file5.getName() + "\" 폴더 만들기 성공");
		} else {
			System.out.println("\"" + file5.getName() + "\" 폴더 만들기 실패");  
		}
		// ▲ mkdir()6은 /test/java 폴더들이 생성되어있지 않기 때문에 생성되지 않는다.
		
		System.out.println("\n===================================\n");
		
		System.out.println("\"" + file1.getName() + "\" 파일의 크기는 "
						+ file1.length() + "byte(s)");
		
		System.out.println("\n===================================\n");
		
		// 이클립스에서 파일을 처리할 때 현재 위치는 해당 프로젝트 위치와 같다.
		File file7 = new File("src/basic/FileTest01.java");
		System.out.println("getPath() => " + file7.getPath());
		System.out.println("getAbsolutePath() => " + file7.getAbsolutePath());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
