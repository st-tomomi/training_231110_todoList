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

        function confirmDelete(paramName, paramValue) {
        	var confirmDelete = confirm("Delete this task? You cannot undo this action.");
        	if (confirmDelete) {
        		window.location.href = "delete-servlet?" + paramName + "=" +paramValue;
        	}
        }
</script>
<style type="text/css">
	td.center-align, th.center-align {
            text-align: center;
    }

    td.padding, th.padding {
            padding: 0px 8px;
    }
</style>
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
	<table border="1">
	<thead>
        <tr>
            <th>Title</th>
            <th class="padding">Due Date</th>
            <th class="padding">Comp. Date</th>
            <th class="padding">Done</th>
            <th class="center-align"></th>
            <th class="center-align"></th>
        </tr>
    </thead>
	<tbody>
	<% for (Todo todo : todoList) { %>
            <tr>
                <td><%= todo.getTitle() %></td>
                <td class="padding"><%= todo.getDuedate() %></td>
                <td class="padding"><%= todo.getCompletiondate() %></td>
                <td class="padding"><%= todo.getStatus() %></td>
                <td class="center-align padding"><a href="update-servlet?<%= Parameters.TODO_ID %>=<%= todo.getId() %>">Edit</a></td>
                <td class="center-align padding"><a href="#" onclick="confirmDelete('<%= Parameters.TODO_ID %>' , '<%= todo.getId() %>')">Delete</a></td>
                <!-- <td class="center-align"><a href="delete-servlet?<%= Parameters.TODO_ID %>=<%= todo.getId() %>">Delete</a></td> -->
            </tr>
        <% } %>
	</tbody>
	</table>
</body>
</html>