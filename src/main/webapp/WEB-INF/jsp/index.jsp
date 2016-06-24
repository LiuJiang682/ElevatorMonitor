<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Elevator Monitor</title>
</head>
<body>
	<div style="text-align: center">
		<h1>
			<spring:message code="elevator.monitor" />
		</h1>
	</div>
	<div>
		<table  width="100%">
			<tr>
				<td style="text-align: center"><h3>
						<spring:message code="elevator.first" />
					</h3></td>
				<td style="text-align: center"><h3>
						<spring:message code="elevator.second" />
					</h3></td>
			</tr>
			<tr>
				<c:forEach items="${messages}" var="message">
				    <td style="text-align: center">
						<table style="text-align: center" width="100%">
						  <tr>
						      <td style="text-align:left"><spring:message code="elevator.moving" /></td>
						      <td style="text-align:left">${message.moving}</td>
						  </tr>
						  <tr>
						      <td style="text-align:left"><spring:message code="elevator.floor" /></td>
						      <td style="text-align:left">${message.floor}</td>
						  </tr>
						  <tr>
						      <td style="text-align:left"><spring:message code="elevator.lockStatus" /></td>
						      <td style="text-align:left">${message.lockStatus}</td>
						  </tr>
						</table>
					</td>
				</c:forEach>

			</tr>
		</table>
	</div>

	<div style="text-align: center">
		<button onclick="location.href='http://localhost:8080/elvMon'">
			<spring:message code="status.refresh" />
		</button>
	</div>
</body>
</html>