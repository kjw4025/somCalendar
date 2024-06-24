<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/dailyList_style.css" type="text/css">

</head>
<body>
	<div style="position: relative" class="dailyList_form">

		<table class="set">
			<tr>
				<td class="center" style="font-size: 20px;">${introduce}</td>
			</tr>
		</table>

		<c:forEach var="t" items="${task}">
			<div class="form-check" style="font-size: 30px;">
				<input class="form-check-input" type="checkbox" value=""
					id="defaultCheck1"> <label class="form-check-label"
					for="defaultCheck1"> <a href="#"
					onclick="location.href=
					    	'<c:url value='/task/view'>
					   			<c:param name='taskId' value="${t.taskId}"/>
					   			<c:param name='date' value="20211120"/>
			 		 		</c:url>'">${t.name}
				</a>
				</label>
			</div>
		</c:forEach>

		<c:forEach var="p" begin="1" end="1" step="1">
			<div style="position: absolute; right: 10px; bottom: 10px;">

				<input type="button" class="button" value="추가"
					onclick="location.href=
									'<c:url value='/task/add'>
							   			<c:param name='userId' value="${userId}"/>
						 		 	</c:url>'" />
			</div>
		</c:forEach>



	</div>




























	<!--  	<div style="position:relative" class="dailyList_form">
		<form name="form" method="POST">
				<table class="set">
					<tr>
						<td class="center">오늘 날짜</td>
					</tr>
				</table>
				<div class="set_height">
					<div class="form-check">
					  <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
					  <label class="form-check-label" for="defaultCheck1">
					    <a href="#" onclick="window.open('<c:url value='/task/update' />', 'test', 'width=600, height=1000, resizable=no, scrollbars=no')">병원가기</a>
					  </label>
					</div>
				</div>	
				
				<div style="position: absolute; right: 10px; bottom: 10px;">
					<!-- 선택 삭제 버튼 기능은 보류! -->
	<!-- <input type="button" value="선택삭제" /> -->


	</form>
	</div>
</body>
</html>