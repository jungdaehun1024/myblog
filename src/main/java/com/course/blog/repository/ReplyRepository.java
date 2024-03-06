package com.course.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.course.blog.model.Reply;

@Repository
public interface ReplyRepository  extends JpaRepository<Reply, Integer>{

	//두 어노테이션이 함께 사용되어서 DB에 데이터를 삽입한다.
	@Modifying //해당 메서드가 DB를 수정한다는것을 Spring에 알려준다.
	@Query(value =" INSERT INTO reply(userId,boardId,content,createDate)VALUES(?1,?2,?3,now())",nativeQuery = true)  
	int mSave(int userId, int boardId, String content);  // INSERT ,UPDATE DELETE 등을 수행하면 
	//리턴값을  엡데이트된 행의 개수를 리턴한다  ( 1리턴시 1개가 save, 0이면 save된거 없음 -1은 오류 )
}
