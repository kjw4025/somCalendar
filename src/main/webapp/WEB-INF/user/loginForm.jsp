<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>login</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<c:url value = '../css/login_style.css' />"
	type="text/css" />
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("btnSend").onclick = login;
	}
	function login() {
		if (form.userId.value == "") {
			alert("사용자 ID를 입력하십시오.");
			form.userId.focus();
			return false;
		}
		if (form.password.value == "") {
			alert("비밀번호를 입력하십시오.");
			form.password.focus();
			return false;
		}
		form.action = "<c:url value='/user/login' />";
		form.method = "POST";
		form.submit();
	}
</script>
</head>
<body>
	<form name="form">
		<fieldset style="border: 3px solid #C8C8FF;">
			<legend>잊지마 마이펫</legend>
			<h1>LOG IN</h1>
			<c:if test="${loginFailed}">
				<br>
				<font color="red"><c:out value="${exception.getMessage()}" /></font>
				<br>
			</c:if>
			<div class="div_set">
				<input type="text" name="userId" class="text" placeholder="아이디">
			</div>

			<div class="div_set">
				<input type="password" name="password" class="text"
					placeholder="비밀번호">
			</div>

			<button type="button" class="btn" id="btnSend">로그인</button>

			<div class="signup">
				<a href="<c:url value='/user/register' />" style="font-size: 30px;">sign
					up</a>
			</div>
		</fieldset>
	</form>
</body>
</html>