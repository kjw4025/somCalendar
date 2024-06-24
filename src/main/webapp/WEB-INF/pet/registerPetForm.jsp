<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updatePetForm</title>
<link rel="stylesheet" href="../css/updatePetForm_style.css">
<script>
	function validate() {
		if (form.name.value == "") {
			alert("반려동물 이름을 입력해주세요.");
			form.name.focus();
			return false;
		}
		form.submit();
	}
</script>
</head>
<body>
	<div class="back">
		<form name="form" method="POST"
			action="<c:url value='/pet/register'/>">
			<div class="set">
				<table>
					<tr>
						<td class="left">&nbsp; <input type="button" class="btn"
							value="back" onClick="history.back();" /></td>
						<!-- 뒤로가기 연결 -->
						<td class="center">펫 추가</td>
						<td class="right">&nbsp;<input type="button" class="btn"
							value="save" onclick="validate()" /> 
					</tr>
				</table>
			</div>

			<div class="set_height">
				<h3>반려동물 이름</h3>
				<input type="text" name="name" class="input_style">
				<h3>반려동물 종</h3>
				<input type="text" name="species" class="input_style">
				<h3>성별</h3>
				<input type='radio' name='sex' value='man' />남 <input type='radio'
					name='sex' value='woman' />여
				<h3>몸무게</h3>
				<input type="text" name="weight" class="input_style">
				<h3>주인(메인유저)</h3>
				<p><%=request.getParameter("mainUser")%></p>
				<input type="hidden" name="mainUser"
					value="<%=request.getParameter("mainUser")%>" class="input_style"
					readonly>

			</div>

		</form>
	</div>
</body>
</html>