<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Main page..</title>
</head>
<body>

<c:set var="command" value="Login" scope="page" />

<c:if test="${param.formType==register}">
  <c:set var="command" value="Reguister" scope="page" />
</c:if>
<%--<jsp:useBean id="testUser" scope="session" class="by.itacademy.app.model.User" type="by.itacademy.app.model.User">--%>
<%--    <jsp:setProperty name="login" property="Hello World with useBean!!!" />--%>
<%--</jsp:useBean>--%>

<c:import url="/WEB-INF/jsp/template/error_tmpl.jsp" />
<%--<%@ include file="/WEB-INF/template/error_tmpl.jsp" %>--%>

<c:import url="/WEB-INF/jsp/template/login_form_tmpl.jsp" />

<%--<%@ include file="/WEB-INF/template/login_form_tmpl.jsp" %>--%>
<%--<%@ include file="/WEB-INF/template/registration_form_tmpl.jsp" %>--%>

</body>
</html>
