<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<!-- 글목록 로직 -->
<!-- page객체는 contente단에 DB에서 가져온 정보들이 들어있다. -->
<c:forEach var ="board" items="${boards.content}">   <!--item속성에는 Collection객체가 들어갈 수 있다.-->
  <div class="card m-2" >
    <div class="card-body">
      <h4 class="card-title">${board.title}</h4>  
      <a href="/board?id=${board.id}" class="btn btn-primary">상세보기</a>
    </div>
  </div>
</c:forEach>


<!--페이징 로직  -->
<ul class="pagination">
 <c:choose>
 <c:when test="${boards.first}">    <!--page객체가 가지는 Pageable에서 first:첫번째 페이지 -->
   <li class="page-item disabled"  ><a class="page-link" href="?page=${boards.number-1}">Previous</a></li><!-- 첫번째 페이지라면 Previous버튼 disabled -->
 </c:when>
 <c:otherwise>
   <li class="page-item"  ><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
 </c:otherwise>
</c:choose>

 <c:choose>
 <c:when test="${boards.last}">  <!-- page객체가 가지는 Pageable에서 last: 마지막 페이지 -->
   <li class="page-item disabled"  ><a class="page-link" href="?page=${boards.number+1}">Next</a></li> <!-- 마지막 페이지라면 Next버튼 disabled -->
 </c:when>
 <c:otherwise>
   <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
 </c:otherwise>
</c:choose>

</ul>
<script src="/js/WebSocket.js"></script>
<%@ include file="layout/footer.jsp" %>