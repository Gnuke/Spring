<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
	<h1>수정 폼</h1>
	<form action="/member/edit" method="post">
	<table>
		<tr>
			<td>
				아이디 : ${myinfo.id }
				<input type="hidden" name="id" value="${myinfo.id }">
				<input type="hidden" name="pwd" value="${myinfo.pwd }">
			</td>
		</tr>
		<tr>
			<td>
				이름 : <input type="text" name="name" placeholder="${myinfo.name }">
			</td>
		</tr>
		<tr>
			<td>
				email : <input type="text" name="email" placeholder="${myinfo.email }">
			</td>
		</tr>
		<tr>
			<td>
				상태 : 
				<select name="type">
					<c:forEach var="type" items="${list }">
						<option>${type }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<input type="submit" value="정보수정" />
	<button type="button" onclick="location.href='/'">취소</button>
	</form>
</body>
</html>