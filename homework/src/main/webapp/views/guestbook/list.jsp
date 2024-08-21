<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
</head>
<body>
	<h3>방문자 목록</h3>
	<table border="1">
		<tr>
			<th>No.</th><th>작성자</th><th>작성 날짜</th>
		</tr>
		<c:forEach var="c" items="${list }">
			<tr>
				<td>${c.num }</td>
				<td><a href="/guestbook/content?num=${c.num }">${c.writer }</a></td>
				<td>${c.wdate }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>