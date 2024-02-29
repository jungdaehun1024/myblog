package com.course.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.course.blog.model.Reply;

public interface ReplyRepository  extends JpaRepository<Reply, Integer>{

	
	@Modifying
	@Query(value =" INSERT INTO reply(userId,boardId,content,createDate)VALUES(?1,?2,?3,now())",nativeQuery = true)
	int mSave(int userId, int boardId, String content);  // INSERT ,UPDATE DELETE 등을 수행하면 
	//리턴값을  엡데이트된 행의 개수를 리턴한다  ( 1리턴시 1개가 save, 0이면 save된거 없음 -1은 오류 )
}


//오프젝트 출렷시 작동으로 toString()이 호출됨 