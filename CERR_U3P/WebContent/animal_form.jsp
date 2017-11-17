<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Animal Form</title>
</head>
<body>
	<form action="AnimalController">
		<label>Name:</label>
		<input type="text" name="name" value="${animal.name}"><br /><br />
		<label>home:</label>
		<input type="text" name="home" value="${animal.home}"><br /><br />
		<label>age:</label>
		<input type="number" name="age" value="${animal.age}"><br /><br />
		<input type="submit" name="btn_save" value="save">
	</form>
</body>
</html>