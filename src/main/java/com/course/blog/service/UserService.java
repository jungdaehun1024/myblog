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
 public void 회원가입(User user) {
	    String rawPassword = user.getPassword();
	    String encPassword = encoder.encode(rawPassword); //해쉬화 
	    user.setPassword(encPassword);
	    user.setRole(RoleType.USER);
		userRepository.save(user);
	 
 }
 
 @Transactional
 public void 회원수정(User user) {
	 User persistance = userRepository.findById(user.getId())
			 .orElseThrow(()->{
				return new IllegalArgumentException("회원찾기 실패");
			 });
	 
	 String rawPassword =user.getPassword();
	 String encPassword = encoder.encode(rawPassword);
	 persistance.setPassword(encPassword);
	 persistance.setEmail(user.getEmail());
 }
 

}


//전통적인 방식의 로그인 로직 
//@Transactional(readOnly =true) //select할 때 트랜잭션 시작 , 해당 서비스가 종료될 때 트랜잭션 종료가 되는데 이 때까지 정합성을 유지할 수 있다.
//public User 로그인(User user) {
//	  return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//}




//서비스는 트랜잭션을 관리하기 위해 필요함 
//여러 로직이 묶에 하나의 서비스가 될 수 있다 ==> 
//서비스에서 일 하나하나를 트랜잭션이라 하는데 트랜잭션들을 하나의 트랜잭션으로 묶어야하기 ㄸ문에 서비스를 사용함 
//서비스는 하나의 트랜잭션을 들고있기도하지만 여러개의 트랜잭션이 모여 하나의 서비스가 된다 .