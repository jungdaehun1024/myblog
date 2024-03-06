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
//'T'는 제네릭 타입이다.  제네릭은 클래스나 메서드 정의시 사용되는 매개변수 타입이다.
// 이 경우 해당 클래스는 어떤 종류의 데이터든지 포함할 수 있는 응답을 나타내기 위해 T사용한다.
//T는 자바에서 임의의 참조 유형을 나타내며 클래스를 정의할 때 실제 데이터 타입이 지정될 것이다.
public class ResponseDto<T> {
	int staus;
	T data;
	

}
