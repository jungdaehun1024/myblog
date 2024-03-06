package com.course.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.course.blog.dto.ResponseDto;

@ControllerAdvice // @Controller, @RestController에서 Exception이 발생시 해당 클래스로 넘어오게함
@RestController //데이터를 리턴하는 컨트롤러
public class GlobalExceptionHandler {
	
	//handleArgumentException 메서드가 Exception 클래스 또는 그 하위 클래스에서 발생하는 예외를 처리한다는 것을 명시
	@ExceptionHandler(value = Exception.class) 
	public ResponseDto<String> handleArgumentException(Exception e) {
		// .value() ==> httpstatus 코드를 int형으로 리턴해준다.
		//HttpStatus.INTERNAL_SERVER_ERROR :INTERNAL_SERVER_ERROR에 대한 HTTP 상태 코드를 가져와서 반환
		//e.getMessage(): 발생한 예외의 메시지를 가져와서 반환
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());

	}

}
