<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Spring MVC Application</title>
</head>
<body>
	<h1>Welcome to Spring MVC</h1>
	<h4> <c:if test="${! empty registerMessage}"> ${registerMessage}</c:if> </h4>
	<form:form>
	
	<table align="right">
		<tr>
		<td> <a href="login">Login</a> </td>
		<td></td>
		<td></td>
		<td></td>
		<td><a href="register">Register</a></td>
		
		<td> <a href="message">Message</a> </td>
		
		<td> <a href="notification">Notification</a> </td>
		<td> <a href="profile">Profile</a> </td>
		</tr>
	</table>
	
	</form:form>
	<c:if test="${! empty registerList}"> 
	<c:forEach var="register" items="${registerList}">
		<table border="2">
			<tr>
				<th>User ID</th>
				<th>User FirstName</th>
				<th>User LastName</th>
				<th>User Email</th>
				<th>User Profile Image</th>
			</tr>
			<tr>
				<td>${register.id}</td>
				<td>${register.firstName}</td>
				<td>${register.lastName}</td>
				<td>${register.email}</td>
				<td><img width="200" height="200"
					src="data:image/jpeg;base64,${register.base64}"></td>
			</tr>
		</table>
	</c:forEach>
	</c:if> 
</body>
</html>