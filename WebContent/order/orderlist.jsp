<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show order list</title>
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
						<td width="200">Order ID</td>
						<td width="200">Request id</td>
						<td width="200">Comment</td>
						<td width="200">Status</td>
						<td width="200">Total cost</td>
						<td width="50">edit</td>
					</tr>
					<c:forEach var="order" items="${orders}">
						<tr align="center" 
							<c:if test="${order.status=='OPENED'}">bgcolor="FFFF8C"</c:if>
							<c:if test="${order.status=='ACTIVE'}">bgcolor="BEFFBA"</c:if>
							<c:if test="${order.status=='CLOSED'}">bgcolor="FFBABA"</c:if>
						>
							<td>${order.id}</td>
							<td>${order.requestFK}</td>
							<td>${order.comment}</td>
							<td>${order.status}</td>
							<td>${order.cost + order.additionalCost + order.repairCost}</td>
							<td>
								<a href="cmd?command=LOAD_ORDER&idorder=${order.id}">show</a>
							</td>
						</tr>	
					</c:forEach>
				</table>
				
				
			</td>			
		</tr>
	</table>

</body>
</html>