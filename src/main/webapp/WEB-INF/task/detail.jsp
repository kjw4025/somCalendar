<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Details</title>
<link rel="stylesheet" href="../css/detail_style.css">


</head>
<body>
	<form name="form" method="POST" class="detailform">
		<div class="set">
			<table>
				<tr>
					<td class="left">&nbsp;<input type="button" class="btn"
						value="back" onClick="history.back();" /></td>
					<td class="center">상세보기</td>
					<td class="right">&nbsp;<input type="button" class="btn"
						value="edit"
						onclick="location.href=
									'<c:url value='/task/update'>
							   			<c:param name='taskId' value="${task.taskId}"/>
						 		 	</c:url>'" />
					</td>
				</tr>
			</table>
		</div>

		<div class="set_high_height">
			<table class="s_table">
				<tr class="s_tr">
					<td class="s_td">일정 이름 : ${task.name} <input type="hidden"
						name="name" value="${task.name}" readonly></td>
				</tr>
				<tr class="s_tr">
					<td class="s_td">시작 날짜 : ${task.startdate} <input
						type="hidden" name="startdate" value="${task.startdate}" readonly></td>
				</tr>
				<tr class="s_tr">
					<td class="s_td">마지막 날짜 : ${task.enddate} <input type="hidden"
						name="enddate" value="${task.enddate}" readonly>
					</td>
				</tr>
				<tr class="s_tr">
					<td class="s_td">장소 : ${task.place} <input type="hidden"
						name="place" value="${task.place}" readonly>
					</td>
				</tr>
				<tr class="s_tr">
					<td class="s_td">시간 : <input type="hidden" name="hour"
						value="${task.hour}" readonly>${task.hour}시 <input
						type="hidden" name="minute" value="${task.minute}" readonly>
						${task.minute}분
					</td>
				</tr>
				<tr class="s_tr">
					<td class="s_td">메모 : ${task.memo} <input type="hidden"
						name="memo" value="${task.memo}" readonly /></td>
				</tr>
				<tr class="s_tr">
					<td class="s_td">펫 : ${pet.name} <input type="hidden"
						name="petId" value="${task.petId}" readonly></td>
				</tr>
			</table>
		</div>


	</form>
</body>
</html>