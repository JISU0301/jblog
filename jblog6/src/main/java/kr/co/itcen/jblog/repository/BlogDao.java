package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;

	// 기본 블로그 생성
	public Boolean createBlog(BlogVo blogVo) {
		int count = sqlSession.insert("blog.insertBlog", blogVo);
		return count == 1;
	}

	// 블로그 메인 정보
	public BlogVo getInfo(String id) {
		return sqlSession.selectOne("blog.getInfo", id);
	}
	
	// 블로그 정보 수정
	public void update(BlogVo blogVo) {
		sqlSession.update("blog.update", blogVo);
		
	}
	
	


}