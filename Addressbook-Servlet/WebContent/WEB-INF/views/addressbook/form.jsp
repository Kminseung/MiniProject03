<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 주소 추가</title>
</head>
<body>
	<h1>주소록 Servlet</h1>
	
	<h3>새 주소 등록</h3>
	
	<form action="<c:url value="/new"/>" method="POST">
		<input type="hidden" name="action" value="insert" />
		
		<label for="name">이름</label>
		<input type="text" name="name" id="name" />
		
		<br/>
		
		<label for="hp">휴대전화</label>
		<input type="text" name="hp" id="hp" />
		
		<br/>
		
		<label for="tel">집 전화</label>
		<input type="text" name="tel" id="tel" />
		
		<input type="submit" value="주소 등록" />
	</form>
	
	<p>
		<a href="<c:url value="/new"/>">목록 보기</a>
	</p>
</body>
</html>