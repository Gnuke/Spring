<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컨텐츠</title>
<script type="text/javascript">
	const a = () => {
		let pwd = prompt("비밀번호?", "");
		if( pwd == '${gb.pwd}' ){
			let cont = prompt("새 글 내용", "");
			if (cont === null){
				cont = "내용 없음";
				location.href = "/guestbook/edit?num=${gb.num}&content=" + cont;
			}else{
				location.href = "/guestbook/edit?num=${gb.num}&content=" + cont;
			}
		}else{
			alert('비밀번호가 틀렸습니다.');
		}
	}
	
	const b = () => {
		let pwd = prompt("비밀번호?", "");
		if( pwd == '${gb.pwd}' ){
			alert( '삭제 성공' );
			location.href = "/guestbook/del?num=${gb.num}";
		}else if( pwd == null ){
			location.href = "/guestbook/content?num=${gb.num}";
		}else{
			alert('비밀번호가 틀렸습니다.');
		}
	}
</script>
</head>
<body>
	<h3>상세 페이지</h3>
	<table border="1">
		<tr><th>글 번호</th><td>${gb.num }</td></tr>
		<tr><th>작성자</th><td>${gb.writer }</td></tr>
		<tr><th>작성일</th><td>${gb.wdate }</td></tr>
		<tr><th>내용</th><td>${gb.content }</td></tr>
		<tr>
			<th>변경</th>
			<td>
				<input type="button" value="수정" onclick="a()" />
				<input type="button" value="삭제" onclick="b()" />
			</td>
		</tr>
	</table>
</body>
</html>