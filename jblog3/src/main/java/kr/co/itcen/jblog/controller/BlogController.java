package kr.co.itcen.jblog.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.FileuploadService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@RequestMapping("/{id:(?!assets)(?!images).*}")

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileuploadService fileuploadService;
	

	// 블로그 메인
	// 블로그 정보, 카테고리 정보, 포스트 정보
	@RequestMapping({ "", "/{categoryno}", "/{categoryno}/{postno}" })
	public String blogMain(@PathVariable String id, @PathVariable Optional<Long> categoryno,
			@PathVariable Optional<Long> postno, Model model) {
		long categoryNo = categoryno.isPresent() ? categoryno.get() : 0; // 값이 있으면 값으로, 없으면 0으로 설정
		long postNo = postno.isPresent() ? postno.get() : 0;

		// 메인 컨텐츠 가져오기
		Map<String, Object> view = postService.view(id, categoryNo, postNo);

		// 블로그 메인 정보
		BlogVo blogVo = blogService.getInfo(id);

//		if (blogVo == null || view == null) {
//			return "user/join";
//		}

		model.addAttribute("view", view);
		model.addAttribute("blogInfo", blogVo);
		

		return "blog/blog-main";
	}
	
	@RequestMapping("/admin")
	public String admin(@PathVariable String id, @RequestParam(value="admin_no", required=false, defaultValue="1") int admin_no,
						@ModelAttribute PostVo postVo, Model model) {
		
		BlogVo blogVo= new BlogVo();
		blogVo = blogService.getInfo(id);


		model.addAttribute("blogInfo",blogVo);
		model.addAttribute("admin_no",admin_no);
		
		
		if(admin_no == 2) {
			// 카테고리 리스트 가져오기
			List<CategoryVo> list=categoryService.getCategoryList(id);
			
			model.addAttribute("list",list);
			
			return "blog/blog-admin-category";
		}
		
		if(admin_no == 3) {
			//포스트작성
			List<CategoryVo> categoryName = categoryService.getCategoryName(id);
			model.addAttribute("categoryName", categoryName);

			return "blog/blog-admin-write";
		}
		
		return "blog/blog-admin-basic";
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@PathVariable String id, @RequestParam(value="title", defaultValue = "") String title,
			@RequestParam(value="logo-file",required=false) MultipartFile multipartFile) {
		
		String logoUrl = fileuploadService.restore(multipartFile);
		
		BlogVo blogVo = new BlogVo();
		System.out.println("@@@@@@@@@" + title);
		blogVo.setId(id);
		blogVo.setTitle(title);
		blogVo.setLogo(logoUrl);
		
		System.out.println("~~~~~~~~~~~~~" + blogVo.getTitle());
		
		// 블로그 정보 수정
		blogService.update(blogVo);
		
		
		return "redirect:/"+id;
		
	}
	
	
	@RequestMapping(value = "/postWrite", method = RequestMethod.POST)
	public String postWrite(@PathVariable String id, @ModelAttribute @Valid PostVo postVo, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error);
			}

			model.addAllAttributes(result.getModel());

			List<CategoryVo> categoryName = categoryService.getCategoryName(id);
			BlogVo blogVo = blogService.getInfo(id);

			model.addAttribute("categoryName", categoryName);
			model.addAttribute("admin_no", 3);
			model.addAttribute("blogInfo", blogVo);

			return "blog/blog-admin-write";
		}
		
		// 포스트 작성
				postService.postWrite(postVo);
				return "redirect:/" + id;
	}
}