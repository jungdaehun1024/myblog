package com.course.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // Builder사용시 @NoargsConstructor를 붙여주면  @AllargsConstructor도 같이 써줘야함
@Entity // 객체와 테이블을 매핑
public class User {
	
	@Id //해당 column이 PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // PK처리방식을 DB에 위임 (A.I)
	private int id;
	
	@Column(nullable = false,length = 30 ,unique = true) //NotNull , 길이 30, 중복값 허용 X
	private String username;
	
	@Column(nullable = false, length = 100) // NotNull, 길이 30 
	private String password;
	
	@Column(nullable = false , length = 50) // NotNull, 길이 50
	private String email;
	
	@CreationTimestamp // 시간이 자동으로 입력된다.
	private Timestamp createDate;
	
	@Enumerated(EnumType.STRING)  // DB에는 RoleType이 존재하지 않아 enum이 String임을 알려주어야한다.
	private RoleType role; 

}
