package a_collectionTest;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
	이름, 주소, 전화번호 속성을 갖는 Phone클래스를 만든다.
	이 Phone클래스를 이용하여 전화번호 정보를 관리하는 프로그램을 완성하시오.
	이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체 출력하는 기능이 있다.
	* 전화번호 수정은 이름으로만 가능
	* 전화번호 삭제는 이름으로만 가능
	* 전화번호 검색은 이름으로만 가능
	그리고, 전체 전화번호 정보는 Map을 이용하여 관리한다.
	(key는 이름으로 하고 value는 Phone클래스의 인스턴스로 한다.)
	
	실행예시)
	===============================
			전화번호 관리 프로그램
	===============================
	
	메뉴를 선택하세요.
	1. 전화번호 등록
	2. 전화번호 수정
	3. 전화번호 삭제
	4. 전화번호 검색
	5. 전화번호 전체 출력
	0. 프로그램 종료
	번호 입력 >> 1 <-- 직접 입력
	
	새롭게 등록할 전화번호 정보를 입력하세요.
	이 름 >> 홍길동 <-- 직접 입력
	전화번호 >> 010-1212-3434 <-- 직접 입력
	주 소 >> 대전시  <-- 직접 입력
	홍길동씨의 전화번호가 등록되었습니다. 
	
	===============================
			전화번호 관리 프로그램
	===============================
	
	메뉴를 선택하세요.
	1. 전화번호 등록
	2. 전화번호 수정
	3. 전화번호 삭제
	4. 전화번호 검색
	5. 전화번호 전체 출력
	0. 프로그램 종료
	번호 입력 >> 5 <-- 직접 입력
	
	--------------------------------------
	번호		이름		전화번호			주소
	 1		홍길동	010-1212-3434	대전시
	 .		.		.				.
	 .		.		.				.
	 .		.		.				.
	 .		.		.				.
	 --------------------------------------
	 
	===============================
			전화번호 관리 프로그램
	===============================
	
	메뉴를 선택하세요.
	1. 전화번호 등록
	2. 전화번호 수정
	3. 전화번호 삭제
	4. 전화번호 검색
	5. 전화번호 전체 출력
	0. 프로그램 종료
	번호 입력 >> 0 <-- 직접 입력
	
	프로그램을 종료합니다.
*/
public class PhoneBookTest {

	// 이름 / (전화번호, 주소) 가 저장될 HashMap
	private HashMap<String, Phone> phoneBook = new HashMap<String, Phone>();
	private Scanner sc = new Scanner(System.in);
	
	{
		phoneBook.put("조민수", new Phone("010-3092-3837", "대흥동"));
		phoneBook.put("강승구", new Phone("010-1234-5678", "대흥동"));
		phoneBook.put("박은지", new Phone("010-5678-1234", "대흥동"));
	}
	
	public static void main(String[] args) {
		
		PhoneBookTest pbt = new PhoneBookTest();
		pbt.menu();
	}

