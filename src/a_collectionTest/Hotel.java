package a_collectionTest;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Hotel {

	TreeMap<Integer, Room> hotel = new TreeMap<Integer, Room>();
	Scanner sc = new Scanner(System.in);
	
	Hotel() { // 생성자
		for(int i = 2; i <= 4; i++) {
			for(int j = 1; j <= 9; j++) {
				int roomNumber = (i * 100) + j;
				String roomType;
				if(i == 4) {
					roomType = "스위트룸";
				} else if(i == 3) {
					roomType = "더블룸";
				} else {
					roomType = "싱글룸";
				}
				hotel.put(roomNumber, new Room(roomNumber, roomType));
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("\n**********************************************");
		System.out.println("\t　　　호텔문을 열었습니다. 어서오세요.");
		System.out.println("**********************************************");
		
		new Hotel().management(); // 관리메뉴 호출
		
		System.out.println("\n**********************************************");
		System.out.println("\t호텔문을 닫았습니다. 안녕히가세요.");
		System.out.println("**********************************************");
	}
	
	public void management() {
		while(true){
			System.out.println("\n----------------------------------------------");
			System.out.println(" 1. 체크인　　　2. 체크아웃　　　3. 객실상태　　　4. 업무종료");
			System.out.println("----------------------------------------------");
			System.out.println(" >> 어떤 업무를 하시겠습니까?");
			System.out.print("\n > ");
			String inputNumber = sc.nextLine();
			switch(inputNumber){
				case "1":
					checkIn();
					break;
				case "2":
					checkOut();
					break;
				case "3":
					printRoom();
					break;
				case "4":
					return;
			}
		}
	}
	
	public void checkIn() {
		boolean guestFlag = false;
		boolean patternFlag = false;
		String roomNum = "";
		while(true){
			System.out.println("\n[체크인]");
			System.out.println("----------------------------------------------");
			System.out.println(" ＊ 201 ~ 209 : 싱글룸");
			System.out.println(" ＊ 301 ~ 309 : 더블룸");
			System.out.println(" ＊ 401 ~ 309 : 스위트룸");
			System.out.println("----------------------------------------------");
			if(guestFlag){
				System.out.println(" >> " + roomNum + "호 객실은 이미 예약되어 있습니다.");
				guestFlag = false;
			} else if(patternFlag){
				System.out.println(" >> 잘못된 입력입니다.");
				patternFlag = false;
			}
			System.out.println(" >> 방 번호를 입력하세요.(메뉴로 돌아가시려면 '0'을 입력하세요.)");
			System.out.print("\n > ");
			if(roomNum.equals("0")){ 
				return;
			}
			roomNum = sc.nextLine();
			String pattern = "^[2-4]0[1-9]$";
			if(Pattern.matches(pattern, roomNum)){ // 정규식이 옳을 때
				Room room = hotel.get(Integer.parseInt(roomNum));
				if(room.getGuest() == null){ // 방이 비어 있을 때
					System.out.println("\n >> 예약자 성함을 입력해주세요.");
					System.out.print("\n > ");
					String name = sc.nextLine();
					room.setGuest(name);
					System.out.println("\n >> 체크인이 완료되었습니다.");
					return;
				} else { // 방이 예약되어 있을 때
					guestFlag = true;
				}
			} else { // 정규식이 틀릴 때
				patternFlag = true;
			}
		}
	}
	
	public void checkOut() {
		boolean guestFlag = false;
		boolean patternFlag = false;
		String roomNum = "";
		while(true){
			System.out.println("\n[체크아웃]");
			System.out.println("----------------------------------------------");
			System.out.println(" ＊ 201 ~ 209 : 싱글룸");
			System.out.println(" ＊ 301 ~ 309 : 더블룸");
			System.out.println(" ＊ 401 ~ 309 : 스위트룸");
			System.out.println("----------------------------------------------");
			if(guestFlag){
				System.out.println(" >> " + roomNum + "호 객실은 체크인을 한 사람이 없습니다.");
				guestFlag = false;
			} else if(patternFlag){
				System.out.println(" >> 잘못된 입력입니다.");
				patternFlag = false;
			}
			System.out.println(" >> 방 번호를 입력하세요.(메뉴로 돌아가시려면 '0'을 입력하세요.)");
			System.out.print("\n > ");
			if(roomNum.equals("0")){ 
				return;
			}
			roomNum = sc.nextLine();
			String pattern = "^[2-4]0[1-9]$";
			if(Pattern.matches(pattern, roomNum)){ // 정규식이 옳을 때
				Room room = hotel.get(Integer.parseInt(roomNum));
				if(room.getGuest() != null){ // 방이 비어있지 않을 때
					System.out.println("\n >> " + roomNum + "호 객실의 " + room.getGuest()
							+ "님이 체크아웃 되었습니다.");
					room.setGuest(null);
					return;
				} else { // 방이 비어있을 때
					guestFlag = true;
				}
			} else { // 정규식이 틀릴 때
				patternFlag = true;
			}
		}
	}
	
	public void printRoom() {
		System.out.println("\n[객실상태]");
		System.out.println("----------------------------------------------");
		System.out.printf(" %s\t  %s\t\t%s\n", "No", "Type", "Guest");
		System.out.println("----------------------------------------------");
		for(Integer key : hotel.keySet()) {
			System.out.println(hotel.get(key).toString());
		}
		System.out.println("----------------------------------------------");
	}

}

class Room {
	private final int roomNumber;
	private final String roomType;
	private String guest;
	
	public Room(int roomNumber, String roomType) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}

	@Override
	public String toString() {
		String tempGuest = guest;
		if(tempGuest == null)
			tempGuest = "-";
		
		return String.format(" %d\t    %s\t\t%s ", roomNumber, roomType, tempGuest);
	}
	
	
	
}