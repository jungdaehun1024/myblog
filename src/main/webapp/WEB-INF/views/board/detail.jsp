<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container">
<button class="btn btn-secondary" onclick = "history.back()">돌아가기 </button>
<c:if test="${board.user.id == principal.user.id}">
  <a class="btn btn-warning" href="/board/${board.id}/updateForm">수정</a>
  <button id ="btn-delete" class = "btn btn-danger">삭제</button>
</c:if>
<button id="btn-like" type="button" class="btn btn-primary">
  Like<span id="currentLike" class="badge badge-light">${likes}</span>
</button>
<br><br>

<div>
 글 번호: <span id ="id"><i>${board.id}</i></span>  
 작성자: <span><i>${board.user.username}</i></span>
</div>

<br>  
	<hr>
		<div class="form-group">
		 <h3>${board.title}</h3>
		</div>
		<hr>
		<div class="form-group">
		<h3>${board.content}</h3>
		</div>
		<hr>
		
		<!-- 댓글 디자인 -->
		<div class ="card">		
		        <form>   
		        <input type ="hidden" id = "userId" value="${principal.user.id}"/>
		        <input type="hidden" id="boardId" value="${board.id}" />
		        <div class="card-body">
		         <textarea id = "reply-content" class="form-control" rows="1"></textarea>
		         </div>
		        <div class="card-footer">
		        <button type="button"id ="btn-reply-save" class= "btn btn-primary">등록</button>
		        </div>
		        </form>
		</div>
		
		<br/>
		<div class= "card">
		 <div class ="card-header">댓글리스트</div>
		</div>
		
		<ul id="reply-box" class="list-group">
		  <c:forEach var="reply" items="${board.replys}">
		  <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-center"> 
		<div>${reply.content}</div>
		<div class="font-italic">작성자:${reply.user.username} &nbsp;</div>
		<button onClick="index.replyDelete(${board.id},${reply.id})"class="badge">삭제</button>
		</li>
		  </c:forEach>
		
		
		</ul>
		
		
</div>


<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>