<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>My Page</title>
<link rel=stylesheet href="<c:url value='/css/mypage_style.css' />"
	type="text/css">
</head>
<body>
	<div class="back">
		<h1>My Page</h1>
		<form name="form1" method="POST" action="<c:url value='/user/view'/>">
			<fieldset style="border: 3px solid #D8BFD8;">
				<legend class="legend">
					<strong>Account</strong>
				</legend>
				<div style="float: right;">
					<img src="<c:url value='/images/edit.jpg' />" class="button"
						onclick="location.href='<c:url value='/user/update'/>'" />

				</div>

				<table name="account" class="table">
					<tr>
						<td><label>User Name: </label></td>
						<td>${user.name}</td>
					</tr>
					<tr>
						<td><label>Email: </label></td>
						<td>${user.email}</td>
					</tr>
					<tr>
						<td><label>Phone: </label></td>
						<td>${user.phone}</td>
					</tr>
				</table>
			</fieldset>
		</form>

		<form name="form2" method="POST">
			<fieldset style="border: 3px solid #F0E68C;">
				<legend class="legend">
					<strong>Pet</strong>
				</legend>
				<div style="float: right;">
					<img src="<c:url value='/images/add_y.jpg' />" class="button"
						onclick="location.href='<c:url value='/pet/registerfirst'><c:param name='mainUser' value="${user.mainUser}"/>
						 		 	</c:url>'" />
				</div>
				<table name="pet" class="table">
					<tr class="center">
						<c:forEach var="p" items="${pet}">
							<td class="myPet" style="border: 3px solid #F0E68C;">
								<div class="myPetBtn_div">
									<img src="<c:url value='/images/edit_y.jpg' />"
										onclick="location.href=
									'<c:url value='/pet/update'><c:param name='mainUser' value="${user.mainUser}"/>
							   			<c:param name='petId' value="${p.petId}"/>
							   			<c:param name='userId' value="${user.userId}"/>
						 		 	</c:url>'" />
									<input type="button" value="x" class="delete"
										onclick="location.href='<c:url value='/pet/delete'>
							   			<c:param name='petId' value="${p.petId}"/>
							   			<c:param name='userId' value="${user.userId}"/>
						 		 	</c:url>'"
										style="color: #F0E68C;" />
								</div>
								<div class="myPet_div">
									<br>${p.name} (${p.sex}) <br>${p.species} <br>${p.weight}

								</div>
							</td>
						</c:forEach>

					</tr>
				</table>
			</fieldset>
		</form>


		<form name="form3" method="POST">
			<fieldset style="border: 3px solid #B0C4DE;">
				<legend class="legend">
					<strong>Sharing</strong>
				</legend>
				<div style="float: right;">
					<img src="<c:url value='/images/add_b.jpg' />" class="button"
						onclick="window.open('<c:url value='/user/invite'><c:param name='userId' value="${user.userId}"/>
						 		 	</c:url>' , 'test', 'width=800, height=950, resizable=no, scrollbars=no')" />
				</div>

				<table name="sharing" class="table">
					<tr class="center">
						<c:forEach var="s" items="${shareUser}">
							<td class="myPet" style="border: 3px solid #B0C4DE;">
								<div class="myPetBtn_div">
									<input type="button" value="x" class="delete"
										onclick="location.href='<c:url value='/user/delete'>
							   			<c:param name='userId' value="${s}"/> <c:param name='currentId' value="${user.userId}"/>
						 		 	</c:url>'"
										style="color: #B0C4DE;" />
								</div>
								<div class="myPet_div">
									<br>${s}

								</div>
							</td>
						</c:forEach>

					</tr>
				</table>
			</fieldset>
		</form>
		<input type="button" value="back" class="btn"
			onclick="location.href='<c:url value='/user/login'><c:param name='userId' value="${user.userId}"/>
						 		 	<c:param name='password' value="${user.password}"/></c:url>'" />
	</div>
</body>
</html>
