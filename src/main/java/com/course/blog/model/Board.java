package com.course.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false ,length = 100)
	private String title;
	
	@Column
	@Lob// 대용량 데이터를 사용,(html태그가 섞여 디자인되어 글자의 용량이 커지기 때문에 사용)
	private String content;
	
	@ColumnDefault("0") //컬럼의 디폴트 값 = 0
	private int count; //조회수 
	
	
	//mappedBy가 있으면 연관관계의 주인이 아니며 읽기(Read)만 가능하다. 
	//Board엔티티는 쿼리 한번으로 Rely엔티티까지 조회 
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	
	@ManyToOne(fetch = FetchType.EAGER) // 게시글에는 반드시 유저정보가 필요
	@JoinColumn(name="userId")//외래키생성 이름은userId (DB에도 userId 컬럼생성)
	private User user;
	
	
	@Column
	@CreationTimestamp
	private Timestamp createDate;

}
