package basic;

public class Thread_Index {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 싱글 쓰레드
		new ThreadTest01();
		
		// 쓰레드 사용법(인스턴스.start() / Runnable / 익명구현체)
		new ThreadTest02();
		
		// 쓰레드의 수행시간
		new ThreadTest03();
		new ThreadTest04();
		
		// 쓰레드를 이용하여 시간제한 입력받기
		new ThreadTest05();
		new ThreadTest06();
		new ThreadTest07(); // 문제(가위 바위 보)
		
		// 쓰레드의 우선순위
		new ThreadTest08();
		
		// 데몬 쓰레드
		new ThreadTest09();
		
		// 쓰레드의 상태
		new ThreadTest10();
		
		// 쓰레드의 종료 순서대로 출력
		new ThreadTest11();
		new ThreadTest12(); // 문제(경마)
		
		// 쓰레드 양보(yield)
		new ThreadTest13();
		
		// 쓰레드 정지(stop / interrupt)
		new ThreadTest14();
		
		// 원주율 계산
		new ThreadTest15();
		
		// 쓰레드 동기화
		new ThreadTest16();
		new ThreadTest17();
		new ThreadTest18(); // --> 동기화된 컬렉션
		
		// 쓰레드 정지 및 실행(Object의 wait()와 notify())
		new ThreadTest19();
		new ThreadTest20();
	}

}
