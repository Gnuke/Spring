<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert</title>
</head>
<body>
	<h3>글 작성</h3>
	<form action="/board/insert" method="post">
	<table>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" value="${sessionScope.loginId }" readonly></td>
		</tr>
		<tr>
			<th>글 제목</th>
			<td><input type="text" name="title" ></td>
		</tr>
		<tr>
			<th>글 내용</th>
			<td><textarea rows="5" cols="30" name="content" ></textarea></td>
		</tr>
		<tr>
			<td><button type="submit" name="insertBtn" onclick="location.href='/board/insert'">작성</button></td>
		</tr>
	</table>
	</form>
</body>
</html>