	private void menu(){
		
		while(true){
			printMargin();
			System.out.println("===============================");
			System.out.println("	전화번호 관리 프로그램");
			System.out.println("===============================");
			System.out.println(" [1] 전화번호 등록");
			System.out.println(" [2] 전화번호 수정");
			System.out.println(" [3] 전화번호 삭제");
			System.out.println(" [4] 전화번호 검색");
			System.out.println(" [5] 전화번호 전체 출력");
			System.out.println(" [0] 프로그램 종료");
			System.out.println("===============================");
			System.out.print(" > 번호 입력: ");
			String inputNum = sc.nextLine();
			switch(inputNum){
				case "0": // 프로그램 종료
					System.out.println("\n > 프로그램을 종료합니다.");
					return;
				case "1": // 등록
					createPhone();
					break;
				case "2": // 수정
					modifyPhone();
					break;
				case "3": // 삭제
					deletePhone();
					break;
				case "4": // 검색
					searchPhone();
					break;
				case "5": // 전체 출력
					printPhoneBook();
					break;
			}
		}
	}
	// 전화번호 등록 메서드
	private void createPhone() {
		
		String name = "";
		String phone = "";
		String addr = "";
		
		boolean flag = false;
		
		// 이름 입력(키 중복 확인)
		while(true){
			printMargin();
			if(flag){
				System.out.println("\n > 이미 해당 이름이 존재합니다. 다시 입력하세요.");
				flag = false;
			}
			System.out.println(" > 전 단계로 돌아가시려면 '0'을 입력하세요.");
			System.out.print(" > 이름: ");
			name = sc.nextLine();
			if(name.equals("0"))
				return;
			if(phoneBook.containsKey(name)){ // 이름이 존재하는 경우
				flag = true;
			} else { // 이름이 존재하지 않는 경우
				break;
			}
		}
		
		// 전화번호 입력(정규식 확인)
		while(true){
			System.out.println();
			if(flag){
				System.out.println(" > 양식에 맞지 않습니다. 다시 입력하세요.");
				flag = false;
			}
			System.out.println(" > 전 단계로 돌아가시려면 '0'을 입력하세요.");
			System.out.println(" > 전화번호는 XXX-XXXX-XXXX 형식으로 입력하세요.");
			System.out.print(" > 전화번호: ");
			phone = sc.nextLine();
			if(phone.equals("0"))
				return;
			String pattern = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
			if(Pattern.matches(pattern, phone)){ // 정규식 맞음
				break;
			} else { // 정규식 틀림
				flag = true;
			}
		}
		
		// 주소 입력
		System.out.println("\n > 전 단계로 돌아가시려면 '0'을 입력하세요.");
		System.out.print(" > 주소: ");
		addr = sc.nextLine();
		if(addr.equals("0"))
			return;
		// 객체 생성 및 데이터 삽입
		phoneBook.put(name, new Phone(phone, addr));
		System.out.println("\n > 추가완료");
		System.out.print(" > 메뉴로 돌아가시려면 아무키나 입력하세요.: ");
		sc.nextLine();
	}
	
	// 전화번호 수정 메서드
	private void modifyPhone() {
		boolean flag = false;
		while(true){
			printMargin();
			if(flag){
				System.out.println(" > 해당하는 이름이 없습니다. 다시 입력하세요.");
				flag = false;
			}
			System.out.println(" > 정보를 수정할 사람의 이름을 입력하세요.");
			System.out.println("   (메뉴로 돌아가시려면 '0'을 입력하세요.)");
			System.out.print("\n > ");
			String inputName = sc.nextLine();
			if(inputName.equals("0")){
				return;
			} 
			
			if(phoneBook.containsKey(inputName)){
				while(true){
					printMargin();
					printPhone(inputName);
					System.out.println(" [1] 이름");
					System.out.println(" [2] 전화번호");
					System.out.println(" [3] 주소");
					System.out.println(" [0] 메인화면으로");
					System.out.print(" > 변경할 정보를 선택하세요.: ");
					String menu = sc.nextLine();
					switch(menu){
						case "0":
							return;
						case "1":
							while(true){
								System.out.print(" > 이름: ");
								String name = sc.nextLine();
								if(phoneBook.containsKey(name)){ // 이름이 존재하는 경우
									System.out.println("\n > 이미 해당 이름이 존재합니다. 다시 입력하세요.");
								} else { // 이름이 존재하지 않는 경우
									phoneBook.put(name, phoneBook.get(inputName));
									phoneBook.remove(inputName);
									printMargin();
									printPhone(name);
									System.out.println(" > 변경되었습니다.");
									System.out.print(" > 메뉴로 돌아가시려면 아무키나 입력하세요.: ");
									sc.nextLine();
									return;
								}
							}
						case "2":
							while(true){
								System.out.println(" > 전화번호는 XXX-XXXX-XXXX 형식으로 입력하세요.");
								System.out.print(" > 전화번호: ");
								String phone = sc.nextLine();
								String pattern = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
								if(Pattern.matches(pattern, phone)){ // 정규식 맞음
									phoneBook.get(inputName).setPhone(phone);
									printMargin();
									printPhone(inputName);
									System.out.println(" > 변경되었습니다.");
									System.out.print(" > 메뉴로 돌아가시려면 아무키나 입력하세요.: ");
									sc.nextLine();
									return;
								} else { // 정규식 틀림
									System.out.println(" > 양식에 맞지 않습니다. 다시 입력하세요.");
								}
							}
						case "3":
							System.out.print(" > 주소: ");
							String addr = sc.nextLine();
							phoneBook.get(inputName).setAddr(addr);
							printMargin();
							printPhone(inputName);
							System.out.println(" > 변경되었습니다.");
							System.out.print(" > 메뉴로 돌아가시려면 아무키나 입력하세요.: ");
							sc.nextLine();
							return;
					}
				}
				
			} else {
				flag = true;
			}
			
		}
	}
	
