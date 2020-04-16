package basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MemberIbatisTest {
	/*
	 	iBatis를 이용하여 DB자료를 처리하는 예제
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			// 작업 순서
			// 1. iBatis의 환경 설정 파일(sqlMapConfig.xml)을 읽어와 실행 시킨다.

			// 1-1. 문자 인코딩 캐릭터셋 설정
			Charset charset = Charset.forName("utf-8");
			Resources.setCharset(charset);
			
			// 1-2. xml문서 읽어오기
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			// 1-3. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 수행할 SqlMapClient객체를 생성한다.
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close(); // Reader객체 닫기
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
			
// ===================================================================================
			
			// 2.1. insert 작업
//			System.out.println("insert 작업 시작...");
//			
//			// insert할 데이터 입력 받기
//			System.out.println("저장할 회원 정보를 입력하세요.");
//			System.out.print("아이디: ");
//			String memId = sc.nextLine();
//			
//			System.out.print("이름: ");
//			String memName = sc.nextLine();
//			
//			System.out.print("전화번호: ");
//			String memTel = sc.nextLine();
//			
//			System.out.print("주소: ");
//			String memAddr = sc.nextLine();
//			
//			// 입력한 데이터들을 VO에 담는다.
//			MemberVO memVo = new MemberVO();
//			memVo.setMem_id(memId);
//			memVo.setMem_name(memName);
//			memVo.setMem_tel(memTel);
//			memVo.setMem_addr(memAddr);
//			
//			// 위에서 생성해 놓은 SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
//			// 형식) 객체변수.insert("namespace값.id속성값", 파라미터클래스);
//			//		반환값 ==> 성공: null, 실패: 오류 객체
//			Object obj = smc.insert("myMemberTest.insertMember", memVo);
//			if(obj == null) {
//				System.out.println("insert 작업 성공");
//			} else {
//				System.out.println("insert 작업 실패");
//			}
			
// ===================================================================================
			
			// 2.2 update 작업
//			System.out.println("update 작업 시작...");
//			
//			System.out.println("수정할 회원 정보를 입력하세요.");
//			System.out.print("아이디: ");
//			String memId = sc.nextLine();
//			
//			System.out.print("이름: ");
//			String memName = sc.nextLine();
//			
//			System.out.print("전화번호: ");
//			String memTel = sc.nextLine();
//			
//			System.out.print("주소: ");
//			String memAddr = sc.nextLine();
//			
//			// 입력한 데이터들을 VO에 담는다.
//			MemberVO memVo = new MemberVO();
//			memVo.setMem_id(memId);
//			memVo.setMem_name(memName);
//			memVo.setMem_tel(memTel);
//			memVo.setMem_addr(memAddr);
//			
//			// 위에서 생성해 놓은 SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
//			// 형식) 객체변수.update("namespace값.id속성값", 파라미터클래스);
//			//		반환값 ==> 성공한 레코드 수
//			int cnt = smc.update("myMemberTest.updateMember", memVo);
//			if(cnt > 0) {
//				System.out.println("update 작업 성공");
//			} else {
//				System.out.println("update 작업 실패");
//			}
			
// ===================================================================================
			
//			// 2.3 delete 작업
//			System.out.println("delete 작업 시작...");
//			
//			System.out.println("삭제할 회원 정보를 입력하세요.");
//			System.out.print("아이디: ");
//			String memId = sc.nextLine();
//			
//			// 위에서 생성해 놓은 SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
//			// 형식) 객체변수.delete("namespace값.id속성값", 파라미터클래스);
//			//		반환값 ==> 성공한 레코드 수
//			int cnt = smc.delete("myMemberTest.deleteMember", memId);
//			if(cnt > 0) {
//				System.out.println("delete 작업 성공");
//			} else {
//				System.out.println("delete 작업 실패");
//			}

// ===================================================================================
			
			// 2.4 select 작업
			
			// 1) select한 응답 결과가 여러개일 경우
//			System.out.println("select 작업 시작...(결과가 여러개)");
//						
//			// 위에서 생성해 놓은 SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
//			// 형식) 객체변수.queryForObject("namespace값.id속성값", 파라미터클래스);
//			//		반환값 ==> List객체
//			// 	==> select한 결과가 여러개이면 레코드 하나 하나는 VO에 담고
//			//		이 VO를 List에 담아서 반환한다.
//			List<MemberVO> memList = smc.queryForList("myMemberTest.getMemberAll");
//			
//			// 결과 출력하기
//			System.out.println("---------------------------------");
//			for(MemberVO memVo : memList) {
//				System.out.println(" ＊ 　아이디: " + memVo.getMem_id());
//				System.out.println(" ＊ 　　이름: " + memVo.getMem_name());
//				System.out.println(" ＊ 전화번호: " + memVo.getMem_tel());
//				System.out.println(" ＊ 　　주소: " + memVo.getMem_addr());
//				System.out.println("---------------------------------");
//			}
			
			// ----------------------------------------------------------------------
			
			// 2) select한 응답 결과가 한개일 경우
			System.out.println("select 작업 시작...(결과가 한개)");
			
			System.out.println("검색할 회원 아이디를 입력하세요.");
			System.out.print("아이디: ");
			String memId = sc.nextLine();
			
			// 위에서 생성해 놓은 SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
			// 형식) 객체변수.queryForObject("namespace값.id속성값", 파라미터클래스);
			//		반환값 ==> VO객체 하나 또는 자바의 자료형 값 1개
			MemberVO memVo = (MemberVO)smc.queryForObject("myMemberTest.getMember", memId);
			
			if(memVo != null) {
				System.out.println("---------------------------------");
				System.out.println(" ＊ 　아이디: " + memVo.getMem_id());
				System.out.println(" ＊ 　　이름: " + memVo.getMem_name());
				System.out.println(" ＊ 전화번호: " + memVo.getMem_tel());
				System.out.println(" ＊ 　　주소: " + memVo.getMem_addr());
				System.out.println("---------------------------------");
			} else {
				System.out.println("검색 실패");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}











