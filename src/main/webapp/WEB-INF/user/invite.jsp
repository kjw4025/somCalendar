<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/invite_style.css">
<meta charset="EUC-KR">
<title>inviteForm</title>
</head>
<body>
	<form name="form" method="POST">
		<div class="set">
			<table>
				<tr>
					<td class="w">&nbsp;</td>
					<td class="center">공유사용자 추가</td>
					<td class="w">&nbsp;</td>
				</tr>
			</table>
		</div>
		<div>
			<h2>공유사용자에게 이 두 가지 정보를 알려주세요.</h2>
			<br> <br>
			<h2>
				회원가입링크: <a href="http://localhost:1010/project2/user/loginForm">http://localhost:1010/project2/user/loginForm</a>
			</h2>
			<br>
			<h2>
				mainUser:
				<%=(String) session.getAttribute("userId")%></h2>
			<br> <br> <br>
		</div>
		<input type="button" class="idbtn" value="확인"
			onclick="window.close();" />
	</form>
</body>
</html>

