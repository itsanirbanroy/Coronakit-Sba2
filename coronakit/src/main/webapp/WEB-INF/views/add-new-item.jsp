<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring-form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<h2>Admin create product</h2>
<spring-form:form action="${pageContext.request.contextPath}/admin/product-save" method="POST">
	<div>
		<div><label>Product name</label> <input type="text" name="productName"></div>
		<br/>
		<div><label>Product Cost</label> <input type="number" name="cost"></div>
		<br/>
		<div><label>Product Description</label> <input type="text" name="productDescription"></div>
		<div> <input type="submit" value="Add"> </div>
	</div>
</spring-form:form>
	<br>
<span>
	<a href="${pageContext.request.contextPath}/admin/product-list"><input type="button" value="List of products" /> </a>
</span>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>