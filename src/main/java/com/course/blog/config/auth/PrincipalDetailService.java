package com.course.blog.config.auth;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.course.blog.model.User;
import com.course.blog.repository.UserRepository;

@Service
// 실제 로그인을 진행하는 클래스  
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepsitory;
	private final HttpSession httpSession = null;

//	UserDetailsService를 사용하여 사용자의 인증 정보를 가져오고, 이 정보를 기반으로 사용자의 인증을 처리합니다
// 사용자가 제공한 사용자명을 토대로 loadUserByUsername 메소드가 호출되어 사용자 정보를 가져오게 됩니다. 
//	이 정보를 스프링 시큐리티가 제공하는 UserDetails 객체로 변환하여 인증에 활용합니다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepsitory.findByUsername(username).orElseThrow(() -> {
			return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");
		});
		return new PrincipalDetail(principal); // 이때 시큐리티 세션에 유저정보가 저장됨
	}
}



//로그인 진행시 loadUserByUsername이 자동으로 실행되면서 username을 찾고 
//username이 있는 경우 PrincipalDetail에 principal을 담는고 리턴 (이때 시큐리티 세션에 유저정보 저장)
//만약  loadUserByUsername()을 override하지 않으면 우리가 들고있는 유저정보를 principalDetail객체에 담아줄 수 없다.
//(기본 username, password만 사용이 가능)