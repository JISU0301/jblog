package kr.co.itcen.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.UserService;
import kr.co.itcen.jblog.vo.UserVo;




@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	

	// 회원 가입
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {

		return "user/join";
	}

	// 회원가입 폼
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
		if (result.hasErrors()) { // result에 Error가 있는지 없는지 확인
			model.addAllAttributes(result.getModel()); // result.getModel()이 Map을 리턴해주어, 키/값을 자동으로 셋팅해준다.
			return "user/join"; // Error가 있다면 join으로 리턴
		}
		userService.join(userVo);
		return "user/joinsuccess";
	}

	// 회원가입 성공
	@RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
	public String joinsuccess() {
		return "redirect:/user/joinsuccess";
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	// 로그인 인터셉터
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public void auth() {
	}
	
	// 로그아웃 인터셉터
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout() {
	}
}