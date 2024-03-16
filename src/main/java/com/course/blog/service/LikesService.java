package com.course.blog.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.blog.dto.BoardLikeDTO;
import com.course.blog.repository.LikesRepository;

@Service
public class LikesService {

	@Autowired
	private LikesRepository likesRepository;
	
	//게시글 좋아요 카운팅
	@Transactional
	public int countLikes(BoardLikeDTO boardLikeDTO) {
		int count =likesRepository.countLikes(boardLikeDTO.getBoardId());
		return count;
		
	}
	
	//좋아요 클릭시 저장
	@Transactional
	public void saveLikes(BoardLikeDTO boardLikeDTO) {
		// 시큐리티 세션에 저장된 사용자 정보를 가져온다.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName(); 
		
		//내가 좋아요 누른게 있으면 좋아요 버튼 클릭시 기존 좋아요 삭제
		if(likesRepository.myLikes(boardLikeDTO.getBoardId(),userId)>0){
			likesRepository.mdeleteLikes(boardLikeDTO.getBoardId(),userId);
		}else {
			//내가 좋아요 누른 기록이 없다면 좋아요 
			likesRepository.mSaveLike(boardLikeDTO.getBoardId(),userId);
		}
		
	}
	
	
	//게시글 상세보기를 눌렀을 때 초기 좋아요값을 조회
	@Transactional
	public int initCountLikes(int boardId) {
		int count =likesRepository.countLikes(boardId);
		return count;
	}
}
