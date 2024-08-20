<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도시 목록</title>
</head>
<body>
<!-- 전체목록 출력  -->
<h3>도시 목록</h3>
<table border="1">
	<tr>
		<th>id</th><th>name</th><th>countryCode</th><th>district</th><th>population</th>
	</tr>
	<c:forEach var="c" items="${list }" >
	<tr>
		<td>${c.id }</td>
		<td><a href="/city/detail?id=${c.id }">${c.name }</a></td>
		<td>${c.countryCode }</td>
		<td>${c.district }</td>
		<td>${c.population }</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>