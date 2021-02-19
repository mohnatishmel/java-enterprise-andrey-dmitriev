<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<%--%>
<%--    if (request.getAttribute("error") != null) {--%>
<%--        out.println("<h2 style=\"color:red\">* Error: " + request.getAttribute("error") + "</h2>");--%>
<%--    }--%>
<%--%>--%>

<c:if test="${!empty requestScope.error}" >
    <h2 style="color:red">* Error: ${requestScope.error} </h2>
</c:if>


<%--<% if (request.getAttribute("error") != null) { %>--%>
<%--<h2 style="color:red">* Error: <%= request.getAttribute("error") %> </h2>--%>
<%--<% } %>--%>
