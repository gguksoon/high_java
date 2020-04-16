package b_argumentTest.argument;
/*
 * 메서드에 넣어 줄 데이터가 가변적일 때 매개변수를 정하는 방법
 * 1. 배열이나 List로 매개변수를 만들어서 사용한다.
 * 2. 가변형 인수를 사용한다.
 */
public class ArgsTest {

	// 1. 배열을 사용하는 메서드
	public int sumArr(int[] data){
		int sum = 0;
		for(int i = 0; i < data.length; i++){
			sum += data[i];
		}
		return sum;
	}
	
	// 2. 가변형 인수를 사용하는 메서드
	// 	    주의) 가변형 인수와 일반적인 인수를 같이 사용할 때는 가변형 인수를 제일 마지막에 배치해야 한다.
	//    ex) public int sumArg(String name, int...data){ } 
	public int sumArg(int...data){
		// 가변형 인수를 메서드 내에서 사용할 때는 가변형 인수를 배열로 보고 처리하면 된다.
		int sum = 0;
		for(int i = 0; i < data.length; i++){
			sum += data[i];
		}
		return sum;
	}
	public static void main(String[] args) {
		ArgsTest at = new ArgsTest();
		System.out.println(at.sumArr(new  int[]{5, 9, 10}));
		System.out.println(at.sumArg(10, 30, 70));
	}

}
