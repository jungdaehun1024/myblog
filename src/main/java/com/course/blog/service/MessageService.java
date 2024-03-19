package com.course.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.blog.dto.MessageDTO;
import com.course.blog.dto.MsgListDTO;
import com.course.blog.model.Message;
import com.course.blog.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;
	
	//메시지 보내기
	@Transactional
	public void sendMessage(MessageDTO messageDTO) {
		String title = messageDTO.getTitle();
		String content = messageDTO.getContent();
		String receiverName = messageDTO.getReceiverName();
		String senderName = messageDTO.getSenderName();
		int senderId = messageDTO.getSenderId();
		messageRepository.mSendMessage(title,content,receiverName,senderId,senderName);
	}
	
	//메시지 리스트
	@Transactional
	public List<MsgListDTO> listMessage(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName(); 
		List<MsgListDTO> messageList = new ArrayList();
		messageList.addAll(messageRepository.mListMessage(userName));
		return messageList;
		
	}
	

	@Transactional
	public Message loadDetailMessage(int id) {
		 return messageRepository.findById(id)  //findById는 Optional을 반환하기 때문에 orElseThrow로 예외처리해주어야함 
				 .orElseThrow(()->{
					 return new IllegalArgumentException(" 쪽지를 찾을 수 없습니다.");
				 });
	}
	
}
