package a_collectionTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		/*
		 * Map ==> key와 value를 한 쌍으로 관리하는 객체
		 *	   ==> key: 중복을 허용하지 않고 순서가 없다.(Set의 특징)
		 *	   ==> value: 중복을 허용한다.
 		 */
		HashMap<String, String> map = new HashMap<>();
		
		// 자료 추가 ==> put(key, value)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		System.out.println("map => " + map);
		System.out.println("===============================================");
		
		// 자료 수정 ==> 데이터를 저장할 때 key가 같으면 나중에 추가한 값이 저장된다.
		map.put("addr", "서울");
		System.out.println("map => " + map);
		System.out.println("===============================================");
		
		// 자료 삭제 ==> remove(key)
		//		  ==> 반환값: 삭제한 value(삭제 실패시 null)
		System.out.println("삭제한 데이터: " + map.remove("tel"));
		System.out.println("삭제한 데이터: " + map.remove("tell"));
		System.out.println("map => " + map);
		System.out.println("===============================================");
		
		// 자료 읽기 ==> get(key) ==> key의 짝인 value가 반환된다.
		//		  ==> 지정한 key값이 없으면 null을 반환
		System.out.println("이름: " + map.get("name"));
		System.out.println("주소: " + map.get("addr"));
		System.out.println("번호: " + map.get("number"));
		System.out.println("===============================================");
		
		// 전체 데이터를 처리하기 ==> 모든 key를 읽어와서 처리하기
		// 방법1 ==> keySet()메서드 이용하기
		//			--> Map의 key만 읽어와 Set형으로 반환한다.
		Set<String> keySet = map.keySet(); // Set은 중복x 순서x이므로 Map과 같다
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key + ": " + map.get(key));
		}
		System.out.println("===============================================");
		
		// 방법2 ==> keySet()을 향상된 for문으로 처리하기(방법1보다 간편)
		for(String key : map.keySet()){
			System.out.println(key + ": " + map.get(key));
		}
		System.out.println("===============================================");
		
		// 방법3 ==> value값만 읽어오기
		for(String value : map.values()){
			System.out.println(value);
		}
		System.out.println("===============================================");
		
		//			Map에는 Entry라는 내부클래스가 만들어져 있다.
		// 			이 Entry클래스는 key와 value라는 멤버변수로 구성되어 있다.
		// 			Map에서는 이 Entry클래스들을 Set형식으로 저장하여 관리한다.
		
		// 방법4 ==> Entry객체 전체를 가져와서 처리하기(최근 많이 쓰진 않음)
		//			(가져온 Entry객체들은 Set형식으로 되어 있다.)
		// 		==> entrySet()메서드를 사용한다.
		Set<Map.Entry<String, String>> mapSet = map.entrySet();// Entry의 제네릭은 기존 Map의 제네릭과 맞춰준다.
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		while(entryIt.hasNext()){
			// Entry형 객체를 하나씩 꺼내온다.
			Map.Entry<String, String> entry = entryIt.next();
			System.out.print("key값: " + entry.getKey() + " / ");
			System.out.println("value값: " + entry.getValue());
		}
		System.out.println("===============================================");
		
		// key의 존재 여부 검사: containsKey(key)
		System.out.println("\"key:name\" 존재여부 ==> " + map.containsKey("name"));
		System.out.println("\"key:tel\" 존재여부 ==> " + map.containsKey("tel"));
		System.out.println("===============================================");
		
		// value값의 존재 여부 검사: containsValue(value)
		//					==> 해당 value가 있으면 true, 없으면 false
		System.out.println("\"value:대전\" 존재여부" + map.containsValue("서울"));
		System.out.println("\"value:홍길동\" 존재여부" + map.containsValue("홍길동"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
