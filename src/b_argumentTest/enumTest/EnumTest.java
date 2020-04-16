package b_argumentTest.enumTest;
/*
 * 	<<열거형>>
 * 	==> 서로 관련있는 상수(final)들의 집합
 * 	==> 클래스처럼 보이게 하는 상수
 * 	==> switch문에서 사용 가능
 * 	==> '=='연산자로 비교한다.
 *                
 * - 기본적인 열거형 만들기
 * 	ex)
 *  enum 열거형이름 { 상수명1, 상수명2, ... }
 * 
 * - 각 상수에 데이터가 설정된 열거형 만들기
 * 	ex) 
 * 	enum 열거형이름 {
 * 		상수명1(값1, ...),
 * 		상수명2(값2, ...),
 * 		...
 * 
 * 		// 각 상수에 데이터를 셋팅하는 생성자를 만든다.
 * 		열거형이름(변수들...){
 * 			변수1 = 값;
 * 			...
 * 		}
 * 
 * 		// 값을 반환하는 메서드 작성(getter)
 * 		반환값자료형 메서드명(){
 * 			return 변수;
 * 		}
 * 	}
 */                                                 
public class EnumTest {                             
                                 
	// 색깔 관련 상수
//	final int RED = 1;                              
//	final int BLUE = 2;                             
//	final int GREEN = 3;                            
	                                                
	// 모양 관련 상수
//	final int TRIANGLE = 1;
//	final int RECTANGLE = 2;
	
	// City 열거형 객체 선언
	// 순서값(ordinal)은 0번부터 
	public enum City { 서울, 부산, 대구, 광주, 대전 }
	
	public enum Season {
		봄("3월부터 5월까지"),
		여름("6월부터 8월까지"),
		가을("9월부터 11월까지"),
		겨울("12월부터 2월까지");
		
		// 데이터가 저장될 변수 선언
		private String span;
		
		// 열거형의 생성자는 private으로 만들어야 하는데 
		// 생략하면 기본값이 private으로 되어 있다. 
		Season(String months){ // private Season(String months){ 와 동일
			// 열거형의 데이터를 지정된 변수에 저장한다.
			span = months;
		}

		// 데이터를 반환하는 메서드
		public String getSpan(){
			return span;
		}
		
	
	}
	
	public static void main(String[] args) {        
		
		// <<색깔, 모양 관련 상수 사용>>
//		EnumTest test = new EnumTest();
//		if(test.RED == test.TRIANGLE){
//			
//		}
		
		
		// <<City 열거형 객체 사용>>
		/*
		 * 열거형 관련 메서드
		 * 1. name() => 열거형 상수의 이름을 문자열로 반환한다.
		 * 2. ordinal() => 열거형 상수가 정의된 순서값을 반환한다.
		 * 3. valueOf("열거형상수명") => 지정된 '열거형상수명'과 일치하는 열거형 상수를 반환한다.
		 */
		
		// City 열거형에서 '서울' 가져오기
		City city1 = City.valueOf("서울"); // 방법1
		System.out.println("city1의 name: " + city1.name());
		System.out.println("city1의 ordinal: " + city1.ordinal());
		System.out.println();
		
		// City 열거형에서 '대구' 가져오기
		City city2 = City.대구; // 방법2
		System.out.println("city2의 name: " + city2.name());
		System.out.println("city2의 ordinal: " + city2.ordinal());
		System.out.println();
		
		System.out.println("=================================\n");
		
		Season ss = Season.valueOf("봄");
		System.out.println("ss의 name: " + ss.name());
		System.out.println("ss의 ordinal: " + ss.ordinal());
		System.out.println("ss의 span: " + ss.getSpan());
		System.out.println();
		
		System.out.println("=================================\n");
				
		// 열거형명.values()메서드 ==> 열거형에 설정된 상수들을 배열로 가져온다.
		for(Season time : Season.values()){
			System.out.println("name: " + time.name());
			System.out.println("ordinal: " + time.ordinal());
			System.out.println("span: " + time.getSpan());
			System.out.println();
		}
		
		System.out.println("=================================\n");
		
//		if(City.서울 == Season.봄){ // 다른 타입의 열거형은 에러발생
		if(City.서울 == City.대전){
			
		}
	}                                               
                                                    
}                                                   
                                                    