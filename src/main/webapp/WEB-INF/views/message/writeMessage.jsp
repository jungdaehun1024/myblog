<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container">

	<form>

	 	보내는 사람: <span id ="senderName"><i>${principal.user.username}</i></span> 
	    <hr>
		<div class="form-group">
			 
			  <input type="hidden" class="form-control" value=${principal.user.id} id="senderId">
			<label for="title">Title</label>
			 <input type="text" class="form-control" placeholder="Enter title" id="title">
		</div>
		<div class="form-group">
			<label for="content">Content</label>
			 <input type="text"class="form-control" placeholder="Enter content"id="content">
		</div>
			<div class="form-group">
			<label for="content">Receiver</label>
			 <input type="text"class="form-control" placeholder="Enter receiver" id="receiverName">
			</div>
	</form>
	<button id="btn-msg-save" class="btn btn-primary">쪽지 보내기</button>

</div>


<script src="/js/message.js"></script>
<%@ include file="../layout/footer.jsp"%>