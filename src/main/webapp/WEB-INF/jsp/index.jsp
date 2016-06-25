<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>Elevator Monitor</title>
<script type="text/javascript">
  function blink() {
    var blinks = document.getElementsByTagName('blink');
    for (var i = blinks.length - 1; i >= 0; i--) {
      var s = blinks[i];
      s.style.visibility = (s.style.visibility === 'visible') ? 'hidden' : 'visible';
    }
    window.setTimeout(blink, 1000);
  }
  if (document.addEventListener) document.addEventListener("DOMContentLoaded", blink, false);
  else if (window.addEventListener) window.addEventListener("load", blink, false);
  else if (window.attachEvent) window.attachEvent("onload", blink);
  else window.onload = blink;
</script>
</head>
<body>
    <t:genericpage>
    <jsp:attribute name="header">
      <div style="text-align: center">
        <h1 style="color:green"><blink><spring:message code="header.title" /></blink></h1>
        <hr>
      </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <div style="text-align: center">
        <hr>
        <p id="copyright"><spring:message code="footer.copyright" /></p>
      </div>
    </jsp:attribute>
    <jsp:body>
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
    </jsp:body>
    </t:genericpage>
	
	
	
</body>
</html>