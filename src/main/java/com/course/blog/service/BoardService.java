package com.course.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.blog.dto.ReplySaveRequestDto;
import com.course.blog.model.Board;
import com.course.blog.model.Reply;
import com.course.blog.model.User;
import com.course.blog.repository.BoardRepository;
import com.course.blog.repository.ReplyRepository;
import com.course.blog.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 통해서 Bean등록을 해줌 (Ioc)
public class BoardService {
	
 @Autowired
 private BoardRepository boardRepository;
 
 @Autowired
 private UserRepository userRepository;
	
 @Autowired
 private ReplyRepository replyRepository;
 
 @Transactional
 public void 글작성(Board board,User user) {
	 board.setCount(0);  // 조회수 미구현 
	 board.setUser(user); //principal.getUser로 받은 user객체(현재 유저정보)를 넣어준다.  [board는 FK를 가지고있어 user정보 조회가능]
	 boardRepository.save(board); //글 저장 
	 
	 
 }
 
 @Transactional(readOnly = true)
 public Page<Board>글목록(Pageable pageable){
	 return boardRepository.findAll(pageable); //findAll():데이터 전부를 가져온다.  findAll메서드에 pageable객체를 인자로 주면 Page객체로 반환 
 }
 

 //readOnly =true  --> 읽기 전용모드 
 @Transactional(readOnly = true) 
 public Board 글상세보기(int id) {
	 return boardRepository.findById(id)  //findById는 Optional을 반환하기 때문에 orElseThrow로 예외처리해주어야함 
			 .orElseThrow(()->{
				 return new IllegalArgumentException("게시글을 찾을 수 없습니다.");
			 });
 }
 
 
 @Transactional
 public void 글수정하기(int id,Board requestBoard) {
	 //영속성컨텍스트에 영속화하기 위한 작업  , findById(id)를 통해 수정하려는 객체를 영속성컨텍스트에 띄운다.
	 Board board = boardRepository.findById(id) .orElseThrow(()->{
		 return new IllegalArgumentException("게시글을 찾을 수 없습니다.");
	 });
	 
	 //더티체킹방식 사용
	 board.setTitle(requestBoard.getTitle());
	 board.setContent(requestBoard.getContent());
	 
 }
 
 
 
 @Transactional
 public void 글삭제(int id){
	boardRepository.deleteById(id);
 }
 
 @Transactional
 public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {


	 replyRepository.mSave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(),replySaveRequestDto.getContent());
	 
 }
 
 @Transactional
 public void 댓글삭제(int replyId){
	replyRepository.deleteById(replyId);
 }
 
}

