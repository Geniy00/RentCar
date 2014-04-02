<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show the car</title>
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
					<tr>
						<td align="center"><img alt="no photo" src="img/car/nophoto.png" width="300"></td>
						<td>
							<table align="center">
								<tr align="center"><td width="100">CodeID:</td><td width="150">${car.id}</td></tr>
								<tr align="center"><td>Mark:</td><td>${car.mark}</td></tr>
								<tr align="center"><td>Model:</td><td>${car.model}</td></tr>
								<tr align="center"><td>Country:</td><td>${car.country}</td></tr>
								<tr align="center"><td>City:</td><td>${car.city}</td></tr>
								<tr align="center"><td>Street:</td><td>${car.street}</td></tr>
								<tr align="center"><td>Price:</td><td>${car.price}</td></tr>
								<tr align="center"><td>Status:</td>
									<td>
										<c:if test="${car.status=='AVAILABLE'}">available</c:if>
										<c:if test="${car.status!='AVAILABLE'}">unavailable</c:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<c:if test="${user.permission=='ADMIN'}">
						<tr><td></td>
							<td> 							
								<br>
								<font size="10"></font>
								<b>
								<a href="cmd?command=EDIT_CAR&idcar=${car.id}">edit</a><br>
								delete
								</b>
							</td>
						</tr>
					</c:if>
					<c:if test="${user.permission!='ADMIN'}">
						<tr>
							<td>
								
							</td>
							<td align="center">
								<br>
								<form action="cmd?command=ADD_REQUEST" method="post">
									<input type="hidden" name="idcar" value="${car.id}" />
									<input type="submit" value="Rent"/>
								</form>
							</td>
						</tr>
					</c:if>
					<tr>						
						<td colspan="2" align="center">
						<br><br>
						<a href="cmd?command=LOAD_CAR_LIST">Back</a></td>
						
					</tr>
				</table>
		
	</table>

</body>
</html>