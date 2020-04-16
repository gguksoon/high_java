package a_collectionTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest2 {

	/*
	 * 	정렬과 관련된 interface는 Comparable과 Comparator가 있다.
	 * 
	 * 	- Comparable ==> 어떤 객체 자체에 정렬 기준을 넣을 경우에 구현한다.
	 * 				 ==> compareTo()메서드를 재정의해서 구현한다.
	 * 
	 * 	- Comparator ==> 정렬 기준은 외부에서 따로 정해주고 싶을 때 구현한다.
	 * 				 ==> compare()메서드를 재정의해서 구현한다.
	 */
	
	public static void main(String[] args) {
		
		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-2222"));
		memList.add(new Member(9, "성춘향", "010-3333-3333"));
		memList.add(new Member(3, "이순신", "010-4444-4444"));
		memList.add(new Member(6, "강감찬", "010-5555-5555"));
		memList.add(new Member(2, "일지매", "010-6666-6666"));
		
		System.out.println("[정렬전]");
		for(Member mem : memList){
			System.out.println(mem);
		}
		
		System.out.println("----------------------------------------------");
		
		Collections.sort(memList);
		
		System.out.println("[정렬후]");
		for(Member mem : memList){
			System.out.println(mem);
		}
		
		System.out.println("----------------------------------------------");
		
		Collections.sort(memList, new SortNumDesc());
		
		System.out.println("[정렬후]");
		for(Member mem : memList){
			System.out.println(mem);
		}
	}

}

// 회원 번호의 내림차순으로 정렬될 수 있도록 외부 정렬 기준class를 작성하시오.
class SortNumDesc implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {
		/*
		if(mem1.getNum() > mem2.getNum())
			return -1;
		else if(mem1.getNum() == mem2.getNum())
			return 0;
		else 
			return -1;
		*/
		
		/*
		return Integer.toString(mem1.getNum()).compareTo(Integer.toString(mem2.getNum())) * -1;
		*/
		
		// Wrapper클래스를 이용하는 방법1
		/*
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
		*/
		
		// Wrapper클래스를 이용하는 방법2
		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
	}


	
}

// Member클래스를 구성하는 구성요소 중 회원의 이름을 기준으로 오름차순 정렬이 될 수 있도록 구현한다.
class Member implements Comparable<Member> { // 제네릭은 클래스명과 동일하게
	private int num;		// 번호
	private String name;	// 이름
	private String tel;		// 전화번호
	
	// ALT + SHIFT + S -> Generator Constructor using Fields
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	// ALT + SHIFT + S -> Generator Getters and Setters
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	// ALT + SHIFT + S -> Generator toString()
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	@Override
	public int compareTo(Member mem) {
		// 현재 객체의 회원이름과 파라미터로 전달되는 객체의 회원이름을 비교하여 정렬되도록 구현한다. 
		return getName().compareTo(mem.getName());
	}
	
}