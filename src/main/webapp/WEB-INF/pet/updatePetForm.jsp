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
			alert("반려동물의 이름을 입력해주세요");
			form.name.focus();
			return false;
		}
		if (form.species.value == "") {
			alert("반려동물의 종을 입력해주세요");
			form.species.focus();
			return false;
		}
		if (form.weight.value == "") {
			alert("반려동물의 몸무게를 입력해주세요");
			form.weight.focus();
			return false;
		}
		form.submit();
	}
</script>
</head>
<body>
	<div class="back">
		<form name="form" method="POST"
			action="<c:url value='/pet/update'>
							   			<c:param name='petId' value="${pet.petId}"/>
						 		 	</c:url>">
			<div class="set">
				<table>
					<tr>
						<td class="left">&nbsp; <input type="button" class="btn"
							value="back" onClick="history.back();" /></td>
						<td class="center">펫정보수정</td>
						<td class="right">&nbsp; <input type="button" class="btn"
							value="save" onclick="validate()" /></td>
					</tr>
				</table>
			</div>

			<div class="set_height">

				<p class="font">반려동물 이름</p>
				<input type="text" name="name" class="input_style"
					value="${pet.name}">
				<p class="font">성별 (${pet.sex})</p>
				<input type='radio' name='sex' value='man' />남 <input type='radio'
					name='sex' value='woman' />여
				<p class="font">반려동물 종</p>
				<input type="text" name="species" class="input_style"
					value="${pet.species}">
				<p class="font">몸무게</p>
				<input type="text" name="weight" class="input_style"
					value="${pet.weight}">
				<p class="font">주인(메인유저)</p>
				<p>${user.name}</p>
				<input type="hidden" name="mainUser" class="input_style"
					value="${user.userId}" readonly>
			</div>

		</form>
	</div>
</body>
</html>