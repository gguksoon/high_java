package basic;

import javafx.application.Application;
import javafx.stage.Stage;
/*
	- Java의 GUI 프로그램 발전 순서
		AWT ==> SWing ==> javaFx
		
	- window창 ==> Stage객체
	
	- 창에 나타나는 내용들 ==> Scene객체
	
	- 객체들을 포함할 수 있는 것 ==> 컨테이너 객체 ==> 화면의 레이아웃을 담당하는 객체
	
	- 컨트롤 객체 ==> 버튼, 콤보박스, 리스트박스, Label 등...
	
	- javaFx프로그램의 실행 순서
		main() --> launch() --> 현재 객체의 생성자 호출(여기선 JavaFxLifeCycle)
		--> init() --> start() --> 사용 --> 종료 --> stop()
		* init()은 환경설정하는 메서드
		
	- javaFx가 종료되는 경우
	1) 마지막 윈도우(Stage)가 닫힐 때
	2) 마지막 윈도우의 close()메서드가 호출될 때
	3) 자바코드의 Platform.exit()메서드를 호출 했을 때
	4) 자바코드의 System.exit(0)메서드를 호출 했을 때 -->
		* 1) ~ 3) 은 수행 후 stop()을 호출하고
		* 4)는 stop()을 호출하지 않는다.
 */

public class JavaFxLifeCycle extends Application {
	
	// 생성자
	public JavaFxLifeCycle() {
		System.out.println(Thread.currentThread().getName() + "- 생성자 메서드");
	}
	
	@Override
	public void init() throws Exception {
		System.out.println(Thread.currentThread().getName() + "- init 메서드");
	}

	@Override
	public void start(Stage primaryStage) { // Stage가 창
		System.out.println(Thread.currentThread().getName() + "- start 메서드");
		primaryStage.show(); // 창 보이기
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println(Thread.currentThread().getName() + "- stop 메서드");
	}

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + "main()메서드 시작");
		launch(args);
	}
}
