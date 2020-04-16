package a_collectionTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/*
	<List와 Set의 차이점>
	1. List
		- 데이터의 순서가 있다. (index가 있다.)
		- 중복된 데이터를 저장할 수 있다.
	2. Set
		- 데이터의 순서가 없다. (index가 없다.)
		- 중복된 데이터를 저장할 수 없다.
*/

public class SetTest {

	public static void main(String[] args) {
		
		HashSet hs1 = new HashSet();
		// 데이터 추가: add()메서드 사용
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set의 개수: " + hs1.size());
		
		// 출력된 결과는 입력한 순서와 무관하다.
		System.out.println("hs1 ==> " + hs1 + "\n");
		
		// HashSet은 데이터의 순서가 없고 중복을 허용하지 않는다.
		// 그래서 이미 저장되어 있는 데이터를 add하면 false를 반환하고 그 데이터는 추가되지 않는다.
		boolean isAdd = hs1.add("FF"); // 중복X 데이터 추가
		System.out.println("중복되지 않은 데이터를 추가할 때(\"FF\"): " + isAdd);
		System.out.println("hs1 ==> " + hs1 + "\n");
		
		isAdd = hs1.add("FF"); // 중복O 데이터 추가
		System.out.println("중복되는 데이터를 추가할 때(\"FF\"): " + isAdd);
		System.out.println("hs1 ==> " + hs1 + "\n");
		
		// HashSet의 데이터 수정하기 
		//		==> 수정하는 명령이 따로 없기 때문에 해당 자료를 삭제한 후 추가해 주어야 한다.
		
		// 삭제하는 메서드 ==> remove(삭제할 데이터) --> 해당 데이터 삭제
		// 			 ==> clear() --> 전체 삭제
		System.out.println("<데이터 수정하기> - - - 삭제하고 추가한 것");
		hs1.remove("FF");
		System.out.println("삭제한 데이터: \"FF\"");
		System.out.println("hs1 ==> " + hs1 + "");
		hs1.add("EE");
		System.out.println("추가한 데이터: \"EE\"");
		System.out.println("hs1 ==> " + hs1 + "\n");
		System.out.println("=========================================================");
//		hs1.clear(); // 전체자료 삭제
//		System.out.println("clear()");
//		System.out.println("hs1 ==> " + hs1 + "\n");
		
		
		
		
		
		// Set은 데이터의 순서(index)가 없기 때문에 List처럼 index로 데이터를 하나씩 불러올 수 없다.
		// 그래서 데이터를 순서대로 처리할 수 있게 하려면 Iterator로 변환해서 사용해야 한다.
		// Iterator로 변환해 주는 메서드 ==> iterator()
		
		Iterator it = hs1.iterator(); // Iteraotr객체로 변환하기
		
		// 데이터 개수만큼 반복하기
		while(it.hasNext()){ // 다음 자료가 있는지 검사
			// next() ==> 포인터를 다음 자료 위치로 이동하고 이동한 위치의 자료를 반환한다.
			System.out.print(it.next() + "  ");
		}
		System.out.println("\n");
		
		// HashSet을 이용한 로또번호 만들기
		HashSet<Integer> lotto = new HashSet<>();
		
		while(lotto.size() < 6){
			// 1 ~ 45사이의 난수 만들기
			int num = (int)(Math.random() * (45 - 1 + 1) + 1);
			lotto.add(num);
		}
		
		System.out.println("lotto번호: " + lotto);
		
		// 컬렉션 유형의 객체들은 서로 다른 자료 구조로 쉽게 변경해서 사용할 수 있다.
		ArrayList<Integer> lottoList = new ArrayList<>(lotto);
		System.out.print("로또번호: ");
		for(Integer num : lottoList){
			System.out.print(num + " ");
		}
	}

}
