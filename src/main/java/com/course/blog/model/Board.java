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
@Entity // DB의 board 테이블과 매핑 
public class Board {
	
	@Id // Pk지정 
	@GeneratedValue(strategy = GenerationType.IDENTITY)//DB의 넘버링 전략을 따라간다 (Auto_Increment)
	private int id;
	
	//엔티티 클래스 필드에 추가적인 설정 , null값이 올 수 없고 길이는 100)
	@Column(nullable = false ,length = 100)
	private String title;
	
	@Lob// 대용량 데이터를 사용,(html태그가 섞여 디자인되어 글자의 용량이 커지기 때문에 사용)
	private String content;
	
	
	//Board엔티티는 쿼리 한번으로 Rely엔티티까지 조회 
	//이전에는 Reply 엔티티가 관계의 주인이었기 때문에 Reply 엔티티에서만 Board 엔티티를 조회할 수 있다. 
	//하지만 mappedBy를 Reply 엔티티의 자기 자신인 "board" 필드로 지정함으로써 Board 엔티티에서도 Reply 엔티티를 조회할 수 있게 되었다.
	//이 관계 설정을 통해 양방향으로의 조회가 가능해졌다.
	//FetchType을 EAGER로 설정하면 Board 엔티티를 조회할 때 연관된 Reply
	//엔티티도 즉시 로딩되어 영속성 컨텍스트에 올라가고, 
	//이 때 필요한 쿼리가 실행됩니다. 그 결과 값은 함께 가져와지므로 추가적인 쿼리를 실행하지 않아도 된다.
	// CascadeType.REMOVE: 부모 엔티티가 삭제 될때 자식엔티티도 함께 삭제
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"board"})//JSON직렬화시 board필드를 숨긴다.(클라이언트에서 댓글 목록 요청시 json형태로 전송해야하는데 이때 board필드는제외하고 변환)
	@OrderBy("id desc") //내림차순
	private List<Reply> replys;
	
	
	//Board와 User엔티티는 다대일 관계를 맺는다.
	@ManyToOne(fetch = FetchType.EAGER) // 게시글에는 반드시 유저정보가 필요
	@JoinColumn(name="userId")//외래키생성 이름은userId (DB에도 userId 컬럼생성)
	private User user;
	
	
	@Column
	@CreationTimestamp
	private Timestamp createDate; 
	
	
    @OneToMany(mappedBy = "board")
    private List<Likes> likesList;

}
