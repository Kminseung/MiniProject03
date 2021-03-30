<%@page import="com.bit.servlet.dao.PhoneVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전화번호 관리 웹 서비스</title>
</head>
<body>
	<h1>주소록 Servlet</h1>
	
	<h3>목록<c:if test="${ keyword != null }">(검색어: ${ keyword })</c:if></h3>
	
	<form action="<c:url value = "/new?a=search"/>" method="POST">
		<label for="keyword">검색어</label>
		<input type="text" name="keyword" />
		<input type="submit" value="검색" />
	</form>
	
	</br>
	
	<div style="text-align:center">
	<table border=1 width=1000>
		<thead>
			<tr style="background:lightgray" >
				<th>이름</th><th>휴대폰번호</th><th>집전화</th><th>도구</th>
			</tr>
		</thead>
		
		<c:forEach items="${ list }" var="vo">
		<tbody>
			<tr>
				<td>${ vo.name }</td><td>${ vo.hp }</td><td>${ vo.tel }</td>
				<td>
					<form action="<c:url value="/new"/>">
						<input type="hidden" name="a" value="delete" />
						<input type="hidden" name="id" value="${ vo.id }"/>
						<input type="submit" value="삭제" onclick="return confirm('삭제하시겠습니까?')"/>
					</form>
				</td>
			</tr>
		</tbody>
		</c:forEach>
	</table>
	</div>
	
	
	
	<p>
		<a href="<c:url value="/new?a=form" />">새 주소 추가</a>
	</p>
</body>
</html>