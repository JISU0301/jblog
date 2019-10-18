package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlSession;
	
	// 카테고리 default(미분류) 생성
	public void defaultCategory(String id) {
		sqlSession.insert("category.defaultCategory",id);
	}
	

	// 카테고리 목록 가져오기
	public List<CategoryVo> getCategoryList(String id) {
		return sqlSession.selectList("category.getCategoryList", id);
	}


	public void add(CategoryVo categoryVo) {
		 sqlSession.insert("category.insert",categoryVo) ;
	}


	public List<CategoryVo> getpostList(String id) {
		return sqlSession.selectList("category.getpostList", id);
	}


	public List<CategoryVo> getCategoryName(String id) {
		return sqlSession.selectList("category.getCategoryName", id);
	}
	



}