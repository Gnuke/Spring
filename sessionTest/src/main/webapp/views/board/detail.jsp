<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
</head>
<body>
	<h3>상세 페이지</h3>
	<c:if test="${sessionScope.loginId != b.writer } ">
		<c:set var="str">readonly</c:set>
	</c:if>
	<form action="/board/insert" method="post" id="insertForm">
	<input type="hidden" value="${b.num }">
	<table>
		<tr>
			<th>작성자</th>
			<td><input type="text" value="${b.writer }" readonly></td>
		</tr>
		<tr>
			<th>글 제목</th>
			<td><input type="text" name="title"  value="${b.title }" ${str }></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><input type="text" value="${b.wdate }" readonly></td>
		</tr>
		<tr>
			<th>글 내용</th>
			<td><textarea rows="5" cols="30" name="content" ${str }>${b.content }</textarea></td>
		</tr>
	</table>
	<c:if test="${b.writer == sessionScope.loginId}">
		<button type="button" onclick="submitForm('/board/update')">글수정</button>
		<button type="button" onclick="location.href='/board/del'">글삭제</button>
	</c:if>
	</form>
	<button type="button" onclick="location.href='/'">뒤로가기</button>
	<script type="text/javascript">
		const submitForm = (actionUrl) => {
			let form = document.getElementById('insertForm');
		}
	</script>
</body>
</html>