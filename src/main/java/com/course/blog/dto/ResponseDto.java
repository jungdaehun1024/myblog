package com.course.blog.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//응답관련 클래스 
public class ResponseDto<T> {
	int staus;
	T data;
	

}
