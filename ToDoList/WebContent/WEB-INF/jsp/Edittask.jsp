<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Todo, constant.Parameters" %>
<%
//Todo取得
Todo todo = (Todo) request.getAttribute("todo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
</head>
<body>
<form action="update-servlet" method="post" >
	<label>Title : </label>
	<input type="text" name="<%=Parameters.TITLE %>" value="<%=todo.getTitle() %>"><br>
	<label>Due Date : </label>
	<input type="date" name="<%=Parameters.DUEDATE %>" value="<%=todo.getDuedate() %>">
	<input type="hidden" name="<%=Parameters.TODO_ID %>" value="<%=todo.getId() %>">
	<input type="submit" value="Submit">
</form>
</body>
</html>