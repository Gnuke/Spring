<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//console.log( "flag : " + ${flag} );
	window.onload = () => {
	if( '${msg}' =='' || '${msg}' == null )
		document.f.id.value = opener.document.f.id.value;
	}else{
		if(${flag}){
			let div = document.getElementById("div");
			let html = "<input type='button' value='아이디 사용' onclick='b()'>";
			div.innerHTML = html;
		}
	}
	
	const b = () => {
		opener.document.f.id.value = '${resultid }';
		close();
	}
</script>
</head>
<body>
	<h3>중복체크</h3>
	${msg }
	<form action="/member/idcheck" method="post" name="f">
		id : <input type="text" name="id" value="${resultid }">
		<input type="submit" value="중복체크">
	</form>
	<div id="div">
	
	</div>
</body>
</html>