package b_argumentTest.generic;

import java.util.Map;

/*
 * <<제네릭 클래스 만드는 방법>>
 * 	class 클래스명<제네릭타입글자>{
 * 		제네릭타입글자 변수명;		// 변수 선언에 사용할 때
 * 		...
 * 
 * 		제네릭타입글자 메서드명(){	// 반환값이 있는 메서드에서 사용할 때
 * 			...
 * 			return 값; 
 * 		}	
 * 
 * 		메서드명(제네릭타입글자 변수명){	// 메서드의 매개변수에 사용할 때
 * 			...
 * 		}
 * 	}
 * 
 * <<제네릭타입글자>>
 * T ==> Type
 * K ==> Key
 * V ==> Value
 * E ==> Element
 */

public class GenericTest {

	public static void main(String[] args) {
		
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		String rtnVal = (String)ng1.getVal();
		System.out.println("rtnVal: " + rtnVal);
		
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(123);
		Integer irtnVal = (Integer)ng2.getVal();
		System.out.println("irtnVal: " + irtnVal);

		
		// 런타임에러가 발생함(컴파일러는 잡지 못함) 
//		rtnVal = (String)ng2.getVal();
		// 위와 같은 에러를 방지하고자 제네릭을 사용한다.
		
		
		System.out.println("=================================");
		
		
		MyGenericClass<String> mg1 = new MyGenericClass<>();
		mg1.setVal("가나다라");
		String temp = mg1.getVal(); // 형변환을 하지 않아도 된다.
		System.out.println("temp: " + temp);
		
		
		MyGenericClass<Integer> mg2 = new MyGenericClass<>();
		mg2.setVal(123);
		Integer itemp = mg2.getVal();
		
		
		// 컴파일에러가 발생함
//		mg2.setVal("ABCD");
		// 제네릭을 사용함으로써 에러를 사전에 방지할 수 있다.
	}

}

// 제네릭을 사용하지 않는 클래스
class NonGenericClass {
	private Object val;

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}
}


// 제네릭을 사용하는 클래스
class MyGenericClass<T> { // Integer, String등을 유동적으로 받을 수 있다.
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
}