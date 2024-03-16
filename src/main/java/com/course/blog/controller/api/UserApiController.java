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
	private AuthenticationManager authenticationManager;// 해당 인터페이스를 구현하는 클래스는 사용자의 인증을 처리하고 인증이 성공하면 Authentication객체 반환
	
    @PostMapping("/auth/joinProc")
    //json기반의 데이터 요청의 경우 @RequestBody를 사용한다. (http요청의 바디내용(Json)을 자바객체로 변환)
	public ResponseDto<Integer> save(@RequestBody User user ){ 
    	userService.signUp(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //http 상태코드와 1을포함한 ResponseDTO객체를 반환
	}
    
    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){
    	userService.updateProfile(user);
    	//UsernamePasswordAuthenticationToken은 새로운 username,password를 받아 인증객체를 반환하고 authenticate()에 전달한다. authenticate는 
    	//UsernamePasswordAuthenticationToken로 인증된 인증객체를 유효하다고 판단해 Authentication객체를 반환
    	Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    	

    	// 현재 실행 중인 스레드의 보안 컨텍스트를 조회하고, 그 안에 있는 현재 사용자의 인증 정보를 주어진 Authentication 객체로 설정
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
