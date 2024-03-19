package com.course.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.course.blog.service.MessageService;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;

	@GetMapping(value = "/message/sendForm")
	public String writeMessage() {
		return "/message/writeMessage";
	}
	
	
	@GetMapping(value = "/message/list")
	public String listMessage(Model model) {
		model.addAttribute("message",messageService.listMessage());

		return "/message/list";
	}
	
	@GetMapping(value = "/message/{id}")
	public String detailMessage(@PathVariable int id,Model model) {
		model.addAttribute("message",messageService.loadDetailMessage(id));
		return "/message/messageDetail";
	}
}
