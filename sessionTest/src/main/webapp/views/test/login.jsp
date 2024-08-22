<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="/test/login" method="post">
		<table>
		<tr>
			<td>
				id : <input type="text" name="id">
			</td>
		</tr>
		<tr>
			<td>
				pwd : <input type="password" name="pwd">
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="login">
			</td>
		</tr>
		</table>
	</form>
</body>
</html>