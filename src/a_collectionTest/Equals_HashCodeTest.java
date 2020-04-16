package a_collectionTest;

import java.util.HashSet;

public class Equals_HashCodeTest {

	public static void main(String[] args) {

//		String str1 = "안녕";
//		String str2 = "안녕";
//		String str3 = new String("안녕");
//		String str4 = new String("안녕");
//		
//		System.out.println("str1 == str2: " + (str1 == str2));
//		System.out.println("str1 == str3: " + (str1 == str3));
//		System.out.println("str3 == str4: " + (str3 == str4) + "\n");
//		
//		System.out.println("str1.equals(str2): " + str1.equals(str2));
//		System.out.println("str1.equals(str3): " + str1.equals(str3));
//		System.out.println("str3.equals(str4): " + str3.equals(str4));
		
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
		p2.setId(1);
		p2.setName("홍길동");
		
		// equals()메서드는 재정의하지 않으면 기본적으로 참조값으로 비교하도록 되어있다.(p1 == p2와 같다)
		System.out.println("p1.equals(p2): " + p1.equals(p2)); // 재정의 전 p1 == p2와 같음
		// 위 equals메서드는 재정의하여 true가 출력된다.(원래는 false가 나왔음)
		
		// set에는 중복된 자료가 들어가면 안되지만 같은 내용의 객체가 들어간다.
		// 그 이유는 hashset의 경우 hash(참조값)도 비교하기 때문에 중복된 자료가 들어갈 수 있다.
		HashSet<Person> set = new HashSet<>(); 
		set.add(p1);
		set.add(p2); // equals를 재정의 한 다음 들어가지 않는다.(원래는 들어감)
		set.add(p1); // 이건 재정의 하지 않아도 들어가지 않는다.
		
		System.out.println("set의 개수 ==> " + set.size());
	}
}

/*
 	- equals()메서드는 두 객체의 내용이 같은지 즉, 동등성(equality)를 비교하는 연산자이다.
 	- hashCode()메서드는 두 객체가 같은지 즉, 동일성(identity)를 비교하는 연산자이다.
 	
 	- HashSet이나 HashMap, HashTable과 같은 객체들을 사용하는 경우
 	    객체의 의미상의 동등성 비교를 위해 hashCode()메서드를 호출한다.
 	    그러므로, 객체가 같은지 여부를 결정하려면 hashCode()메서드를 재정의 해야한다.
 	    
 	- equals()와 hashCode()에 관련된 규칙
 	  1. equals()메서드를 Override하면 반드시 hashCode()메서드도 Override해야 한다.
 	  2. hashCode()메서드는 기본적으로 Heap에 있는 각 객체에 대한 메모리 주소(참조값)를 기반으로 한 정수값을 반환한다.
 	  3. a.equals(b)가 true이면 a.hashCode()==b.hashCode()도 성립한다.
 	          하지만 a.hashCode()==b.hashCode()가 성립해도 a.equals(b)가 반드시 true인 것은 아니다.
 	     (이유: hashCode()메서드에서 사용하는 '해싱 알고리즘'에서 서로 다른 객체들에 대해 같은 hashCode값을
 	                     만들어낼 수 있기 때문에 객체가 같지 않더라도 hashCode가 같을 수 있다.) 
*/

class Person{
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() { // 자동완성
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) { // 자동완성
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
//	@Override
//	public boolean equals(Object obj) { // 선생님이 만든 equals
//		// id와 name이 모두 같으면 true를 반환하도록 한다.
//		
//		if(obj == null) // obj가 null인 경우
//			return false;
//		
//		if(this.getClass() != obj.getClass()) // 같은 클래스가 아닐 경우
//			return false;
//		
//		if(this == obj){ // 같은 객체일 때
//			return true;
//		}
//		
//		Person temp = (Person)obj; // 해당 객체형으로 형변환
//		
//		if(this.name == null && temp.name != null)
//			return false;
//		
//		if(this.id == temp.id && this.name == temp.name) // null인 경우 확인
//			return true;
//
//		if(this.id == temp.id && this.name.equals(temp.name)) // id는 숫자이므로 ==, name은 스트링이므로 equals
//			return true;
//		
//		return false;
//	}
	
}
