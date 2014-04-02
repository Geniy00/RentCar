<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body style="background-color: gray; ">
	<br/><br/><br/><br/><br/><br/><br/>
	<form action="cmd?command=SIGN_UP" method="post">
		
		<table style="background-color: White; " align="center">
			<tr>
				<td colspan="3"><p align="center"><b>Sign up:</b></p></td>
			</tr>
			<tr>
				<td>Login: </td>
				<td colspan="2" width="190"><input type="text" name="login" style="width: 170px; "/></td>
				
			</tr>
			<tr>
				<td>Password:</td>
				<td colspan="2"><input type="password" name="password" style="width: 170px; "/></td>				
			</tr>
			<tr>
				<td>Configm password:</td>
				<td colspan="2"><input type="password" name="confirm_password" style="width: 170px; "/></td>				
			</tr>
			<tr>
				<td>First name:</td>
				<td colspan="2"><input type="text" name="first_name" style="width: 170px; "/></td>				
			</tr>
			<tr>
				<td>Last name:</td>
				<td colspan="2"><input type="text" name="last_name" style="width: 170px; "/></td>				
			</tr>
			<tr>	
				<td></td>
				<td colspan="2" align="center"><input type="submit" value="Sign up" /></td>
				
			</tr>			
		</table>
		<br><br><br>
		<c:if test="${message!=null}">
			<div align="center"><font color="FFA126"><b>${message}</b></font></div>
		</c:if>
	</form>
</body>
</html>