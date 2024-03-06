package com.course.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.course.blog.dto.ReplySaveRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false , length = 200)
	private String content;
	
	@ManyToOne //@ManyToOne: 기본 fetch전략이 EAGER이다. 하나의 게시글엔 댓글 여러개가 달릴수있다. 
	@JoinColumn(name="boardId")  // 외래키로 boardId지정 해당 키로 댓글의 게시글 정보를 볼 수 있다.
	private Board board;
	
	@ManyToOne//하나의 유저는 여러개의 댓글을 달수 있다. 
	@JoinColumn(name="userId")  //  외래키로 Userid
	private User user;  // 해당 키로 댓글의 유저정보를 볼 수 있다.
	
	@CreationTimestamp //현재시간 설정
	private Timestamp createDate;// 현재 시간이 밀리초 단위로 저장된다.
	

}
