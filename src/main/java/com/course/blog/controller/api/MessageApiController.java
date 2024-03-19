package com.course.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.course.blog.dto.MessageDTO;
import com.course.blog.dto.ResponseDto;
import com.course.blog.service.MessageService;

@RestController
public class MessageApiController {

	@Autowired
	private MessageService messageService;
	
	@PostMapping(value = "/message/send")
	public ResponseDto<Integer> sendMessage(@RequestBody MessageDTO messageDTO){
		 messageService.sendMessage(messageDTO);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
