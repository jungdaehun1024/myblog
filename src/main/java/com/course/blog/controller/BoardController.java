package com.course.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.course.blog.service.BoardService;
//HTTP요청을 처리하는 컨트롤러역할을 수행하는 클래스 
@Controller
public class BoardController {

	//의존성 주입
	@Autowired
	private BoardService boardService;

	//HTTP GET요청을 처리한다.
	@GetMapping({ "", "/" })
	
	//@PageableDefault:페이징을 위한 정보가 담겨있는 인터페이스,[시작페이지:0 , 한페이지에 글 3개씩 , 정렬기준:id , 내림차순]
	// 해당 어노테이션에서 지정한 페이지 설정은 Pageable인터페이스의 객체에 반영되어 메서드 호출시 사용된다.
	// boardService.글목록(pageable)실행 결과를 boards라는 이름으로 뷰에 전달
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
	//쿼리스트링형태로 id값을 받아  받아온 id값으로 상세 게시글을 보여준다.
	//model인터페이스를 통해 뷰로 전달
	@GetMapping("/board")
	public String boardDetails(@RequestParam int id, Model model) {
		model.addAttribute("board",boardService.글상세보기(id));	
		
		return "/board/detail";
	}
	
	//게시글수정메서드 
	// 해당 URL로 HTTP GET요청이 오면 
	//@PathVariable로 id값을 받아와 글 상세보기 메서드를 실행시키고 반환값을 
	//model을 사용해 board라는 이름으로 뷰에 넘긴다.
	//그리고 /board/updateForm 화면을 보여준다.
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id,Model model) {
		model.addAttribute("board",boardService.글상세보기(id)); //글을 수정하기 위해 id값으로 게시글을 불러오는 것
		return "/board/updateForm";
	}

	
	
}
