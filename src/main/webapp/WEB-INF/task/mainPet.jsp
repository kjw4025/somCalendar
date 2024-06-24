<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/mainPet_style.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker")
				.datepicker(
						{
							dateFormat : 'yymmdd',
							showOtherMonths : true,
							showMonthAfterYear : true,
							changeYear : true,
							changeMonth : true,
							showOn : "both",
							buttonImage : "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
							buttonImageOnly : true,
							buttonText : "선택",
							yearSuffix : "년",
							monthNamesShort : [ '1월', '2월', '3월', '4월', '5월',
									'6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
							monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월',
									'7월', '8월', '9월', '10월', '11월', '12월' ],
							dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
							dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일',
									'금요일', '토요일' ],
							minDate : "-5Y",
							maxDate : "+5y"
						});

		$('#datepicker1').datepicker('setDate', 'today');
	});
</script>
</head>
<body>
	<form name="form" method="POST"
		action="<c:url value='/task/select'>
											<c:param name='year' value="${year}"/>
											<c:param name='month' value="${month}"/>
											<c:param name='syear' value="${syear}"/>
											<c:param name='smonth' value="${smonth}"/>
											</c:url>">
		<table class="mainPet_set">
			<tr>
				<c:forEach var="pet" items="${pet}">
					<td><input type='radio' name='selectPet' value='${pet.petId}' />${pet.name}
					</td>
				</c:forEach>
			</tr>
		</table>
		<input type="text" id="datepicker" class="input_style" name="date">
		<input type="hidden" name="userId" value="${userId}"> <input
			type="submit" value="조회" class="idbtn">
	</form>
</body>
</html>