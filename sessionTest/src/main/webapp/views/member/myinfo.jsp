<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보</title>
</head>
<body>
	<h3>내정보</h3>
	<table>
		<tr>
			<td>
				아이디 : ${myinfo.id }
			</td>
		</tr>
		<tr>
			<td>
				이름 : ${myinfo.name }
			</td>
		</tr>
		<tr>
			<td>
				email : ${myinfo.email }
			</td>
		</tr>
		<tr>
			<td>
				상태 : ${myinfo.type }
			</td>
		</tr>
	</table>
	<button type="button" onclick="location.href='/member/edit'">정보수정</button>
	<button type="button" onclick="location.href='/'">취소</button>
</body>
</html>