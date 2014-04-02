<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit order</title>
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
						
						<tr align="center"><td width="100">Order ID:</td><td width="200">${order.id}</td></tr>
						<tr align="center"><td>User:</td><td>${userfk.login} (${userfk.firstName} ${userfk.lastName})</td></tr>
						<tr align="center"><td>Car:</td><td>${carfk.model} ${carfk.mark}</td></tr>
						<tr align="center"><td>Location:</td><td>${carfk.country}, ${carfk.city}, ${carfk.street}</td></tr>	
						<tr align="center"><td>Status:</td>
							<td align="left">
								<input type="radio" name="status" value="OPENED" 
									<c:if test="${order.status=='OPENED'}">checked="checked"</c:if>
								/>opened<br>
								<input type="radio" name="status" value="ACTIVE"
									<c:if test="${order.status=='ACTIVE'}">checked="checked"</c:if>
								/>active<br>	
								<input type="radio" name="status" value="CLOSED"
									<c:if test="${order.status=='CLOSED'}">checked="checked"</c:if>
								/>closed<br>
							</td>
						</tr>	
						<tr align="center"><td>Comment:</td>
							<td><input type="text" name="comment" value="${order.comment}" size="50"></td>
						</tr>			
						<tr align="center"><td>Cost:</td>
							<td><input type="text" name="cost" value="${order.cost}"></td>
						</tr>
						<tr align="center"><td>Addition cost:</td>
							<td><input type="text" name="additional_cost" value="${order.additionalCost}"></td>
						</tr>
						<tr align="center"><td>Repair cost:</td>
							<td><input type="text" name="repair_cost" value="${order.repairCost}"></td>
						</tr>
						<tr align="center"><td><b>Total cost:</b></td>
						<td><b>${order.cost + order.additionalCost + order.repairCost}</b></td></tr>
						<tr>
							<td colspan="2" align="center">
								<br><input type="submit" value="Save"/> <br>
								<c:if test="${message!=null}">Message: ${message}</c:if>
							</td>
						</tr>		
						<tr>						
							<td colspan="2" align="center">
							<br><br>
							<a href="cmd?command=LOAD_ORDER&idorder=${order.id}">Back</a></td>
							
						</tr>
					</table>
				</form>
	</table>

</body>
</html>