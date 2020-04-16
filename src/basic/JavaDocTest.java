package basic;

/**
 * @author 조민수
 * @version 1.0
 * 
 * <p>
 * 파일명: JavaDocTest.java<br>
 * 설명: JavaDoc문서 작성을 위한 연습용 Interface<br>
 * <br>
 * 수정 이력
 * ===================<br>
 * 수정일자: 2019-06-19<br>
 * 수정인: 조민수<br>
 * 수정내용: 최초생성<br>
 * ===================<br>
 * </p> 
 */
public interface JavaDocTest {

	/**
	 * methodTest - 반환값이 없는 메서드
	 * @param a - 첫번째 매개변수(정수형)
	 * @param b - 두번째 매개변수(정수형)
	 */
	public void methodTest(int a, int b);
	
	/**
	 * methodAdd - 두 매개변수의 덧셈 결과를 반환하는 메서드
	 * @param x - 덧셈에 사용할 첫번째 정수값
	 * @param y - 덧셈에 사용할 두번째 정수값
	 * @return - 두 매개변수의 합계를 정수형으로 반환
	 */
	public int methodAdd(int x, int y);
	
	/**
	 * methodSub - 매개변수는 없고 반환값만 있는 메서드
	 * @return - 처리된 결과를 정수형으로 반환한다.
	 */
	public int methodSub();
}


