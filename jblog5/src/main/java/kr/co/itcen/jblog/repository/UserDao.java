package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.exception.UserDaoException;
import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	// 회원가입
	public Boolean insert(UserVo userVo) throws UserDaoException {
		int count = sqlSession.insert("user.insert", userVo);
		return count == 1;
	}
	
	// id 중복검사
	public UserVo get(String id) { 
		UserVo result = sqlSession.selectOne("user.getById", id);
		return result;
	}

	public UserVo get(UserVo userVo) {
		UserVo result = sqlSession.selectOne("getByEmailAndPassword", userVo);
		return result;
	}
}
