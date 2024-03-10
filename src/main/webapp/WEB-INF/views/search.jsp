<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<!-- 게시글 서칭 목록 -->
<c:forEach items="${searchList}" var ="searchList" >   <!--item속성에는 Collection객체가 들어갈 수 있다.-->
  <div class="card m-2" >
    <div class="card-body"> 
      <h4 class="card-title">${searchList.title}</h4>  
      <a href="/board/${searchList.id}" class="btn btn-primary">상세보기</a>
    </div>
  </div>
</c:forEach>
<!--   -->
<%@ include file="layout/footer.jsp" %>