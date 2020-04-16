package a_collectionTest;

import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {
		
		// ArrayList는 기본적인 사용법이 Vector와 같다.
		// Vector는 이 후에 배울 thread에서 동기화기능이 있다.
		// 동기화는 쓰레드간 같은 데이터를 참조하려 할 때 한 쓰레드만 참조할 수 있도록 보호하는 것이다.
		// 데이터를 참조하는 동안 방해를 받으면 데이터가 손실될 수 있다.
		
		ArrayList list1 = new ArrayList();
		
		// add()메서드를 이용하여 추가 작업을 한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
		list1.add('k');
		list1.add(true);
		list1.add(12.345);
		
		System.out.println("리스트의 크기: " + list1.size());
		System.out.println("list1 => " + list1);
		
		// get()메서드로 데이터를 꺼내온다.
		System.out.println("2번째 자료: " + list1.get(2));
		
		// 데이터 끼워넣기도 같다. ==> add(index, 데이터)
		list1.add(1, "zzz");
		System.out.println("list1 => " + list1);
		
		// 데이터 변경하기 ==> set(index, 데이터)
		String temp = (String) list1.set(0, "yyy");
		System.out.println("list1 => " + list1);
		System.out.println("변경전 자료: " + temp);
		
		// 삭제도 같다.
		list1.remove(0);
		System.out.println("삭제 후 list => " + list1);
		
		list1.remove("bbb");
		System.out.println("삭제 후 list => " + list1);
		
		System.out.println("----------------------------------------------");
		
		// ---------------------------------------------------------------------
		
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		
		for(int i = 0; i < list2.size(); i++){
			System.out.println(i + ": " + list2.get(i));
		}
		
		System.out.println("----------------------------------------------");
		
		for(String str : list2)
			System.out.println(str);
		
		System.out.println("----------------------------------------------");
		
		// ---------------------------------------------------------------------

		// contains(비교데이터); ==> 리스트에 '비교데이터'가 있으면 true, 그렇지 않으면 false
		System.out.println(list2.contains("DDDD")); // 있으면 true 없으면 false
		System.out.println(list2.contains("ZZZZ"));
		
		// indexOf(비교데이터); ==> 리스트에 '비교데이터'가 있으면 '비교데이터'가 있는 index값을 반환한다.
		//					 ==> 없으면 -1을 반환한다.
		System.out.println("DDDD의 index값: " + list2.indexOf("DDDD"));
		System.out.println("ZZZZ의 index값: " + list2.indexOf("ZZZZ"));
		
		System.out.println("----------------------------------------------");

		// toArray(); ==> 리스트 안의 데이터들을 배열로 변환하여 반환한다.
		// 			  ==> 기본적으로 Object형 배열로 변환한다.
		// toArray(new 제네릭타입[0]); ==> 제네릭타입의 배열로 반환한다.
		
		// Object로만 되는것이 단점이다.
		Object[] strArr = list2.toArray();
		System.out.println("배열의 개수: " + strArr.length);
		for(int i = 0; i < strArr.length; i++){
//			String sss = strArr[i]; // 에러발생, (String)추가해야됨
			System.out.println(i + "번째: " + strArr[i]);
		}
		
		String[] strArr2 = list2.toArray(new String[0]);
		for(String str2 : strArr2){
			System.out.println(str2);
		}
		
	}

}
