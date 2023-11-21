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
		//削除確認ダイアログ
        function confirmDelete(paramName, paramValue) {
        	var confirmDelete = confirm("Delete this task? You cannot undo this action.");
        	if (confirmDelete) {
        		window.location.href = "delete-servlet?" + paramName + "=" +paramValue;
        	}
        }
		//実行状況(status=0, 1)でフィルター
		//期日(DueDate)を過ぎていた場合赤文字にする
        function filterTodos(status) {
            var todos = document.getElementsByClassName('todo');

            for (var i = 0; i < todos.length; i++) {
                var todo = todos[i];
                var todoStatus = todo.getAttribute('data-status');
                var dateCell = todo.querySelector('.date-cell');
                var todoDate = new Date(dateCell.textContent);

                if (status === 'all' || todoStatus === status) {
                    todo.style.display = 'table-row';
                    if (todoDate < new Date()) {
                    	dateCell.style.color = 'red';
                    } else {
                    	dateCell.style.color = '';
                    }
                } else {
                    todo.style.display = 'none';
                }
            }
        }
      //最初の画面読み込み時に実行
        window.onload = function() {
            filterTodos('all');
        };

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
		<input type="submit"  id = "newTask" value="add"><br>
	</form>
	<% if (msg != null) { %>
		<p><%= msg %></p>
	<% } %>
    <button onclick="filterTodos('all')">Show All</button>
	<button onclick="filterTodos('0')">Show Not Done</button>
    <button onclick="filterTodos('1')">Show Done</button>
	<table border="1">
	<thead>
        <tr>
            <th width="500">Title</th>
            <th class="padding">Due Date</th>
            <th class="padding">Comp. Date</th>
            <th class="padding">Done</th>
            <th class="center-align"></th>
            <th class="center-align"></th>
        </tr>
    </thead>
	<tbody>
	<% for (Todo todo : todoList) { %>
            <tr class="todo" data-status="<%=todo.getStatus() %>">
                <td width="500"><%= todo.getTitle() %></td>
                <td class="date-cell padding"><%= todo.getDuedate() %></td>
                <td class="padding"><%= todo.getCompletiondate() %></td>
                <td class="padding"><%= todo.getStatus() %></td>
                <td class="center-align padding"><a href="update-servlet?<%= Parameters.TODO_ID %>=<%= todo.getId() %>">Edit</a></td>
                <td class="center-align padding"><a href="#" onclick="confirmDelete('<%= Parameters.TODO_ID %>' , '<%= todo.getId() %>')">Delete</a></td>
            </tr>
        <% } %>
	</tbody>
	</table>
</body>
</html>