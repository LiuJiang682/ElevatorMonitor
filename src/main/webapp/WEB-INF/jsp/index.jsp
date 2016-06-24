<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Elevator Monitor</title>
</head>
<body>
<div style="text-align: center">
    <h1><spring:message code="elevator.monitor" /></h1>
</div>
<div>
    <table style="text-align: center" width="100%">
        <tr>
            <td><spring:message code="elevator.first" /></td>
            <td><spring:message code="elevator.second" /></td>
        </tr>
        <tr>
            <td>${message1}</td>
            <td>${message2}</td>
        </tr>
    </table>
</div>

<div style="text-align: center">
<button onclick="location.href='http://localhost:8080/elvMon'"><spring:message code="status.refresh" /></button>
</div>
</body>
</html>