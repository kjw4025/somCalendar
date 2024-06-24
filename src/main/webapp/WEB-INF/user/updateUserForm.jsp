<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateUserForm</title>
<link rel="stylesheet" href="../css/updateUserForm_style.css">
<script type="text/javascript">
	function validate() {
		if (form.name.value == "") {
			alert("이름을 입력해주세요.");
			form.name.focus();
			return false;
		}
		if (form.password.value == "") {
			alert("새로운 비밀번호를 입력해주세요.");
			form.password.focus();
			return false;
		}
		form.submit();
	}
</script>
</head>
<body>
	<div class="back">
		<form name="form" method="POST"
			action="<c:url value='/user/update' />">

			<div class="set">
				<table>
					<tr>
						<td class="left">&nbsp; <input type="button" class="btn"
							value="back" onClick="history.back();" /></td>
						<td class="center">개인정보수정</td>
						<td class="right">&nbsp;<input type="button" class="btn"
							value="save" onclick="validate()" /></td>
					</tr>
				</table>
			</div>

			<div class="set_height">
				<p class="font">이름</p>
				<input type="text" name="name" class="input_style"
					value="${user.name}">
				<p class="font">email</p>
				<input type="email" name="email" class="input_style"
					value="${user.email}">
				<p class="font">phone</p>
				<input type="text" name="phone" class="input_style"
					value="${user.phone}">
				<p class="font">아이디</p>
				<p>${user.userId }</p>
				<input type="hidden" name="userId" class="input_style"
					value="${user.userId }" readonly>
				<p class="font">비밀번호 수정</p>
				현재 비밀 번호 : <input type="password" name="currentPwd"
					value="${user.password}" class="input_style"><br> 바꿀
				비밀 번호 : <input type="password" name="password" class="input_style">
			</div>


		</form>
	</div>
</body>
</html>