<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Animal List</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>
			<form action="AnimalReport">
					<input type="submit" name="btn_report" value="Report">
				</form>
			</th>
			<th>
				
				<form action="AnimalController">
					<input type="submit" name="btn_new" value="New">
				</form>
			</th>
			<th>Id</th>
			<th>Name</th>
			<th>Home</th>
			<th>Age</th>
		</tr>
			<c:forEach var="animal" items="${animals}">
				<tr>
					<td>
						<form action="StudentController">
							<input type="hidden" name="id" value="${animal.id}">
							<input type="submit" name="btn_edit" value="Edit">
							<input type="submit" name="btn_delete" value="Delete">
						</form>
					</td>
					<td>${animal.id}</td>
					<td>${animal.name}</td>
					<td>${animal.home}</td>
					<td>${animal.age}</td>
				</tr>
			</c:forEach>
	</table>
</body>
</html>