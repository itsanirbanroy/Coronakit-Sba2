<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Page</title>
</head>
<body>
<spring-form:form action="${pageContext.request.contextPath}/logout" method="POST">
<input type="submit" value="Logout">
</spring-form:form>
<br>
<hr>
	<h3>List of Products</h3>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Price</th>
			<th>Buy</th>
		</tr>
		<c:forEach var="product" items="${products }">
			<tr>
				<td>${product.id}</td>
				<td>${product.productName }</td>
				<td>${product.cost }</td>
				<td align="center">
					<a href="${pageContext.request.contextPath }/admin/product-delete/${product.id}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
<span>
	<a href="${pageContext.request.contextPath}/admin/product-entry"><input type="button" value="Add Product" /> </a>
</span>

</body>
</html>