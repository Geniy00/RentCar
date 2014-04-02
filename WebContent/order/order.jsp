<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show order</title>
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
				<table align="center" width="85%">
					
					<tr align="center"><td width="100">Order ID:</td><td width="200">${order.id}</td></tr>
					<tr align="center"><td>User:</td><td>${userfk.login} (${userfk.firstName} ${userfk.lastName})</td></tr>
					<tr align="center"><td>Car:</td><td>${carfk.model} ${carfk.mark}</td></tr>
					<tr align="center"><td>Location:</td><td>${carfk.country}, ${carfk.city}, ${carfk.street}</td></tr>	
					<tr align="center"><td>Status:</td><td>${order.status}</td></tr>	
					<tr align="center"><td>Comment:</td><td>${order.comment}</td></tr>			
					<tr align="center"><td>Cost:</td><td>${order.cost}</td></tr>
					<tr align="center"><td>Addition cost:</td><td>${order.additionalCost}</td></tr>
					<tr align="center"><td>Repair cost:</td><td>${order.repairCost}</td></tr>
					<tr align="center"><td><b>Total cost:</b></td>
					<td><b>${order.cost + order.additionalCost + order.repairCost}</b></td></tr>
					
					<c:if test="${user.permission=='ADMIN'}">						
						<tr><td></td>
							<td> 							
								<br>
								<font size="10"></font>
								<b>
								<a href="cmd?command=EDIT_ORDER&idorder=${order.id}">edit</a><br>
								delete
								</b>
							</td>
						</tr>
					</c:if>
					
					<tr>
						<td colspan="2"><c:if test="${message!=null}">Message: ${message}</c:if> </td>
					</tr>
					<tr>						
						<td colspan="2" align="center">
						<br><br>
						<a href="cmd?command=LOAD_ORDER_LIST">Back</a></td>
						
					</tr>
				</table>
	</table>

</body>
</html>