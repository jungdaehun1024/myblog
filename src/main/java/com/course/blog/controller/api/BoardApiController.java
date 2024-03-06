package com.course.blog.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.course.blog.config.auth.PrincipalDetail;
import com.course.blog.dto.ReplySaveRequestDto;
import com.course.blog.dto.ResponseDto;
import com.course.blog.model.Board;
import com.course.blog.model.Reply;
import com.course.blog.service.BoardService;

@RestController// 데이터만 리턴 
public class BoardApiController {
	@Autowired
	private BoardService boardService;
	
    @PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal){//@RequestBody: httpBody데이터를 받는다.,@AuthenticationPrincipal : 현재 세션정보를 가져온다.
    	// principal객체는 현재 세션의 유저정보 확인을 위해 필요
    	// getUser를 하기 위해서 PrincipalDetail클래스에 getter를 붙여주어야 함  PrincipalDetails의 User정보를 받아오기위함
    	boardService.글작성(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
    
    @PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board){ 
        boardService.글수정하기(id,board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
    
    @DeleteMapping("/api/board/{id}")
 	public ResponseDto<Integer> deleteBoard(@PathVariable int id){
         boardService.글삭제(id);
 		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
 	}
    
    
    //데이터를 받을 때 컨트롤러에서 dto만들어주서 받는게 좋다. 
    @PostMapping("/api/board/{boardId}/reply")
  	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){//@RequestBody: httpBody데이터를 받는다
    
   
      	boardService.댓글쓰기(replySaveRequestDto);
  		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
  	}
    
    
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
    	boardService.댓글삭제(replyId);
    	return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}



