<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show car list</title>
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
						<td width="80">Code</td>
						<td width="100">Mark</td>
						<td width="100">Model</td>
						<td width="100">Country</td>
						<td width="100">City</td>
						<td width="100">Price</td>
						<td width="100">Status</td>
						<td width="50">show</td>
					</tr>
					<c:forEach var="car" items="${cars}">
						<tr align="center" 
							<c:if test="${car.status=='AVAILABLE'}">bgcolor="BEFFBA"</c:if>
							<c:if test="${car.status!='AVAILABLE'}">bgcolor="FFBABA"</c:if>
						>
							<td>${car.id}</td>
							<td>${car.mark}</td>
							<td>${car.model}</td>
							<td>${car.country}</td>
							<td>${car.city}</td>
							<td>${car.price}</td>
							<td>${car.status}</td>
							<td>
								<a href="cmd?command=LOAD_CAR&idcar=${car.id}">show</a>
							</td>
						</tr>	
					</c:forEach>
					<tr><td colspan="2"><br><a href="cmd?command=ADD_CAR">Add a car</a></td></tr>
				</table>
				
				
			</td>			
		</tr>
		</table>
	
</body>
</html>