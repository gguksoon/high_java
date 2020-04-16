package a_collectionTest;

import java.util.SortedSet;
import java.util.TreeSet;

public class SetTest2 {

	public static void main(String[] args) {
		// HashSet은 데이터의 순서가 없으나
		// TreeSet은 자동 정렬 기능이 들어가 있다.
		
		TreeSet<String> ts = new TreeSet<>();
		
		for(char ch='Z'; ch>='A'; ch--){
			String temp = String.valueOf(ch); // char형을 String형으로 변환하는 방법1
//			String temp = ch + ""; // char형을 String형으로 변환하는 방법2
			ts.add(temp);
		}
		
		System.out.println("ts ==> " + ts + "\n");
		
		// TreeSet에 저장된 자료 중 특정한 자료보다 작은 자료를 찾아서 SortedSet으로 반환하는 메서드가 있다.
		
		// headSet(기준값) 메서드
		//	   ==> 기본적으로 '기준값'은 포함되지 않는다.
		//	   ==> '기준값' 포함 여부는 '기준값' 다음에 true(포함), false(미포함)로 지정하여 정할 수 있다.
		SortedSet<String> ss1 = ts.headSet("K");
		System.out.println("K이전 자료: " + ss1);
		System.out.println("K이전 자료: " + ts.headSet("K", true) + "\n"); // k 포함
		
		// tailSet(기준값) 메서드
		//     ==> 기본적으로 '기준값'이 포함된다.
		//	   ==> '기준값'보다 큰 자료를 찾아 SortedSet형으로 반환한다.
		SortedSet<String> ss2 = ts.tailSet("K");
		System.out.println("K이후 자료: " + ss2);
		System.out.println("K이후 자료: " + ts.tailSet("K", false) + "\n"); // k 미포함
		
		// subSet()aptjemsms ~이상 ~미만의 값을 가져온다.
		System.out.println(ts.subSet("K", "N")); // K포함, N미포함 
		
		// subSet()메서드로 기준값 뒤에 boolean을 붙여서 포함 여부를 정할 수 있다.
		System.out.println(ts.subSet("K", false, "N", true) + "\n"); // K미포함, N포함
		
	}

}
