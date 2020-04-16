package board.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;

public class BoardController {

	private Scanner sc;
	private IBoardService service;
	
	public BoardController() {
		sc = new Scanner(System.in);
		service = new BoardServiceImpl();
	}
	
	public static void main(String[] args) {
		new BoardController().startMenu();
	}
	
	private void startMenu() {
		
		while(true) {
			printMargin();
			printAllBoard(service.getAllBoard()); // 게시판 전체를 불러와서 출력
			String menu = "";
			System.out.println("\n[MENU]===========================");
			System.out.println("[1]새글작성  [2]게시글보기  [3]검색  [0]종료");
			System.out.println("=================================");
			System.out.print("> ");
			menu = sc.nextLine();
			switch(menu) {
				case "1":
					add();
					break;
				case "2":
					show();
					break;
				case "3":
					search();
					break;
				case "0":
					return;
			}
		} 
	}

	// case 1: 새글작성
	private void add() {
		
		// 메뉴 출력 및 정보 입력
		printMargin();
		System.out.println("[새글작성]");
		System.out.println("==================================");
		System.out.print("> 제　목: ");
		String title = sc.nextLine();
		System.out.print("> 작성자: ");
		String writer = sc.nextLine();
		System.out.print("> 내　용: ");
		String content = sc.nextLine();
		System.out.println("==================================");
		
		// 입력받은 정보를 객체에 저장
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		// 객체를 insert
		int cnt = service.insertBoard(boardVo);
		
		// 결과
		if(cnt > 0) System.out.println("> 새글 작성 성공!");
		else System.out.println("> 새글 작성 실패!");
		
		System.out.println(" ('ENTER'를 입력하면 메뉴로 돌아갑니다.)");
		sc.nextLine();
	}

	// case 2: 게시글보기
	private void show() {
		while(true) {
			printMargin();
			System.out.println("[게시글보기]");
			printAllBoard(service.getAllBoard()); // 게시판 전체를 불러와서 출력
			
			System.out.println("> 게시글 번호를 입력하세요.");
			System.out.println(" ('0'을 입력하면 전 단계로 돌아갑니다.)");
			System.out.print("> ");
			int inputNum = Integer.parseInt(sc.nextLine());
			
			if(inputNum == 0) return;
			
			BoardVO board = service.getBoard(inputNum);
						
			if(board != null) {	
				modify(inputNum);
			}
		} 
	}
	
	// case 3: 검색
	private void search() {
		printMargin();
		System.out.println("[검색]");
		
		System.out.println("> 게시글의 제목을 입력하세요.");
		System.out.println(" ('0'을 입력하면 전 단계로 돌아갑니다.)");
		System.out.print("> ");
		String inputTitle = sc.nextLine();
		
		if(inputTitle.equals("0")) {
			return;
		} else {
			while(true) {
				printMargin();
				System.out.println("[검색결과]");
				List<BoardVO> boardList = service.searchBoard(inputTitle);
				
				
				
				printAllBoard(boardList);
				
				if(boardList.size() == 0 || boardList == null) {
					System.out.println("> '0'을 입력하면 전 단계로 돌아갑니다.");
				} else {
					System.out.println("> 게시글 번호를 입력하세요.");
					System.out.println(" ('0'을 입력하면 전 단계로 돌아갑니다.)");
				}
				System.out.print("> ");
				int inputNum = Integer.parseInt(sc.nextLine());
				
				if(inputNum == 0) return;
				
				boolean flag = false;
				for(BoardVO board : boardList) {
					if(board.getBoard_no() == inputNum) {
						flag = true;
						break;
					}
						
				}
				
				if(flag) {
					BoardVO board = service.getBoard(inputNum);
								
					if(board != null) {	
						modify(inputNum);
					}
				}
			}
		}
		
	}
	
	private void modify(int id) {
		service.increaseCnt("" + id); // 조회수 늘리기
		BoardVO board = service.getBoard(id); // 객체 갱신
		while(true) {
			printMargin();
			printBoard(board);
			String menu = "";
			System.out.println("\n[MENU]===========================");
			System.out.println("[1]수정하기  [2]삭제하기  [0]돌아가기");
			System.out.println("=================================");
			System.out.print("> ");
			menu = sc.nextLine();
			if(menu.equals("1")) {
				// 입력받기
				printMargin();
				System.out.println("[수정하기]");
				System.out.println("==================================");
				System.out.print("> 제　목: ");
				String title = sc.nextLine();
				System.out.print("> 내　용: ");
				String content = sc.nextLine();
				System.out.println("==================================");
				
				// 수정하기
				Map<String, String> params = new HashMap<>();
				params.put("boardNo", "" + id);
				params.put("fieldName", "BOARD_TITLE");
				params.put("editData", title);
				service.updateBoard(params); // title 수정
				
				Map<String, String> params2 = new HashMap<>();
				params2.put("boardNo", "" + id);
				params2.put("fieldName", "BOARD_CONTENT");
				params2.put("editData", content);
				service.updateBoard(params2); // content 수정
				
				board = service.getBoard(id); // 객체 갱신
				
				System.out.println("> 수정되었습니다.");
				System.out.println(" ('ENTER'를 입력하면 메뉴로 돌아갑니다.)");
				sc.nextLine();
			} else if(menu.equals("2")) {
				service.deleteBoard("" + id);
				System.out.println("> 삭제되었습니다.");
				System.out.println(" ('ENTER'를 입력하면 메뉴로 돌아갑니다.)");
				sc.nextLine();
				break;
			} else if(menu.equals("0")) {
				break;
			}
		}
	}

	// 마진을 주기 위한 메서드
	private void printMargin() {
		for(int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
	// 리스트를 출력하는 메서드
	private void printAllBoard(List<BoardVO> boardList) {
		System.out.println("======================================================");
		System.out.printf(" %-5s %-25s %-15s %s\n",
				"NO", "TITLE", "WRITER", "VIEW");
		System.out.println("======================================================");
		if(boardList == null || boardList.size() == 0) {
			System.out.println(" 입력된 데이터가 없습니다.");
		} else {
			for(BoardVO boardVo : boardList) {
				System.out.printf(" %-5s %-25s %-15s %s\n",
						boardVo.getBoard_no(), boardVo.getBoard_title(),
						boardVo.getBoard_writer(), boardVo.getBoard_cnt());
			}
		}
		System.out.println("======================================================");
	}
	
	// 게시글 한개를 출력하는 메서드
	private void printBoard(BoardVO boardVo) {
		System.out.println("[No." + boardVo.getBoard_no() + "]");
		System.out.println("======================================================");
		System.out.println("> 제　목: " + boardVo.getBoard_title());
		System.out.println("> 작성자: " + boardVo.getBoard_writer());
		System.out.println("> 내　용: " + boardVo.getBoard_content());
		System.out.println("> 작성일: " + boardVo.getBoard_date());
		System.out.println("> 조회수: " + boardVo.getBoard_cnt());
		System.out.println("======================================================");
	}

}
