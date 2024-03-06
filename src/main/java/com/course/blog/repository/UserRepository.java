package com.course.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.course.blog.model.User;

//User테이블을 관리하는 Repository (CRUD만 하고싶다면 비워둠)
public interface UserRepository extends JpaRepository<User, Integer>{
//	Select * From user WHERE username=1?;  (첫번째 파라미터가 1?에 들어감)
//	Optional<User> findByUsername(String username);


	// "user" 테이블에서 모든 열을 가져오지만, WHERE 절에서는 username이 지정된 값과 일치하는 행만 반환한다.
	@Query(value = "SELECT * FROM `user` WHERE username = ?1", nativeQuery = true)
	Optional<User> findByUsername(String username);
}
