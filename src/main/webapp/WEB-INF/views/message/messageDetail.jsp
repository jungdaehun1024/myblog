<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../layout/header.jsp"%>

<div> 
    <%-- 보낸 사람과 전송 시간을 양 끝에 배치 --%>
    <div style="display: flex; justify-content: space-between;">
        <span><i>보낸사람:${message.senderName}</i></span>
        <%-- 포맷된 날짜를 출력합니다. --%>
        <fmt:formatDate var="formattedDate" value="${message.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
        <span><i>전송시간:${formattedDate}</i></span>
    </div>
    <hr>
    제목:<span><i>${message.title}</i></span>
    <hr>
    내용: <span><i>${message.content}</i></span>
</div>

<%@ include file="../layout/footer.jsp"%>