	// 전화번호 삭제 메서드
	private void deletePhone() {
		boolean flag = false;
		while(true){
			printMargin();
			if(flag){
				System.out.println(" > 해당하는 이름이 없습니다. 다시 입력하세요.");
				flag = false;
			}
			System.out.println(" > 삭제할 사람의 이름을 입력하세요.");
			System.out.println("   (메뉴로 돌아가시려면 '0'을 입력하세요.)");
			System.out.print("\n > ");
			String inputName = sc.nextLine();
			if(inputName.equals("0")){
				return;
			} 
			if(phoneBook.containsKey(inputName)){
				System.out.println("\n > 삭제되었습니다. 메뉴로 돌아가시려면 아무키나 입력하세요.");
				phoneBook.remove(inputName);
				sc.nextLine();
				break;
			} else {
				flag = true;
			}
		}
	}
	
	// 전화번호 검색 메서드
	private void searchPhone() {
		boolean flag = false;
		while(true){
			printMargin();
			System.out.println("[전화번호 검색]\n");
			if(flag){
				System.out.println(" > 해당하는 이름이 없습니다. 다시 입력하세요.");
				flag = false;
			}
			System.out.println(" > 검색할 사람의 이름을 입력하세요.");
			System.out.println("   (메뉴로 돌아가시려면 '0'을 입력하세요.)");
			System.out.print("\n > ");
			String inputName = sc.nextLine();
			if(inputName.equals("0")){
				return;
			} 
			if(phoneBook.containsKey(inputName)){
				printMargin();
				printPhone(inputName);
				System.out.print(" > 전 단계로 가시려면 아무키나 입력하세요.: ");
				sc.nextLine();
				break;
			} else {
				flag = true;
			}
		}
			
	}
	
	// 전화번호 전체 출력 메서드
	private void printPhoneBook() {
		printMargin();
		int num = 1;
		
		System.out.println("[전화번호 전체 출력]");
		System.out.println("========================================");
		System.out.println("번호\t이름\t전화번호\t\t주소");
		System.out.println("========================================");
		for(String key : phoneBook.keySet()){
			System.out.println(num++ + "\t" + key + "\t" + phoneBook.get(key).getPhone() + "\t" +
								phoneBook.get(key).getAddr());
		}
		System.out.println("========================================");
		System.out.print(" > 전 단계로 가시려면 아무키나 입력하세요.: ");
		sc.nextLine();
	}
	
	// 전화번호 한명 출력 메서드
	private void printPhone(String key) {
		System.out.println("===============================");
		System.out.println("＊이　　름: " + key);
		System.out.println("＊전화번호: " + phoneBook.get(key).getPhone());
		System.out.println("＊주　　소: " + phoneBook.get(key).getAddr());
		System.out.println("===============================");
	}
	
	private void printMargin(){
		for(int i = 0; i < 40; i++){
			System.out.println();
		}
	}




}

class Phone{
	private String phone;
	private String addr;
	
	public Phone(String phone, String addr) {
		super();
		this.phone = phone;
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
