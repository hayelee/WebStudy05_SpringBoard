<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-bordered">
   <tr>
      <th>글번호</th>
      <td>${board.boNo}</td>
   </tr>
   <tr>
      <th>제목</th>
      <td>${board.boTitle}</td>
   </tr>
   <tr>
      <th>작성자</th>
      <td>${board.boWriter}</td>
   </tr>
   <c:if test="${not empty board.attatchList}">
      <tr>
         <th>첨부파일</th>
         <td>
            <c:forEach items="${board.attatchList}" var="attatch" varStatus="vs">
               ${attatch.attFilename} ${vs.last ? "" : ","}
            </c:forEach>
         </td>
      </tr>
   </c:if>
   <tr>
      <th>아이피</th>
      <td>${board.boIp}</td>
   </tr>
   <tr>
      <th>이메일</th>
      <td>${board.boMail}</td>
   </tr>
   <tr>
      <th>내용</th>
      <td>${board.boContent}</td>
   </tr>
   <tr>
      <th>작성일</th>
      <td>${board.boDate}</td>
   </tr>
   <tr>
      <th>조회수</th>
      <td>${board.boHit}</td>
   </tr>
 	<tr>
      <td colspan="2">
         <form:button type="submit" class="btn btn-success">수정</form:button>
         <form:button type="submit" class="btn btn-danger">삭제</form:button>
         <a class="btn btn-secondary" href="<c:url value='/board/boardList.do'/>">목록으로</a>
      </td>
   </tr>
</table>