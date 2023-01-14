<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<title>board/boardList.do</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>게시글 목록 조회</h4>

<table class="table table-bordered">
	<thead class="thead-dark"> 
		<tr>
			<th>일련번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>이메일</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="boardList" value="${pagingVO.dataList}" />
		<c:choose>
			<c:when test="${not empty boardList }">
				<c:forEach items="${boardList }" var="board">
					<tr>
						<td>${board.boNo }</td>
						<td>${board.boTitle }[${board.attCount }]</td>
						<td>${board.boWriter }</td>
						<td>${board.boMail }</td>
						<td>${board.boDate }</td>
						<td>${board.boHit }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">조건에 맞는 회원이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="pagingArea">${pagingVO.pagingHTML }</div>
				<form:form id="searchUI" modelAttribute="simpleCondition" method="get" onclick="return false;">
					<form:select path="">
						<option value>전체</option>
						<form:option value="writer" label="작성자" />
						<form:option value="content" label="내용" />
					</form:select>
					<form:input path="searchWord" />
					<input type="button" id="searchBtn" value="검색" />
				</form:form>
			</td>
		</tr>
	</tfoot>
</table>
<h4>Hidden Form</h4>
<form:form id="searchForm" modelAttribute="simpleCondition" method="get">
	<form:hidden path="searchType" />
	<form:hidden path="searchWord" />
	<input type="hidden" name="page" />
</form:form>

<script type="text/javascript">
	
	let searchForm = $("#searchForm");
	let searchUI = $("#searchUI").on("click", "#searchBtn", function(){
		let inputs = searchUI.find(":input[name]");
		$.each(inputs, function(index, input){
			let name = this.name;
			let value = $(this).val();
			searchForm.find("[name="+name+"]").val(value);
		});
		searchForm.submit();
	});
	
	$("a.paging").on("click", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(!page) return false;
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
		return false;
	});
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>