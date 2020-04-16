package a_collectionTest;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		// 벡터라는 객체안에 배열이 존재
		// 초기에는 10개정도 크기의 배열을 가지고 있음
		// 이 후 자료가 추가되면 배열이 커짐
		
		// 벡터객체선언
		Vector v1 = new Vector(); // Object타입
		// 객체의  크기 확인: .size()
		System.out.println("v1의 초기 사이즈: " + v1.size());
		
		// 데이터 추가: .add()메서드
		v1.add("AAA"); // 문자열(객체)
		
		v1.add(111);   // 정수(객체아님) <= 옛날엔 에러났지만 auto boxing
					   // auto boxing: 일반 자료형을 자동으로 객체형으로 변환하는 기능
		v1.add(new Integer(123)); // 정수(객체) <= wrapper class
		
		v1.add('a'); // auto boxing
		v1.add(true); 
		v1.add(3.14);
		
		System.out.println("v1의 크기: " + v1.size());
		
		
		
		// .addElement()메서드를 이용하여 추가할 수도 있다.
		// 이 메서드는 벡터초창기에 사용하던 메서드로, 호환성을 위해 존재한다.
		// 예전의 프로그램에서 사용하던 것이며 현재는 add()를 사용한다.
		v1.addElement("ccc");
		System.out.println("v1 => " + v1.toString());
		System.out.println("v1 => " + v1); // .toString()이 붙은것과 같다.(자동)
		
		// .add(index, data): 벡터의 index번째에 'data'를 끼워 넣는다.
		//				            벡터의 index는 '0'부터 시작한다.
		//				            반환값: add가 성공적인지 알려주는 boolean타입
		v1.add(1, "kkk");
		System.out.println("v1 => " + v1);
		
		// .set(index, data): 벡터의 index번째에 있는 data를 주어진 'data'로 덮어쓴다.
		//					    반환값: 기존의 데이터
		String temp = (String)v1.set(0, "zzz"); // Object타입으로  되어있기에 자식인 String타입에 주려면 형변환을 해야된다.
		System.out.println("v1 => " + v1);
		System.out.println("원래 데이터: " + temp);
		
		// .remove(index): 벡터의 index번째 데이터를 삭제한다.
		//				      자료가 삭제되면 index번째 다음번째의 데이터들이 앞으로 자동으로 당겨져서 채워진다.
		//				      반환값: 삭제된 데이터
		// .remove(삭제할 데이터): '삭제할 데이터'를 찾아서 삭제한다.
		//					   '삭제할 데이터'가 여러개면 앞에서부터 삭제한다.
		//					   '삭제할 데이터'가 '정수형'이거나 'char형'일 경우에는 객체로 변환해서 사용해야 한다.
		v1.remove(0);
		System.out.println("0번째 삭제 후 v1 => " + v1);
		
		temp = (String)v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.add(123);
		v1.remove(true);
		System.out.println("삭제 후 v1 => " + v1);
		
//		v1.remove(123); // 123번째를 지우라고 하는 말과 같음 따라서 에러발생
		v1.remove(new Integer(123));
		System.out.println("삭제 후 v1 => " + v1);
		
//		v1.remove('a'); // 'a'번째(9번째)를 지우라는 말과 같음
		v1.remove(new Character('a'));
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(3.14);
		System.out.println("삭제 후 v1 => " + v1);
		
		// .get(index): index번째의 데이터를 꺼내온다(반환한다).
		int data = (int)v1.get(0); // Object형태이므로 형변환해준다.
		System.out.println("0번째 자료: " + data);
		System.out.println("v1 => " + v1);
		
		System.out.println("======================================================");
		// ----------------------------------------------------------------------------
		/*
		 * 제네릭타입(generic type) 
		 *    ==> 객체를 선언할 때 < > 안에 그 Collection이 사용할 데이터 타입을 정해주는 것을 말한다.
		 *    ==> 이런식으로 선언하게 되면 그 데이터 타입 이외의 다른 데이터를 저장할 수 없다.
		 *    ==> 단, 제네릭 타입으로 선언될 수 있는 데이터 타입은 클래스이여야 한다. 	
		 *        그래서 int는 Integer, boolean은 Boolean, char는 Character등으로 사용해야 한다.
		 *    ==> 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다.
		 */
		
		// 인스턴스부분은 <>을 써도 <String>과 같다.
		Vector<String> v2 = new Vector<String>(); // String 데이터만 저장할 수 있는 벡터 선언
		Vector<Integer> v3 = new Vector<>();
		
		v2.add("안녕하세요");
//		v2.add(123); // 오류: 지정한 제네릭타입과 다른 종류의 데이터를 추가할 수 없다.
		
		v3.add(123);
//		v3.add(3.14); // 오류: 지정한 제네릭타입과 다른 종류의 데이터를 추가할 수 없다.
		
		String temp2 = v2.get(0); // 형변환 없이 사용 가능하다.
		
		Vector<Vector> vv = new Vector<>(); // 2차원 배열과 비슷한 형태
		
		Vector<Vector<Vector>> vvv = new Vector<>(); // 3차원 배열과 비슷한 형태
		
		// ----------------------------------------------------------------------------
		
		// .clear()메서드 ==> 벡터의 모든 데이터를 지운다.
		v2.clear();
		System.out.println("v2의 크기: " + v2.size());
		
		v2.add("AAAA");
		v2.add("BBBB");
		v2.add("CCCC");
		v2.add("DDDD");
		v2.add("EEEE");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBBB");
		v4.add("EEEE");		
		
		System.out.println("v2 => " + v2);
		System.out.println("v4 => " + v4);
		
		// .removeAll(Collection객체) ==> 벡터에서 'Collection객체"가 가지고 있는 데이터를 모두 지운다.
		v2.removeAll(v4);
		System.out.println("v2 => " + v2);
		System.out.println("-----------------------------");
		
		v2.clear();
		v2.add("AAAA");
		v2.add("BBBB");
		v2.add("CCCC");
		v2.add("DDDD");
		v2.add("EEEE");
		
		// 벡터의 데이터들을 순서대로 가져와 사용하고 싶으면 보통 반복문을 사용하면 된다.
		// (주로 for문을 많이 사용한다.)
		
		for(int i = 0; i < v2.size(); i++){
			System.out.println(i + "번째 자료: " + v2.get(i));
		}
		System.out.println("-----------------------------");
		
		// 향상된 for문 사용
		// for(자료형타입 변수명 : 배열 또는 Collection){ }
		for(String i : v2){ // 몇번째인지 첨자는 알수 없다.
			System.out.println(i);
		}
		
	}

}
