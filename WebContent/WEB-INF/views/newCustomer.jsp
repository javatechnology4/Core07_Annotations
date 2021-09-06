<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Customer</title>
</head>
<body>
	<div align="center">
		<h2>New Customer</h2>
		<form:form action="saveCustomer" method="post"
			modelAttribute="customer">
			<table border="1">
				<tr>
					<td>Name:</td>
					<td><form:input path="name" /></td>
					<td><form:errors path="name" cssClass="error"/></td>  
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="email" /></td> 
					<td><form:errors path="email" cssClass="error"/></td>  
				</tr>
				<tr>
					<td>Address:</td>
					<td><form:input path="address" /></td>
					<td><form:errors path="address" cssClass="error"/></td>  
				</tr>
				<tr>
				<td colspan="2"><input type="submit" value="Save"> </td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>