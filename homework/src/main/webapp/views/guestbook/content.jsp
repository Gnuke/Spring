<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컨텐츠</title>
</head>
<body>
	<h3>상세 페이지</h3>
	${msg}<br />
	${gd.num }<br />
	<form action="/guest/edit" method="post">
		writer : <input type="text" name="writer" value="${gd.writer }" readonly><br />
		<br/>
		<fieldset>
			<label>글 내용</label>
		</fieldset>
		<br />
		<input type="submit" value="수정">
		<input type="button" value="삭제" onclick="a()">
	</form>
</body>
</html>