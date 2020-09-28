<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>
table,th,td{
border: 1px solid purple
}

#atk
{
padding: 30,30,30,30
}
</style>
</head>
<body>
<spring-form:form action="${pageContext.request.contextPath}/logout" method="POST">
<input type="submit" value="Logout">
</spring-form:form>
<br>
<hr>
<h2>Cart Items For User : <security:authentication property="principal.username"/></h2>
<hr>
<table>
<tr>
<th>Product Name</th>
<th>Qty.</th>
<th>Total Amount</th>
</tr>
<core:set var="product" value="${sessionScope.productMap}"></core:set>
<core:set var="total" value="0"></core:set>

<core:forEach var="item" items="${sessionScope.cart}">
<core:set var="total" value="${total + item.quantity * item.amount }"></core:set>
<tr>
<td>${product.get(item.productId)}</td>
<td>${item.quantity}</td>
<td>${item.amount}</td>
<td><a href="${pageContext.request.contextPath}/user/delete/${item.productId}"><input id="atk" type="button" value="remove"></a></td>
</tr>
</core:forEach>
<tr>
<td></td>
			<td>Sum</td>
			<td>${total }</td>
		</tr>
</table>
<hr>
<span><a href="${pageContext.request.contextPath}/user/show-list"><input  type="button" value="Show Products"></a></span>
<span><a href="${pageContext.request.contextPath}/user/checkout"><input  type="button" value="Check Out"></a></span>
</body>
</html>