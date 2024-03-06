package com.course.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.blog.model.Board;


//JpaRepository를 상속받는 인터페이스는 기본적으로 Spring Data JPA가
//자동으로 해당 인터페이스를 구현한 클래스를 생성하고 빈으로 등록
public interface BoardRepository extends JpaRepository<Board, Integer>{
	//T:JpaRepository가 작업할 대상 엔티티의 타입이다.
	//ID: 엔티티 식별자의 타입이다. (엔티티의 PK를 나타낸다.)
}






