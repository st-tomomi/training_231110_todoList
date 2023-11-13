<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Todo, java.util.List, java.util.ArrayList" %>
<%
//To-DoList取得
List<Todo> todoList =
 (List<Todo>) request.getAttribute("todoList");
//メッセージ取得
String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>To-Do</title>
</head>
<body>
	<h1>To-Do List</h1>
	<% if (msg != null) { %>
		<p><%= msg %></p>
	<% } %>
	<%for(Todo todo : todoList) { %>
	<p><%=todo.getTitle() %>:<%=todo.getDeadline() %><br></p>
	<% } %>
</body>
</html>