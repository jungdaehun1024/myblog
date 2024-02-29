package com.course.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.course.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/" })
	//@PageableDefault:페이징을 위한 정보가 담겨있는 인터페이스,[시작페이지:0 , 한페이지에 글 3개씩 , 정렬기준:id , 내림차순]
	public String index(Model model,@PageableDefault(page=0,size=3,sort="id",direction=Direction.DESC) Pageable pageable) {
		model.addAttribute("boards", boardService.글목록(pageable));
		return "index";
	}

	// 글 쓰기 페이지 호출 메서드
	@GetMapping("/board/saveForm")
	public String save() {
		return "/board/saveForm";
	}
	
	
	//게시글상세페이지 호출 메서드
	@GetMapping("/board/{id}")
	public String boardDetails(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.글상세보기(id));	
		
		return "/board/detail";
	}
	
	//게시글수정메서드 
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id,Model model) {
		model.addAttribute("board",boardService.글상세보기(id)); //글을 수정하기 위해 id값으로 게시글을 불러오는 것
		return "/board/updateForm";
	}

	
	
}
