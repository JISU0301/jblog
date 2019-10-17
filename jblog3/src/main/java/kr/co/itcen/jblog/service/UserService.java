package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.UserDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;

	// 회원가입
	public void join(UserVo userVo) {
		userDao.insert(userVo);

		BlogVo blogVo = new BlogVo();
		blogVo.DefaultBlogSetting(userVo.getId());

		// 회원가입시 자동으로 블로그 생성
		blogDao.createBlog(blogVo);

		// 카테고리 default(미분류) 생성
		categoryDao.defaultCategory(userVo.getId());

	}

	// id 중복검사
	public Boolean existUser(String id) {
		return userDao.get(id) != null;
	}
	
	// 세션 회원정보
	public UserVo getUser(UserVo userVo) {
		return userDao.get(userVo);
	}

}