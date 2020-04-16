package basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;
/*
 * 아래의 전화번호 관리 프로그램에 저장 기능을 부여한다.
 *  1. 메뉴 추가 ==> 6. 전화번호 저장
 *  2. 프로그램이 실행될 저장된 파일을 읽어와서 사용할 수 있게 한다.
 * 	      만약 저장된 파일이 없으면 처음 작업하는 것으로 처리한다.
 *  3. 프로그램을 종료할 때 데이터가 변경되었으면 저장할지 확인해서 
 * 	      저장하는 기능을 넣는다.
 */


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
	6. 전화번호 저장
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
	
	private String path = "e:/D_Other/"; // 경로
	private String fileName = "/phoneBookObj.bin"; // 파일명
	
	private boolean hasChanged = false; // 저장된 파일이 변경되었는지 체크하는 논리형 변수 
	
	// 최초 초기화용 블럭
//	{
//		phoneBook.put("조민수", new Phone("조민수", "010-3092-3837", "대흥동"));
//		phoneBook.put("강승구", new Phone("강승구", "010-1234-5678", "대흥동"));
//		phoneBook.put("박은지", new Phone("박은지", "010-5678-1234", "대흥동"));
//	}
	
	public static void main(String[] args) {
		
		new PhoneBookTest().menu();
	}

	private void menu(){
		
		// 최초 실행 시 전화번호 불러오기
		// 경로: "e:/D_Other/phoneBookObj.bin"
		loadPhoneBook();
		
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
			System.out.println(" [6] 전화번호 저장");
			System.out.println(" [0] 프로그램 종료");
			System.out.println("===============================");
			System.out.print(" > 번호 입력: ");
			String inputNum = sc.nextLine();
			switch(inputNum){
				case "0": // 프로그램 종료
					exitProgram();
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
				case "6": // 전화번호 저장
					savePhoneBook();
					break;
			}
		}
	}
	
	private void exitProgram() {
		
		if(hasChanged) { // true: 변경되었을 경우
			String saveCheck = "";
			boolean flag = false;
			while(true) {
				System.out.println();
				if(flag) {
					System.out.println(" > 잘못 입력하셨습니다. 다시 입력하세요.");
					flag = false;
				}
				System.out.println(" > 변경된 사항이 있습니다. 저장하고 종료하시겠습니까?(Y/N)");
				System.out.print(" > ");
				saveCheck = sc.nextLine();
				if(saveCheck.equals("y") || saveCheck.equals("Y")) {
					savePhoneBook();
					break;
				} else if(saveCheck.equals("n") || saveCheck.equals("N")) {
					break;
				} else {
					flag = true;
				}
			}
		} 
		
		System.out.println("\n > 프로그램을 종료합니다.");
	}
	
	// 전화번호 저장 메서드
	private void savePhoneBook() {
		
		File Folder = new File(path);

		// 해당 디렉토리가 없을경우 디렉토리를 생성
		if (!Folder.exists()) {
			try {
				Folder.mkdirs(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} 
		
		ObjectOutputStream oos = null;
		try {
			// 출력용 스트림 -> 파일에 쓰기
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
						new FileOutputStream(path + fileName)
					)
			);
			
			for(String key : phoneBook.keySet()) {
				oos.writeObject(phoneBook.get(key));
			}
			hasChanged = false; // 저장되었기에 false로 바꿔준다.
			System.out.println(" > 저장되었습니다.");
			
		} catch(IOException e ) {
			System.out.println("입출력에러");
		} finally {
			if(oos != null)
				try { oos.close(); } catch(IOException e) { }
		}
	}
	
	// 전화번호 불러오기 메서드
	private void loadPhoneBook() {
		ObjectInputStream ois = null;
		try {
			// 입력용 스트림 -> 파일 불러오기
			ois = new ObjectInputStream(
				new BufferedInputStream(
					new FileInputStream(path + fileName)
				)
			);
			
			// 읽어온 객체를 저장할 변수
			Object obj = null;
			
			while( (obj = ois.readObject()) != null ) {
				Phone phone = (Phone)obj;
				phoneBook.put(phone.getName(), phone);
			}
			System.out.println("읽어오기 완료");
			
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			try { ois.close(); } catch (IOException e) { }
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
		phoneBook.put(name, new Phone(name, phone, addr));
		hasChanged = true;
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
									hasChanged = true;
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
									hasChanged = true;
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
							hasChanged = true;
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
				hasChanged = true;
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

class Phone implements Serializable{
	private static final long serialVersionUID = 1432605378974552445L;
	
	private String name;
	private String phone;
	private String addr;
	
	public Phone(String name, String phone, String addr) {
		this.name = name;
		this.phone = phone;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
