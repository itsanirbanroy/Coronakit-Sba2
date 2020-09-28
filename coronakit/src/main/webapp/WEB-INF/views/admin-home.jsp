<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="spring-form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2>Hello <security:authentication property="principal.username"/></h2>
<br/>
<spring-form:form action="${pageContext.request.contextPath}/logout" method="POST">
<input type="submit" value="Logout">
</spring-form:form>
<hr/>

<a href="${pageContext.request.contextPath}/admin/product-entry">Add New Product</a>

<hr/>
<a href="${pageContext.request.contextPath}/admin/product-list">Get List of Products</a>


</body>
</html>