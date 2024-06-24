<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.dao.UserDAO"%>
<%@ page import="model.service.UserManager"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/invite_style.css">
<meta charset="EUC-KR">
<title>checkUser</title>
</head>
<body>
	<%
	String id = request.getParameter("mainUser");
	UserManager m = UserManager.getInstance();
	User user = null;
	try {
		user = m.findUser(request.getParameter("mainUser"));
	%>
	<h2 style="align: center; margin-left: 70px; margin-top: 30px;"><%=id%>는
		존재하는 메인유저입니다.
	</h2>
	<h2 style="align: center; margin-left: 150px;">(회원가입 가능)</h2>
	<%
	} catch (Exception e) {
	%>
	<h2 style="align: center; margin-left: 70px; margin-top: 30px;"><%=id%>는
		존재하지 않는 메인유저입니다.
	</h2>
	<h2 style="align: center; margin-left: 150px;">(회원가입 불가능)</h2>
	<%
	}
	%>
	<input type="button"
		style="width: 120px; height: 40px; align: center; margin-left: 190px; margin-top: 30px;"
		value="확인" onclick="window.close();">
</body>
</html>

