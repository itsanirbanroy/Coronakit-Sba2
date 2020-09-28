<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %> 
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="spring-form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table,th,td{
border: 1px solid purple
}

#delete
{
padding: 30,30,30,30
}

</style>
</head>
<body>
<br>
<spring-form:form action="${pageContext.request.contextPath}/logout" method="POST">
<input type="submit" value="Logout">
</spring-form:form>
<br>
<hr>
<h2> Order Summary</h2>
<hr>
<div><label>Customer Name : <security:authentication property="principal.username"/> </label></div>
<br>
<div><label>Delivery Address : ${coronaKit.deliveryAddress} </label></div>
<br>
<div><label>Order Date : ${coronaKit.orderDate}</label></div>
<br>
<div><label>Total Order Amount : ${coronaKit.totalAmount}</label></div>
<br>
<hr>
<h3>Detailed Summary</h3>
<hr>
<table>
<tr>
<th>No.</th>
<th>Product Name</th>
<th>Qty.</th>
<th>Amount</th>
</tr>
<core:set var="num" value="1"></core:set>
<core:set var="product" value="${sessionScope.productMap}"></core:set>
<core:forEach var="item" items="${sessionScope.cart}">
<tr>
<td>${num}</td>
<td>${product.get(item.productId)}</td>
<td>${item.quantity}</td>
<td>${item.amount}</td>
</tr>
<core:set var="num" value="${num + 1}"></core:set>
</core:forEach>
</table>
<hr>
<div><b><i><h3>Thank you for Ordering !!!</h3></i></b></div>
</body>
</html>