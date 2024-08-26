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
		//비동기 요청 객체
		let req = new XMLHttpRequest();
		
		//비동기 요청에 대한 응답이 오면 자동 호출될 핸들러 등록
		//받은 응답은 비동기 객체의 responseText 속성에 저장됨
		//res: {"flag":true}
		req.onload = () => {
			let res = document.getElementById("res");
			let msg = '중복된 아이디';
			let obj = JSON.parse(req.responseText);
			
			if(obj.flag){
				msg = '사용가능한 아이디';
			}
			res.innerHTML = msg;
		}
		
		let id = document.getElementById("id").value;
		//전송할 파라메터 정의
		let params = 'id=' + id;
		
		//open()로 요청 설정
		req.open( 'get', '/member/idcheckAjax?'+params);
		
		//요청 전송
		req.send(params);
	}
</script>
</head>
<body>
	<h2>회원가입</h2>
	<form action="/member/join" method="post" name="f">
	<table border="1">
		<tr>
			<td>
				아이디 : <input type="text" name="id" id="id">
				<input type="button" value="중복체크" onclick="a()"><div id="res"></div>
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