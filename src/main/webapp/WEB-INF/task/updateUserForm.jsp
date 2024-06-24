<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateUserForm</title>
<link rel="stylesheet" href="../css/updateUserForm_style.css">

</head>
<body>
	<div class="back">
		<form name="form" method="POST">

			<div class="set">
				<table>
					<tr>
						<td class="left">&nbsp; back</td>
						<td class="center">개인정보수정</td>
						<td class="right">save &nbsp;</td>
					</tr>
				</table>
			</div>

			<div class="set_height">
				<div class="userImage">
					<img src="../image/share.png" class="rounded" alt="사용자이미지">
				</div>
				<h3>이름</h3>
				<input type="text" name="name" class="input_style">
				<h3>email</h3>
				<input type="text" name="email" class="input_style">
				<h3>phone</h3>
				<input type="text" name="phone" class="input_style">
				<h3>아이디</h3>
				<input type="text" name="id" class="input_style" readonly>
				<h3>비밀번호 수정</h3>
				현재 비밀 번호 : <input type="password" name="currentPwd"
					class="input_style"><br> 바꿀 비밀 번호 : <input
					type="password" name="updatePwd" class="input_style">
			</div>


		</form>
	</div>
</body>
</html>