<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body background="${pageContext.request.contextPath}/img/error/error404.jpg" 
	bgcolor="666666"  style="background-repeat: round; background-position: top;" >
	<br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br>
	<div align="center">
		<a href="cmd?command=LOGIN" ><font size="24" color="1EC9FC">.Go Main</font></a> 
	</div>
	<br><br>
	<div align="center">
		<c:if test="${message!=null}">Message: ${message}</c:if>
	</div>
</body>
</html>