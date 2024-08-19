<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>로그인 폼</h3>
	<form action="/member/login" method="post">
		id: <input type="text" name="myId"><br />
		pw: <input type="password" name="myPwd"><br />
		<input type="submit" value="login">
	</form>
</body>
</html>