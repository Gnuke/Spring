<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member</title>
</head>
<body>
	<h3>hello spring</h3>
	<button type="button" onclick="location.href='/mem/join'">회원가입</button>
	<button type="button" onclick="location.href='/mem/get'">검색</button>
	<button type="button" onclick="location.href='/mem/getall'">전체 검색</button>
	<button type="button" onclick="location.href='/mem/edit'">수정</button> 
	<button type="button" onclick="location.href='/mem/del'">삭제</button>
	<br/>
	${m1.id} / ${m1.pwd} / ${m1.name} / ${m1.email}<br />
	<hr />
	<a href="/city/info">도시 검색</a><br />
	<a href="/city/add">도시 추가</a><br />
	<a href="/city/list">도시 목록</a><br />
</body>
</html>