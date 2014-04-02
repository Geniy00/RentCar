<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new request</title>
</head>
<body>
	<table width="90%">
		<tr >
			<td width="15%"></td>
			<td align="right">
				<c:import url="/frames/top.jsp" />
			</td>
		</tr>
		<tr>
			<td>
				<c:import url="/frames/controlpanel.jsp" />
			</td>
			<td align="center">
				<br><br><br>
				<form action="" method="post">
					<table align="center" width="85%">
						<tr align="center"><td width="70">User:</td><td width="250">${user.firstName} ${user.lastName}</td></tr>
						<tr align="center">
							<td>Mark:</td><td>${car.mark}</td>
						</tr>
						<tr align="center">
							<td>Model:</td><td>${car.model}</td>
						</tr>
						<tr align="center">
							<td>Begin date:</td><td><input type="text" name="begin_date" value="${request.beginDate }"/></td>
						</tr>
						<tr align="center">
							<td>End date:</td><td><input type="text" name="end_date" value="${request.endDate}"/></td>
						</tr>
						<tr align="center">
							<td>Passport:</td><td><input type="text" name="passport" value="${request.passport}"/></td>
						</tr>
						<tr align="center">
							<td>Request message:</td><td><input type="text" height="60" name=request_message value="${request.requestMessage}"/></td>
						</tr>
						<tr align="center"><td>Cost:</td>
							<td>${request.cost}</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<br>
								<input type="hidden" name="idcar" value="${car.id}" />
								<c:if test="${request.passport==''}">
									<input type="submit" value="Send request"/> <br>
								</c:if>
								<c:if test="${message!=null}">Message: ${message}</c:if>
							</td>
						</tr>	
					</table>
				</form>
				<br><br>
				<div align="center"><a href="cmd?command=LOAD_CAR&idcar=${car.id}">Back</a></div>
			</td>			
		</tr>
	</table>

</body>
</html>