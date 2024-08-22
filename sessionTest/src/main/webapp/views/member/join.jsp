<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript">
	const a = () => {
		let win = open( "./idcheck", "win", "width=300, height=300, top=100, left=100" );
	}
</script>
</head>
<body>
	<h2>회원가입</h2>
	<form action="/member/join" method="post" name="f">
	<table>
		<tr>
			<td>
				아이디 : <input type="text" name="id">
				<input type="button" value="중복체크" onclick="a()">
			</td>
		</tr>
		<tr>
			<td>
				비밀번호 : <input type="password" name="pwd">
			</td>
		</tr>
		<tr>
			<td>
				이름 : <input type="text" name="name">
			</td>
		</tr>
		<tr>
			<td>
				email : <input type="text" name="email">
			</td>
		</tr>
		<tr>
			<td>
				type : 
				<select name="type">
					<c:forEach var="type" items="${list }">
						<option>${type }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<input type="submit" value="회원가입" />
	</form>
</body>
</html>