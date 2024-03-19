package com.course.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO { 
	private String title;
	private String content;
	private String receiverName;
	private int senderId;
	private String senderName;

}
