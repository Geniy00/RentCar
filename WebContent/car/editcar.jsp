<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit the car</title>
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
						<tr align="center"><td width="100">CodeID:</td><td width="150">${car.id}</td></tr>
						<tr align="center">
							<td>Mark:</td><td><input type="text" name="mark" value="${car.mark}"/></td>
						</tr>
						<tr align="center">
							<td>Model:</td><td><input type="text" name="model" value="${car.model}"/></td>
						</tr>
						<tr align="center">
							<td>Country:</td><td><input type="text" name="country" value="${car.country}"/></td>
						</tr>
						<tr align="center">
							<td>City:</td><td><input type="text" name="city" value="${car.city}"/></td>
						</tr>
						<tr align="center">
							<td>Street:</td><td><input type="text" name="street" value="${car.street}"/></td>
						</tr>
						<tr align="center">
							<td>Price:</td><td><input type="text" name=price value="${car.price}"/></td>
						</tr>
						<tr align="center"><td>Status:</td>
							<td align="left">
								<input type="radio" name="status" value="AVAILABLE" 
									<c:if test="${car.status=='AVAILABLE'}">checked="checked"</c:if> >available<br>
								<input type="radio" name="status" value="UNAVAILABLE"
									<c:if test="${car.status=='UNAVAILABLE'}">checked="checked"</c:if>>unavailable
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<br><input type="submit" value="Save"/> <br>
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