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
	<h3>방명록</h3>
	<button type="button" onclick="location.href='/guest/write'">글작성</button><br />
	<c:if test="${list.size() == 0 }">
		작성된 글이 없습니다.
	</c:if>
	<c:if test="${list.size() != 0 }">
		<table border="1">
			<tr>
				<th>No.</th><th>작성자</th><th>작성 날짜</th>
			</tr>
			<c:forEach var="g" items="${list }">
				<tr>
					<td><a href="/guestbook/content?num=${g.num }">${g.num }</a></td>
					<td>${g.writer }</td>
					<td>${g.wdate }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>