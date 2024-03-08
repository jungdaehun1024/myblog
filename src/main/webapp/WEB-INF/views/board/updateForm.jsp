<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
	     <input value="${board.id}" type="hidden" id="id">  <!-- 게시글 번호를 알아야 수정할 수 있기 때문에 추가해준 것  -->
		<div class="form-group">
			<label for="title">Title</label>
			 <input value="${board.title}" type="text" class="form-control"  id="title">
		</div>
		
		<div class="form-group">
			<label for="content">Content</label>
			 <input value ="${board.content}" type="text"class="form-control" id="content">
		</div>
	</form>
	
	<button id="btn-update" class="btn btn-primary">수정 완료</button>

</div>


<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>