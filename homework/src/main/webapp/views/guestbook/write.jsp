<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GuestBook</title>
</head>
<body>
	<h3>방명록 작성</h3>
	<form action="/guest/write" method="post">
		writer : <input type="text" name="writer"><br />
		pwd : <input type="password" name="pwd"><br />
		content : <br /><textarea name="content"></textarea><br />
		<input type="submit" value="작성">
		<button type="button" onclick="location.href='/'">취소</button>
	</form>
</body>
</html>