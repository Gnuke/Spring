<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member</title>
</head>
<body>
	<h3>회원가입 폼</h3>
	<form action="/mem/join" method="post">
		id: <input type="text" name="id"><br />
		pw: <input type="password" name="pwd"><br />
		name: <input type="text" name="name"><br />
		email: <input type="text" name="email"><br />
		<input type="submit" value="가입">
	</form>
</body>
</html>