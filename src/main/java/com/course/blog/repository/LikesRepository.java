package com.course.blog.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.course.blog.model.Likes;

public interface LikesRepository extends JpaRepository<Likes, Integer>{	
	//좋아요 카운팅
	@Query(value = "SELECT COUNT(*) FROM likes WHERE boardId = ?1 ",nativeQuery = true)
	 int countLikes(int boardId);
	
	//좋아요 저장
	@Modifying
	@Query(value="INSERT INTO likes(createDate,boardId,userId) VALUES(now(),?1,?2) ",nativeQuery = true)
	int mSaveLike(int boardId,String userId);
	
	//좋아요 삭제
	@Modifying
	@Query(value ="DELETE FROM likes WHERE boardId = ?1 AND userId =?2", nativeQuery = true)
	int mdeleteLikes(int boardId,String userId);
	
	//내 좋아요찾기 쿼리
	@Query(value = "SELECT COUNT(*) FROM likes WHERE boardId = ?1 AND userId =?2 ",nativeQuery = true)
	 int myLikes(int boardId,String userId);
}
