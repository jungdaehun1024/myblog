package com.course.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.blog.model.RoleType;
import com.course.blog.model.User;
import com.course.blog.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 통해서 Bean등록을 해줌 (Ioc)
public class UserService {
 @Autowired
 private UserRepository userRepository;
 
 @Autowired
 private BCryptPasswordEncoder encoder;
	
 @Transactional
 public void signUp(User user) {
	 	
	    String rawPassword = user.getPassword();//사용자가 입력한 원시 비밀번호
	    
	    String encPassword = encoder.encode(rawPassword); //입력한 비밀번호를 해시화해 저장
	    
	    user.setPassword(encPassword);//사용자 비밀번호가 해시화된 값으로 설정
	 
	    user.setRole(RoleType.USER); // 권한은 USER
		userRepository.save(user); // 사용자 저장
	 
		//함수가 종료되면 더티체킹 방식으로 DB에 commit
 }
 
 
 @Transactional
 public void updateProfile(User user) {
	 //유저 정보를 가져온다.
	 User persistance = userRepository.findById(user.getId())
			 .orElseThrow(()->{
				return new IllegalArgumentException("회원찾기 실패"); // 사용자를 못찾았을때 예외
			 });
	 String rawPassword =user.getPassword(); //유저가 회원수정란에서 수정한 원시 비밀번호
	 
	 String encPassword = encoder.encode(rawPassword); // 수정한 비밀번호 해시화
	 persistance.setPassword(encPassword); // 유저의 passowrd정보 수정
	 persistance.setEmail(user.getEmail());// 이메일 수정
 } 
}


//서비스는 트랜잭션을 관리하기 위해 필요함 
//여러 로직이 묶에 하나의 서비스가 될 수 있다 
//서비스에서 일 하나하나를 트랜잭션이라 하는데 트랜잭션들을 하나의 트랜잭션으로 묶어야하기 때문에 서비스를 사용함 
//서비스는 하나의 트랜잭션을 들고있기도하지만 여러개의 트랜잭션이 모여 하나의 서비스가 된다 .