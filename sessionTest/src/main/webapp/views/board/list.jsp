<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
<script type="text/javascript">
const a = (num) => {
    let req = new XMLHttpRequest();

    req.onload = () => {
      let res = document.getElementById("res");
      let obj = JSON.parse(req.responseText);
      let txt = "글 상세내용<br />";
      txt += "num : " + obj.num + "<br />";
      txt += "writer : " + obj.writer + "<br />";
      txt += "wdate : " + obj.wdate + "<br />";
      txt += "title : " + obj.title + "<br />";
      txt += "content : " + obj.content + "<br />";
      res.innerHTML = txt;
    }

    //open()로 요청 설정
    req.open('get', '/board/getAjax?num='+num);

    //요청 전송
    req.send();
  }
  
const b = () => {
	let res = document.getElementById("res");
	res.innerHTML = "";
}
</script>
</head>
<body>
	<h3>게시판</h3>
	<a href="/board/todayList">오늘 읽은 글 목록</a>
	<select name="gettype">
		<option>작성자</option>
		<option>제목</option>
	</select>
	<input type="text" name="search">
	<input type="button" value="검색">
	<span id="res" style="position:absolute;top:200px;left:300px"></span>
	<table border="1">
		<tr><th>num</th><th>title</th><th>writer</th></tr>
		<c:forEach var="b" items="${list }">
			<tr>
				<td><a href="/board/detail?num=${b.num }">${b.num }</a></td>
				<td><span onmouseover="a(${b.num })" onmouseout="b()">${b.title }</span></td>
				<td>${b.writer }</td>
			</tr>
		</c:forEach>
	</table>
	<button type="button" onclick="location.href='/board/insert'">글작성</button>
	<button type="button" onclick="location.href='/'">뒤로가기</button>
</body>
</html>