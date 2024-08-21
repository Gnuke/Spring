<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Param1</title>
</head>
<body>
	<h3>param1 list</h3>
		<c:forEach var="s" items="${list }">
			<h3>${s }</h3><br />
		</c:forEach>
</body>
</html>