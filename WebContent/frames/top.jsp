<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
Hello <b>${user.firstName} ${user.lastName}</b> (<a href="cmd?command=LOGOUT">Logout</a>)