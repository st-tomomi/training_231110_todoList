<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Todo, java.util.List, java.util.ArrayList, constant.Parameters" %>
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
<script>
        function validateForm() {
            // 入力欄の値を取得
            var input1 = document.getElementById("inputTitle").value;
            var input2 = document.getElementById("inputDate").value;

            // 全ての入力欄が埋まっているか検証
            if (input1 === '' || input2 === '') {
                // 入力欄が一つでも空の場合は警告を出す
                alert("Please fill in all fields.");
                return false;
            } else {
               return true;
            }
        }
</script>
</head>
<body>
	<h1>To-Do List</h1>
	<form action="insert-servlet" method="post" onsubmit="return validateForm()">
		<label>Task:</label><input type="text" id="inputTitle" name="<%=Parameters.TITLE %>"><br>
		<label>Due Date:</label><input type="date" id= "inputDate" name="<%=Parameters.DUEDATE %>"><br>
		<input type="submit"  id = "newTask" value="add">
	</form>
	<% if (msg != null) { %>
		<p><%= msg %></p>
	<% } %>
	<%for(Todo todo : todoList) { %>
	<h5><%=todo.getTitle() %></h5>
	<h6><%=todo.getDuedate() %></h6>
	<a href="update-servlet?<%=Parameters.TODO_ID %>=<%=todo.getId() %>">Edit</a>
	<a href="delete-servlet?<%=Parameters.TODO_ID %>=<%= todo.getId() %>">Delete</a>
	<% } %>
</body>
</html>