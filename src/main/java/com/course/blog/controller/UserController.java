package com.course.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/**   (auth 이하경로만 허용),  index.jsp 허용 
// static이하에 있는 /js/**  , /css/** , /image/**  허용할 것 
// 인증이 필요 없는 곳에는 /auth를 붙일 것 


@Controller
public class UserController {
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "user/joinform";

	}
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginform";

	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		
		return "/user/updateForm";
	}

}
