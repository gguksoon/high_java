package a_collectionTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * 	문제) 학번(int), 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수(모두 int)를 멤버로 갖는 Student클래스를 만든다.
 * 	생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화한다.
 * 
 * 	이 Student객체들은 List에 저장하여 관리한다.
 * 	List에 저장된 데이터들을 학번의 오름차순 정렬로 출력하는 부분(내부 정렬 기준)과
 * 	총점의 역순으로 정렬하는 부분(외부정렬기준)을 프로그램 하시오.
 * 	(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 */
public class StudentTest {

	public static void main(String[] args) {
		ArrayList<Student> stuList = new ArrayList<Student>();
		
		stuList.add(new Student(20020123, "최재영", 80, 80, 85));
		stuList.add(new Student(20084321, "김소리", 50, 50, 50));
		stuList.add(new Student(20131111, "이인규", 40, 40, 30));
		stuList.add(new Student(20132222, "강승구", 20, 40, 50));
		stuList.add(new Student(20110828, "조민수", 90, 90, 95));
		stuList.add(new Student(20141235, "박은지", 50, 50, 50));
		stuList.add(new Student(20112323, "박태순", 10, 80, 10));
		
		// 순위 매기기
		new StudentTest().ranking(stuList);
		
		// 정렬 전
		System.out.println("[정렬 전]");
		for(Student stu : stuList){
			System.out.println(stu);
		}
		System.out.println("===============================================================================");
		
		// 학번순 정렬
		Collections.sort(stuList);
		System.out.println("[학번 순 정렬]");
		for(Student stu : stuList){
			System.out.println(stu);
		}
		System.out.println("===============================================================================");
		
		// 점수별 정렬
		Collections.sort(stuList, new SortRankDesc());
		System.out.println("[총점 순 정렬]");
		
		for(Student stu : stuList){
			System.out.println(stu);
		}
		System.out.println("===============================================================================");
	}
	
	void ranking(ArrayList<Student> stuList){
		for(int i = 0; i < stuList.size(); i++){
			for(int j = 0; j < stuList.size(); j++){
				if(stuList.get(i).getSum() < stuList.get(j).getSum()){
					stuList.get(i).setRank(stuList.get(i).getRank() + 1);
				}
			}
		}
	}

}

class Student implements Comparable<Student>{
	private int number;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int sum;
	private int rank;
	
	public Student(int number, String name, int kor, int eng, int mat) {
		this.number = number;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.sum = kor + eng + mat;
		this.rank = 1;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "Student [number=" + number + ", name=" + name + ", kor=" + kor
				+ ", eng=" + eng + ", mat=" + mat + ", sum=" + sum + ", rank="
				+ rank + "]";
	}

	@Override
	public int compareTo(Student stu) {
		// 내가 한 답
//		return ("" + getNumber()).compareTo(("" + stu.getNumber()));
		// 슨생님 답
		return Integer.compare(getNumber(), stu.getNumber());
	}
}

/*
 	// 주로 Comparator는 익명클래스로 사용한다.
	Comparator<Employee> salesComparator = new Comparator<Employee>() {
    	@Override
		public int compare(Employee o1, Employee o2) {
			return o2.getSales().intValue() - o1.getSales().intValue();
		}
	};
	출처: https://jeong-pro.tistory.com/173 [기본기를 쌓는 정아마추어 코딩블로그]
	// Comparable은 일반적인 정렬,
	// Comparator는 커스터마이즈 정렬(비교가 아님)
*/

class SortRankDesc implements Comparator<Student>{

	@Override
	public int compare(Student stu1, Student stu2) {
		if(stu1.getSum() > stu2.getSum())
			return -1;
		else if(stu1.getSum() == stu2.getSum())
			return stu1.compareTo(stu2) * -1;
		else 
			return 1;
		
	}
	
}
