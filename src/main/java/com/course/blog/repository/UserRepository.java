package com.course.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.blog.model.User;


//해당 JpaRepository는 User테이블을 관리하는 Repositroy (CRUD만 하고싶다면 비워둠)
//JpaRepositroy는 @Repository생략이 가능하다-> 자동으로 Bean으로 등록됨
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
	//Select * From user WHERE username=1?;  (첫번째 파라미터가 1?에 들어감)
	Optional<User> findByUsername(String username);
	
	
}







//[ 전통적인 방법의 로그인처리 로직]
//JPA네이밍쿼리 
//SELECT * FROM user WHERE username =?1 AND Password =?2 ;  와 같다 . 
//User findByUsernameAndPassword(String username,String password);


//@Query(value="SELECT * FROM user WHERE username =?1 AND Password =?2",nativeQuery = true)
//User login(String username,String password);
//	