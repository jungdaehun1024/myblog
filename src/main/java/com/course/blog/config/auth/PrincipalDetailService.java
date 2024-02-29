package com.course.blog.config.auth;

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

	// 스프링요청을 가로챌때 username, password 변수 2개를 가로챈다.
	// username이 DB에 있는지 확인해주면됨 (password는 SecurityConfig로직에서 처리)
	// 해당 메서드를 통해 로그인이 된다.
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