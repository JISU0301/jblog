package kr.co.itcen.jblog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	PostDao postDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	public Map<String, Object> view(String id, long categoryNo, long postNo) {
		PostVo postView = new PostVo();
		List<PostVo> postList = new ArrayList<PostVo>();
		
		if(categoryNo == 0 && postNo == 0) {
			// 최근 포스트 내용 가져오기
			postView = postDao.getPostView(id);
			
			if(postView != null) {
				// 포스트 목록 가져오기
				postList = postDao.getPostList(postView.getCategoryNo());
			}
		} else if(categoryNo == 0 && postNo > 0) {
			return null;
		} else {
			// 해당 카테고리의 최근 포스트 내용 가져오기
			postView = postDao.getPostView(categoryNo);
			
			if(postView != null) {
				// 포스트 목록 가져오기
				postList = postDao.getPostList(postView.getCategoryNo());
			}
		}
		
		List<CategoryVo> categoryList = new ArrayList<CategoryVo>();
		
		// 카테고리 목록 가져오기
		categoryList = categoryDao.getCategoryList(id);
		
		Map<String, Object> mainList = new HashMap<String, Object>();
		
		mainList.put("postView", postView);
		mainList.put("postList", postList);
		mainList.put("categoryList", categoryList);
		
		return mainList;
	}

	public void postWrite(PostVo postVo) {
		postDao.postWrite(postVo);

	}
}