<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
</head>
<body>
<c:if test="${sessionScope.loginId == null }">
	<button type="button" onclick="location.href='/test/loginAjax'">로그인</button>
	<button type="button" onclick="location.href='/member/join'">회원가입</button>
</c:if>
<c:if test="${sessionScope.loginId != null }">
	${sessionScope.loginId }님의 타입은 ${sessionScope.type }입니다.<br />
	<button type="button" onclick="location.href='/test/logout'">로그아웃</button>
	<button type="button" onclick="location.href='/member/myinfo'">내정보확인</button>
	<button type="button" >탈퇴</button>
	<button type="button" >게시판</button>
</c:if><br />
	${msg }<br />
</body>
</html>