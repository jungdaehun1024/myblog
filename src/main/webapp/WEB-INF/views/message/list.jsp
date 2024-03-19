<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<c:forEach var ="message" items="${message}">   <!--item속성에는 Collection객체가 들어갈 수 있다.-->
  <div class="card m-2" >
    <div class="card-body">
      <h4 class="card-title">${message.title}</h4>  
      <a href="/message/${message.id}" class="btn btn-primary">상세보기</a>
    </div>
  </div>
</c:forEach>
<script src="/js/message.js"></script>
<%@ include file="../layout/footer.jsp"%>