package com.course.blog.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.blog.dto.ReplySaveRequestDto;
import com.course.blog.model.Board;
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
 
 //글작성
 //해당 메서드 안에서 실행되는 모든 DB작업이 하나의 트랜젝션으로 처리된다.
 //메서드가 성공적으로 완료되면 커밋되어 변경 내용이 반영되고 예외 발생시 롤백된다.
 //User는 로그인 성공시 Authenticaion객체에서 가져온 User정보이다.
 @Transactional
 public void createPost(Board board,User user) { 
	 board.setUser(user); //principal.getUser로 받은 user객체(현재 유저정보)를 넣어준다.  [board는 FK를 가지고있어 user정보 조회가능]
	 boardRepository.save(board); //글 저장 
	 
 }
 
 //읽기전용
 //글목록
 @Transactional(readOnly = true)
 public Page<Board>listPosts(Pageable pageable){
	 return boardRepository.findAll(pageable); //findAll():데이터 전부를 가져온다.  findAll메서드에 pageable객체를 인자로 주면 Page객체로 반환 
 }
 
 
//글상세
 //readOnly =true  --> 읽기 전용모드 
 @Transactional(readOnly = true) 
 public Board loadPostDetails(int id) {
	 return boardRepository.findById(id)  //findById는 Optional을 반환하기 때문에 orElseThrow로 예외처리해주어야함 
			 .orElseThrow(()->{
				 return new IllegalArgumentException("게시글을 찾을 수 없습니다.");
			 });
 }
 
 //글수정
 @Transactional
 public void updatePost(int id,Board requestBoard) {
	 //영속성컨텍스트에 영속화하기 위한 작업  , findById(id)를 통해 수정하려는 객체를 영속성컨텍스트에 띄운다.
	 Board board = boardRepository.findById(id) .orElseThrow(()->{
		 return new IllegalArgumentException("게시글을 찾을 수 없습니다.");
	 });
	 
	 //더티체킹방식 사용
	 //함수가 종료시 @Trnasactional은 영속성 컨텍스트의 변화를 감지해 commit되어 DB에 반영된다.
	 board.setTitle(requestBoard.getTitle());
	 board.setContent(requestBoard.getContent());
	 
 }
 
 
 //글삭제
 @Transactional
 public void deletePost(int id){
	boardRepository.deleteById(id);
 }
 
 //BoardApiController로부터 댓글 데이터를 replySaveRequestDto로 받아온다.
 @Transactional
 public void writeReply(ReplySaveRequestDto replySaveRequestDto) {

	 //받아온 댓글 데이터에서 유저id, 게시글id,내용을 저장한다.
	 //INSERT INTO reply(userId,boardId,content,createDate)VALUES(?1,?2,?3,now())가 replyRepository에서 수행된다.
	 replyRepository.mSave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(),replySaveRequestDto.getContent());
 }
 
 //BoardApiController로부터 댓글 id를 받아와 댓글 삭제
 @Transactional
 public void deleteReply(int replyId){
	replyRepository.deleteById(replyId);
 }
 
 //서칭
 public List<Board> search(String search){
	 return boardRepository.searchByKeyword(search);
 }
}

