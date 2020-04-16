package a_collectionTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전: " + list);
		
		// 정렬은 Collections.sort()메서드를 이용하여 정렬한다.
		// 정렬은 기본적으로 오름차순 정렬을 수행한다.
		Collections.sort(list);
		
		System.out.println("정렬 후: " + list);
		
		Collections.shuffle(list); // 데이터 섞기
		
		System.out.println("자료 섞기 후 : " + list);
		
		Collections.sort(list, new Desc()); // Desc클래스에서 구현한 compare를 이용
		
		System.out.println("정렬 후: " + list);
	}

}

class Desc implements Comparator<String>{
	/*
	 *	compare()메서드의 반환값이 양수이면 인접한 두 데이터의 순서가 바뀐다.
	 *
	 * 	오름차순 ==> 앞의 데이터가 크면 양수, 같으면 0, 작으면 음수
	 * 
	 * 	String객체에는 정렬을 위해서 compareTo()메서드가 구현되어 있는데 이 메서드의 반환값은 
	 * 	오름차순에 맞도록 구현되어 있다.
	 * 	(Wrapper클래스와 Date클래스, File클래스에도 compareTo()메서드가 구현되어 있다.)
	 */
	@Override
	public int compare(String o1, String o2) {
		/*if(o1.compareTo(o2) > 0) // 앞의 데이터(o1)가 크다
			return -1; // 두 데이터가 같다
		else if(o1.compareTo(o2) == 0)
			return 0;
		else  // 앞의 데이터(o1)가 작다
			return 1;*/
		
		return o1.compareTo(o2) * -1; // 위 3가지 경우를 한 줄로 줄이기
	}
}
