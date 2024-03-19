package com.course.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.course.blog.dto.MsgListDTO;
import com.course.blog.model.Message;

public interface MessageRepository extends JpaRepository<Message,Integer> {

	@Modifying
	@Query(value = "INSERT INTO message(title,content,receiverName,senderId,createDate,senderName)VALUES(?1,?2,?3,?4,now(),?5)",nativeQuery = true)
	int mSendMessage(String title, String content, String receiverName, int senderId,String senderName);
	

	@Query(value = "SELECT message.title ,message.content,message.id FROM user JOIN message ON message.receiverName = user.userName WHERE user.userName = ?1", nativeQuery = true)
	List<MsgListDTO> mListMessage(String userame);
}

