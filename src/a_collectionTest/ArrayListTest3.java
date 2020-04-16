package a_collectionTest;

import java.util.ArrayList;
import java.util.Scanner;

/*
	문제1) 5명의 별명을 입력하여 ArrayList에 저장하고
		   별명의 길이가 제일 긴 별명을 출력하시오.
		 (단, 각 별명들은 길이가 모두 다르다.)
		 
	문제2) 문제1에서 별명의 길이가 같은 것도 입력되었을 때를 처리하시오.
*/
public class ArrayListTest3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ArrayList<String> nameList = new ArrayList<String>();
		
		// 입력받기
		for(int i = 0; i < 5; i++){ 
			System.out.print("별명을 입력하세요.(" + (i + 1) + "/5): ");
			nameList.add(sc.nextLine());
		}
		
		// 비교한 데이터를 저장할 공간
		ArrayList<Integer> maxLengthIndex = new ArrayList<>();
		maxLengthIndex.add(0);
		
		// 비교하기
		for(int i = 1; i < nameList.size(); i++){ 
			if(nameList.get(i).length() > nameList.get(maxLengthIndex.get(0)).length()){
				maxLengthIndex.clear();
				maxLengthIndex.add(i);
			} else if(nameList.get(i).length() == nameList.get(maxLengthIndex.get(0)).length()){
				maxLengthIndex.add(i);
			}
		}
		
		// 출력하기
		for(int i = 0; i < maxLengthIndex.size(); i++){
			System.out.println("가장 긴 별명은 \"" + nameList.get(maxLengthIndex.get(i)) + "\"입니다.");
		}
	}

}
