<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>

<head>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
}

th {
	text-align: left;
}
</style>
</head>
<body>
	<table style="width: 100%">
		<caption>Employee Details</caption>
		<tr>
			<th>Employee Id</th>
			<th>Employee Name</th>
			<th>Employee Salary</th>
			<th>Employee Location</th>
			<th>Employee Company Name</th>
			<th>Employee Phone</th>
			<th>Employee Role</th>
			<th>Employee Old Company Details</th>
		</tr>
		<c:forEach var="empDetail" items="${employeeDetails}">
			<tr>
				<td>${empDetail.employeeId}</td>
				<td>${empDetail.employeeName}</td>
				<td>${empDetail.employeeSalary}</td>
				<td>${empDetail.employeeLocation}</td>
				<td>${empDetail.employeeCompanyName}</td>
				<td><c:if
						test="${not empty empDetail.phoneCode && not empty empDetail.phoneNumber}">
				+${empDetail.phoneCode}-${empDetail.phoneNumber}
				</c:if></td>
				<td>${empDetail.employeeRole}</td>
				<td><c:if test="${! empty empDetail.employeeOldCompanyDetails}">
						<c:forEach var="empOldComp"
							items="${empDetail.employeeOldCompanyDetails}" varStatus="status">
					${empOldComp.employeeCompanyName}
         			<c:if test="${!status.last}">,</c:if>
						</c:forEach>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
</body>

</html>