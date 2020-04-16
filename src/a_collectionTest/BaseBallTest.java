package a_collectionTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
	문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
		 컴퓨터의 숫자는 난수를 이용하여 구한다. (1 ~ 9사이의 서로 다른 난수 3개)
		 (스트라이크는 S, 볼은 B로 출력한다.)
		 
		 [컴퓨터가 만든 난수가 9 5 7일 경우]
	실행예시)
		 숫자 입력: 3 5 6
		 3 5 6 => 1S 0B
		 숫자 입력: 7 8 9
		 7 8 9 => 0S 2B
		 숫자 입력: 9 7 5
		 9 7 5 => 1S 2B
		 숫자 입력: 9 5 7
		 9 5 7 => 3S 0B
		 
		 4번째 만에 맞췄습니다.
*/
public class BaseBallTest {

	
	/**
	 * 세 개의 숫자를 공백으로 구분하여 입력받는다. (9 4 1)
	 * 1. 입력받은 값이 숫자가 아니면 다시 입력을 받는다.
	 * 2. 입력받은 값이 3개가 아니면 다시 입력을 받는다.
	 * 3. 정답과 비교하여 결과를 출력한다.
	 * 4. 결과를 출력한 뒤 위 내용을 반복한다.  
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		HashSet<Integer> randNum = new HashSet<Integer>(); // 임의의 중복되지 않는 숫자 3개를 저장
		while(randNum.size() < 3){ // 숫자 생성
			randNum.add((int)(Math.random() * 9) + 1);
		}
		
		ArrayList<Integer> randNumList = new ArrayList<>(randNum); // 숫자를 저장할 리스트(인덱스)
		Collections.shuffle(randNumList); // 숫자의 순서를 섞기
		
		System.out.println("컴퓨터가 생성한 숫자: " + randNumList + "\n"); // 최종으로 생성된 숫자 출력
		
		int count = 1;
		String pattern = "^[1-9]$";
		boolean flag = false;
		while(true){ // 정답을 입력할때 까지 무한루프
			System.out.println("\n <<" + count + "회차>>");
			System.out.println(" > 3개의 1~9사이의 숫자를 공백으로 구분하여 입력하세요. ex) 4 5 9");
			System.out.print(" > ");
			String inputNum = sc.nextLine(); // 3가지 숫자를 공백으로 입력받음
			String[] nums = inputNum.split(" "); // 입력값을 nums라는 String배열에 나눠서 입력
			
			for(int i = 0; i < nums.length; i++){ // 배열에 저장된 값들이 1~9의 숫자인지 정규식 확인
				if(!Pattern.matches(pattern, nums[i]))
					flag = true;
			}
			
			if(flag){ // 정규식을 이용하여 1~9가 아닐 때 다시 입력받게 함.
				System.out.println(" > 1~9사이의 숫자만 입력하세요!");
				flag = false;
				continue;
			}
			
			if(nums.length == 3){ // 3개의 숫자를 정상적으로 입력했을 때
				int strike = 0;
				int ball = 0;
				for(int i = 0; i < 3; i++){
					int num = Integer.parseInt(nums[i]);
					if(num == randNumList.get(i)){
						strike++;
					} else if(num == randNumList.get(0) || num == randNumList.get(1) || num == randNumList.get(2)){
						ball++;
					}
				}
				System.out.println(" > " + Arrays.toString(nums) + " ==> " + strike + "S " + ball + "B");
				
				if(strike == 3){ // 정답인경우
					System.out.println("\n > 정답입니다. 도전 횟수: " + count);
					break;
				}
				
				count++;
				
			} else { // 3개의 숫자를 입력하지 않았을 때 
				System.out.println(" > 세 개의 숫자를 공백으로 구분하여 입력하세요.");
			}
		}
	}

}
