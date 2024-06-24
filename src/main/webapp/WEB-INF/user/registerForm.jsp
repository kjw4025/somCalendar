<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.dao.UserDAO"%>
<%@ page import="model.service.UserManager"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function validate(index){	
		if(index == 1){
			if(form.userId.value == ""){
				alert("아이디를 입력해주세요");	
				return false;
			}
			if(form.password.value == ""){
				alert("비밀번호를 입력해주세요");
				return false;
			}
	 		if(form.password.value != form.chpw.value){
				alert("입력하신 비밀번호와 다릅니다.");
				return false;
			} 
			if(form.mainUser.value == ""){
				alert("메인유저의 아이디값을 입력해주세요.");
				return false;
			}
			if(form.check.value == null){
				alert("개인정보 수집 및 이용동의에 체크를 해주세요.");
				return false;
			}
			if(form.mainUser.value == form.userId.value){
				form.check2.value = "yes";
			}
			if(form.check1.value == "no"){
				alert("아이디 중복체크가 안되었습니다.");
				return false;
			}
			if(form.check2.value == "no"){
				alert("메인유저 확인이 실행되지 않았습니다.");
				return false;
			}
		}
		else if(index == 2){
			if(form.userId.value == ""){
				alert("아이디를 먼저 입력해주세요");	
				return false;
			}
			else{
				var url = "<c:url value='/user/checkUser'/>";
				var options = "width=500, height=200, top=500, left=500, resizable=no, scrollbars=no";
				form.check1.value = "yes";
				window.open(url + "?userId=" + form.userId.value, "", options);
			}
		}	
		else{
			if(form.mainUser.value == ""){
				alert("메인유저를 먼저 입력해주세요");	
				return false;
			}
			
			if(form.mainUser.value != form.userId.value){
				var url = "<c:url value='/user/checkMain'/>";
				var options = "width=650, height=300, top=500, left=500, resizable=no, scrollbars=no";
				form.check2.value = "yes";
				window.open(url + "?mainUser=" + form.mainUser.value, "", options);
			} 
			
		}	
	}
</script>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Sign Up</title>
<link rel="stylesheet"
	href="<c:url value = '../css/signup_style.css' />" type="text/css" />
</head>
<body>
	<form name="form" method="POST" onsubmit="return validate(1);">
		<fieldset style="border: 3px solid #C8C8FF;">
			<legend>잊지마 마이펫</legend>
			<h1>SIGN UP</h1>
			<!-- 아이디 입력 -->
			<div id="check"></div>
			<div>
				<h3>아이디</h3>
				<input type="String" name="userId" id="userId" class="input_style"
					maxlength="20"> &nbsp;&nbsp; <input type="button"
					value="중복확인" class="idbtn" onclick="validate(2)"> <input
					type="hidden" name="check1" value="no">

			</div>
			<!-- 비밀번호 입력 -->
			<div>
				<h3>비밀번호</h3>
				<input type="password" name="password" id="pw" class="input_style"
					maxlength="20">
			</div>
			<!-- 비밀번호 재확인 입력 -->
			<div>
				<h3>비밀번호 재확인</h3>
				<input type="password" name="chpw" id="pwCheck" class="input_style"
					maxlength="20">
			</div>

			<!-- 성명 입력 -->
			<div>
				<h3>성명</h3>
				<input type="text" name="name" class="input_style" maxlength="20">
			</div>

			<!-- 메인유저 -->
			<div>
				<h3 class="list">메인유저</h3>
				<input type="text" name="mainUser" class="input_style"
					maxlength="20"> &nbsp;&nbsp; <input type="button"
					value="유저확인" class="idbtn" onclick="validate(3)"> <input
					type="hidden" name="check2" value="no">
			</div>

			<div class="tap">
				<label><input type="checkbox" value="agree" name="check">
					&nbsp; 개인정보 수집 및 이용 동의</label>
			</div>

			<div class="center">
				<input type="submit" value="회원가입" class="idbtn"> <input
					type="reset" value="취소" class="idbtn"
					onclick="location.href='<c:url value='/user/loginForm'></c:url>'" />
			</div>

		</fieldset>
	</form>
</body>
</html>