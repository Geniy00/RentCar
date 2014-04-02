<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show request list</title>
</head>
<body>
	<table width="90%">
		<tr >
			<td width="200"></td>
			<td align="right">
				<c:import url="/frames/top.jsp" />
			</td>
		</tr>
		<tr>
			<td>
				<c:import url="/frames/controlpanel.jsp" />
			</td>
			<td>
				<br><br><br>
				<table align="center" width="85%">
					<tr bgcolor="BABFFF" align="center">
						<td width="200">ID request</td>
						<td width="200">User id</td>
						<td width="200">Car id</td>
						<td width="200">Begin date</td>
						<td width="200">End date</td>
						<td width="200">Passport</td>
						<td width="200">Cost</td>
						<td width="200">Status</td>
						<td width="50">show</td>
					</tr>
					<c:forEach var="request" items="${requests}">
						<tr align="center" 
							<c:if test="${request.status=='PENDING'}">bgcolor="FFFF8C"</c:if>
							<c:if test="${request.status=='ACCEPTED'}">bgcolor="BEFFBA"</c:if>
							<c:if test="${request.status=='REFUSED'}">bgcolor="FFBABA"</c:if>
						>
							<td>${request.id}</td>
							<td>${request.userFK}</td>
							<td>${request.carFK}</td>
							<td>${request.beginDate}</td>
							<td>${request.endDate}</td>
							<td>${request.passport}</td>
							<td>${request.cost}</td>
							<td>${request.status}</td>
							<td>
								<a href="cmd?command=LOAD_REQUEST&idrequest=${request.id}">show</a>
							</td>
						</tr>	
					</c:forEach>
				</table>
				
				
			</td>			
		</tr>
	</table>

</body>
</html>