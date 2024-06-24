<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AddEdit</title>
<link rel="stylesheet" href="../css/addEdit_style.css">

<!-- datepicker부분 -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {

		$("#datepicker0,#datepicker1,#datepicker2")
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

		$('#datepicker').datepicker('setDate', 'today');
	});
</script>
<script type="text/javascript">
	function validate() {
		if (form.name.value == "") {
			alert("체크리스트 제목을 적어주세요.");
			return false;
		}
		if (form.startdate.value == "" || form.enddate.value == "") {
			alert("날짜를 선택해주세요.")
			return false;
		}
		if (form.startdate.value > form.enddate.value) {
			alert("시작날짜가 더 큽니다.");
			return false;
		}
		if (form.hour.value > 24 || form.hour.value < 0) {
			alert("범위를 벗어났습니다. (가능범위: 0~24)");
			return false;
		}
		if (form.minute.value > 60 || form.minute.value < 0) {
			alert("범위를 벗어났습니다. (가능범위: 0~60)");
			return false;
		}

		var check = $(":radio[name='selectPet']:checked").val();
		if (check == undefined) {
			alert("펫을 선택해주세요.");
			return false;
		}
		form.submit();
	}
</script>
</head>
<body>
	<form name="form" method="POST"
		action="<c:url value='/task/add'>
									<c:param name='userId' value="${user.userId}"/>  
									</c:url>">
		<div class="set">
			<table>
				<tr>
					<td class="left">&nbsp;<input type="button" class="btn"
						value="back" onClick="history.back();" /></td>
					<td class="center">일정추가</td>
					<td class="right">&nbsp;<input type="button" id="btnSend"
						class="btn" value="save" onclick="validate()"></td>
				</tr>
			</table>
		</div>

		<div class="set_height">
			<p class="font">일정 이름</p>
			<input type="text" name="name" class="input_style"> <br>
			<p class="font">시작날짜</p>
			<input type="date" id="datepicker1" name="startdate"
				class="input_style" readonly>
			<p class="font">마지막 날짜</p>
			<input type="date" name="enddate" id="datepicker2"
				class="input_style" readonly>

			<p class="font">장소</p>
			<input type="text" name="place" id="id" class="input_style"
				class="input_style">
			<p class="font">시간 설정</p>
			<p>
				<input type="text" name="hour" min="0" max="24" class="input_style">시
				<input type="text" name="minute" min="0" max="60"
					class="input_style">분
			</p>

			<p class="font">메모</p>
			<input type="text" name="memo" id="id" class="input_style"
				class="input_style">


			<p class="font">펫</p>
			<c:forEach var="pet" items="${pet}">
				<input type='radio' class="input_style2" name='selectPet'
					value='${pet.petId}' /> ${pet.name} 	
					</c:forEach>

		</div>

	</form>
</body>
</html>