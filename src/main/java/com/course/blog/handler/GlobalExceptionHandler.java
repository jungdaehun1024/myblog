package com.course.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.course.blog.dto.ResponseDto;

@ControllerAdvice // @Controller, @RestController에서 Exception이 발생시 해당 클래스로 넘어오게함
@RestController //데이터를 리턴하는 컨트롤러
public class GlobalExceptionHandler {
	
	//IllegalArgumentException이 발생하면 해당 함수에 전달 
	@ExceptionHandler(value = Exception.class) 
	public ResponseDto<String> handleArgumentException(Exception e) {
		
		
		// .value() ==> httpstatus 코드를 int형으로 리턴해준다.
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());

	}

}
