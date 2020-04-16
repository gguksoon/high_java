package student.service;

import java.util.List;

import student.vo.StudentVO;

public interface IStudentService {

	/**
	 * StudentVO데이터를 매개변수로 받아서 DB에 저장하는 메서드
	 * @param stdVo DB에 Insert할 StudentVO
	 * @return 성공:1, 실패:0
	 */
	public int insertStudent(StudentVO stdVo);
	
	
	/** mystudent테이블의 전체 데이터를 가져오는 메서드
	 * @return 전체 StudentVO를 가진 List
	 */
	public List<StudentVO> getAllStudent();
}
