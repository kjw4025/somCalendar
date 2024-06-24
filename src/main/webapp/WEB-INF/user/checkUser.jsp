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
	String id = request.getParameter("userId");
	UserManager m = UserManager.getInstance();
	User user = null;
	try {
		user = m.findUser(request.getParameter("userId"));
	%>
	<h2 style="align: center; margin: 50px;"><%=id%>는 이미 사용중입니다.
	</h2>
	<%
	} catch (Exception e) {
	%>
	<h2 style="align: center;"><%=id%>는 사용가능한 아이디입니다.
	</h2>
	<%
	}
	%>
	<input type="button"
		style="width: 80px; height: 40px; align: center; margin-left: 115px;"
		value="확인" onclick="window.close();">
</body>
</html>

