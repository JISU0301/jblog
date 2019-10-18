package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class PostDao {
	@Autowired
	private SqlSession sqlSession;
	
	// 최근 포스트 내용 가져오기
	public PostVo getPostView(String id) {
		return sqlSession.selectOne("post.getPostViewId", id);
	}
	
	// 해당 카테고리의 최근 포스트 내용 가져오기
	public PostVo getPostView(Long categoryNo) {
		return sqlSession.selectOne("post.getPostViewNo", categoryNo);
	}
	
	// 카테고리의 해당 포스트 목록 가져오기
	public List<PostVo> getPostList(Long categoryNo) {
		return sqlSession.selectList("post.getPostList", categoryNo);
	}


	public void postWrite(PostVo postVo) {
		sqlSession.insert("post.postWrite", postVo);		
	}

}
