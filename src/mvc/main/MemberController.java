package mvc.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mvc.service.IMemberService;
import mvc.service.MemberServiceImpl;
import mvc.vo.MemberVO;

public class MemberController {

	private Scanner sc;
	private IMemberService service;
	
	public MemberController() {
		sc = new Scanner(System.in);
		service = new MemberServiceImpl();
		
	}

	public static void main(String[] args) {
		new MemberController().menu();
	}
	
	void menu() {
		while(true) {
			System.out.println("[MYMEMBER 관리]");
			System.out.println("================");
			System.out.println("1. 자료 추가");
			System.out.println("2. 자료 삭제");
			System.out.println("3. 자료 수정");
			System.out.println("4. 전체 출력");
			System.out.println("0. 종료");
			System.out.println("================");
			System.out.print(" > ");
			String menu = sc.nextLine();
			switch(menu) {
				case "1":
					memInsert();
					break;
				case "2":
					memDelete();
					break;
				case "3":
					memUpdate();
					break;
				case "4":
					memPrintAll();
					break;
				case "0":
					return;
			}
		}
	}

	private void memInsert() {
		
		// 입력받은 값들이 저장될 변수
		String id = "";
		String name = "";
		String tel = "";
		String addr = "";
		
		System.out.println("메뉴로 돌아가려면 '-1'을 입력하세요.");
		// id
		int count = 0;
		do {
			System.out.print("ID: ");
			id = sc.nextLine();
			if(id.equals("-1")) return;
			count = service.getMemberCount(id);
			if(count == 1) {
				System.out.println("\"" + id + "\"는 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while(count == 1);
		
		// name
		System.out.print("NAME: ");
		name = sc.nextLine();
		if(name.equals("-1")) return;
		
		// tel
		System.out.print("TEL: ");
		tel = sc.nextLine();
		if(tel.equals("-1")) return;
		
		// addr
		System.out.print("ADDR: ");
		addr = sc.nextLine();
		if(addr.equals("-1")) return;
		
		// -----------------------------------------------------------------
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);
		
		int cnt = service.insertMember(memVo);
		
		if(cnt > 0) {
			System.out.println("추가성공");
		} else {
			System.out.println("추가실패");
		}
		
	}

	private void memDelete() {
		int count = 1;
		String id = "";
		do {
			memPrintAll();
			System.out.println("삭제할 아이디를 입력하세요.(메뉴로 돌아가려면 '-1'을 입력하세요.)");
			System.out.print("ID: ");
			id = sc.nextLine();
			if(id.equals("-1")) return;
			count = service.getMemberCount(id);
			if(count == 0) {
				System.out.println("\"" + id + "\"는 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while(count == 0);
		
		int cnt = service.deleteMember(id);
					
		if(cnt > 0) {
			System.out.println("삭제에 성공했습니다.");
		} else {
			System.out.println("삭제에 실패했습니다.");
		}
			
	}

	private void memUpdate() {
		int count = 1;
		String id = "";
		
		// 아이디 입력받기 --------------------------------
		
		do {
			memPrintAll();
			System.out.println("정보를 수정할 아이디를 입력하세요.(메뉴로 돌아가려면 '-1'을 입력하세요.)");
			System.out.print("ID: ");
			id = sc.nextLine();
			if(id.equals("-1")) return;
			count = service.getMemberCount(id);
			if(count == 0) {
				System.out.println("\"" + id + "\"는 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while(count == 0);
		
		System.out.println(" 수정할 정보를 입력하세요. (메뉴로 돌아가려면 \"-1\"를 입력하세요.)");
		System.out.println("1. 이름");
		System.out.println("2. 전화번호");
		System.out.println("3. 주소");
		System.out.print("> ");
		String num = sc.nextLine();
		
		// 변경될 데이터 입력받기 ----------------------------------
		
		String fieldName = "";
		String editData = "";
		
		switch(num) {
			case "1": // name
				System.out.println("메뉴로 돌아가려면 \"-1\"를 입력하세요.");
				System.out.print("변경할 이름: ");
				editData = sc.nextLine();
				if(editData.equals("-1")) return;
				fieldName = "MEM_NAME";
				break;
			case "2": // tel
				System.out.println("메뉴로 돌아가려면 \"-1\"를 입력하세요.");
				System.out.print("변경할 전화번호: ");
				editData = sc.nextLine();
				if(editData.equals("-1")) return;
				fieldName = "MEM_TEL";
				break;
			case "3": // addr
				System.out.println("메뉴로 돌아가려면 \"-1\"를 입력하세요.");
				System.out.print("변경할 주소: ");
				editData = sc.nextLine();
				if(editData.equals("-1")) return;
				fieldName = "MEM_ADDR";
				break;
		}
		
		// fieldName: 필드명
		// editData: 값
		// memId: 회원ID
		Map<String, String> params = new HashMap<>();
		params.put("memId", id);
		params.put("fieldName", fieldName);
		params.put("editData", editData);
		
		int cnt = service.updateMember(params);;
		
		if(cnt > 0) {
			System.out.println("변경에 성공했습니다.");
		} else {
			System.out.println("변경에 실패했습니다.");
		}
		
	}
	
	private void memPrintAll() {
		
		List<MemberVO> memList = service.getAllMember();
		
		System.out.println("==========================================================================");
		System.out.printf(" %-15s %-15s %-20s %-15s\n",
				"MEM_ID", "MEM_NAME", "MEM_TEL", "MEM_ADDR");
		System.out.println("==========================================================================");
		if(memList == null || memList.size() == 0) {
			System.out.println(" 입력된 데이터가 없습니다.");
		} else {
			for(MemberVO memVo : memList) {
				System.out.printf(" %-15s %-15s %-20s %-15s\n", 
						memVo.getMem_id(), memVo.getMem_name(),
						memVo.getMem_tel(), memVo.getMem_addr());
			}
		}
		System.out.println("==========================================================================");
		
	}

}
