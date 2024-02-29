package com.course.blog.controller.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.course.blog.dto.ResponseDto;
import com.course.blog.model.User;
import com.course.blog.service.UserService;

@RestController// 데이터만 리턴 
public class UserApiController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
    @PostMapping("/auth/joinProc")
    //json기반의 데이터 요청의 경우 @RequestBody를 사용한다. (http요청의 바디내용을 자바객체로 변환)
	public ResponseDto<Integer> save(@RequestBody User user ){ 
    	System.out.println("save()");
    	userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
    
    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){
    	userService.회원수정(user);
    	Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
 
    
    }
 
    
    
}




//스프링부트 전통적인 로그인 로직 
//@PostMapping("/api/user/login")
//httpsession은 @autowired해서 받는게 가능 ==> 세션 객체는 스프링컨테이너가 빈으로 등록해서 가지고있는다는 것 
//필요시 DI해서 쓸수 있다는 것==> 컨트롤러 함수의 매개변수로만 해도 된다.
//public ResponseDto<Integer> login(@RequestBody User user,HttpSession session){
//	User principal = userService.로그인(user);  // principal =>접근 주체라는 뜻 
//	if(principal != null) {
//		session.setAttribute("principal", principal);
//	}
//	
//	return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//}