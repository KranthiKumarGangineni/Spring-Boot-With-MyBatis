<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>

<head>
<body>
	<c:if test="${userIdInvalid}">
		<c:out
			value="userId should not be Null & It should be a Valid Number. If you are getting multiple user Details, please seperate userIds with 
			semicolon(;)"></c:out>
	</c:if>

	<c:if test="${userObjectNull}">
		<c:out value="User Details are not present for userId : ${userId}"></c:out>
	</c:if>
</body>

</html>