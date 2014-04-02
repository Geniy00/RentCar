<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show request</title>
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
				<form action="cmd?command=EXAMINE_REQUEST" method="post">
				<table align="center" width="85%">
					
					<tr align="center"><td width="100">Request ID:</td><td width="200">${request.id}</td></tr>
					<tr align="center"><td>User:</td><td>${userfk.login} (${userfk.firstName} ${userfk.lastName})</td></tr>
					<tr align="center"><td>Car:</td><td>${carfk.model} ${carfk.mark}</td></tr>
					<tr align="center"><td>Location:</td><td>${carfk.country}, ${carfk.city}, ${carfk.street}</td></tr>					
					<tr align="center"><td>Date:</td><td>${request.beginDate} to ${request.endDate}</td></tr>
					<tr align="center"><td>Request message:</td><td>${request.requestMessage}</td></tr>
					<c:if test="${request.responseMessage != null}">
						<tr align="center"><td>Response message:</td><td>${request.responseMessage}</td></tr>
					</c:if>
					<tr align="center"><td>Cost:</td><td>${request.cost}</td></tr>
					<tr align="center"><td>Status:</td><td>${request.status}</td></tr>
					<c:if test="${user.permission=='ADMIN'}">						
						<tr>						
							<td colspan="2"><br><br>Response message:* 
							<input type="text" name="response_message" size="70" value="Your request accepted. Pay order please!"/><br><br><br></td>
						</tr>
						<tr>
							<td align="right">
								<br>
								<input type="hidden" name="idrequest" value="${request.id}" />
								<input type="submit" name="action" value="REFUSE"/>
							</td>
							<td align="center">
								<br>
								<input type="submit" name="action" value="ACCEPT"/>
							</td>
						</tr>
					</c:if>
					<tr>
						<td colspan="2"><c:if test="${message!=null}">Message: ${message}</c:if> </td>
					</tr>
					<tr>						
						<td colspan="2" align="center">
						<br><br>
						<a href="cmd?command=LOAD_REQUEST_LIST">Back</a></td>
						
					</tr>
				</table>
				</form>
	</table>

</body>
</html>