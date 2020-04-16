package student.service;

import java.util.List;

import student.dao.IStudentDao;
import student.dao.StudentDaoImpl;
import student.vo.StudentVO;

public class StudentServiceImpl implements IStudentService {

	private static StudentServiceImpl service;
	private IStudentDao dao;
	
	private StudentServiceImpl() {
		dao = StudentDaoImpl.getInstance();
	}
	
	public static StudentServiceImpl getInstance() {
		if(service == null) 
			service = new StudentServiceImpl();
		return service;
	}
	
	@Override
	public int insertStudent(StudentVO stdVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StudentVO> getAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}

}
