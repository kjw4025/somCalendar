<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<link href="../css/main_style.css" rel="stylesheet">
</head>
<body>


	<h1>잊지마 마이펫</h1>
	<input type="button" class="button" value="마이페이지"
		onclick="location.href='<c:url value='/user/mypage'>
					   			<c:param name='userId' value="${userId}"/>
			 		 		</c:url>'" />
	<input type="button" class="button" value="로그아웃" style="float: right;"
		onclick="location.href='<c:url value='/user/logout'>
			<c:param name='userId' value="${userId}"/>
							</c:url>'" />

	<hr color="#C8C8FF">

	<div align="center">
		<table class="main_container">
			<tr>
				<td class="mainPet"><%@ include file="mainPet.jsp"%></td>
				<td rowspan='2' class="emptyContainer">
					<div class="empty">&nbsp; &nbsp;</div>
				</td>
				<td rowspan='2' class="mainCalendar"><jsp:include
						page="calendar.jsp" flush="false" /></td>
			</tr>
			<tr>
				<td class="dailyList_Container"><%@ include
						file="dailyList.jsp"%></td>
			</tr>
		</table>
	</div>



</body>
</html>