package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	// 카테고리 리스트 가져오기
	public List<CategoryVo> getCategoryList(String id) {
		List<CategoryVo> list = categoryDao.getCategoryList(id);
		return list;
	}

	public List<CategoryVo> add(CategoryVo categoryVo) {
		categoryDao.add(categoryVo);

		// 블로그 카테고리 목록 가져오기
		return categoryDao.getCategoryList(categoryVo.getBlog_id());
	}

	
	public List<CategoryVo> getCategoryName(String id) {
		List<CategoryVo> categoryList = categoryDao.getCategoryName(id);
		return categoryList;
	}

}

	
	
	


