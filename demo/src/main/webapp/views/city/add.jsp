<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>City</title>
</head>
<body>
	<h3>도시 입력</h3>
	<form action="/city/add" method="post">
		name : <input type="text" name="name"><br />
		countryCode : 
		<select name="countryCode">
			<c:forEach var="code" items="${list }">
				<option>${code }</option>
			</c:forEach>
		</select><br />
		district : <input type="text" name="district"><br />
		population : <input type="number" name="population"><br />
		<input type="submit" value="도시추가">
	</form>
</body>
</html>