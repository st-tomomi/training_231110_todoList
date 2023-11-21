<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Todo, java.util.List, java.util.ArrayList,  constant.Parameters" %>
<%
//Todo取得
Todo todo = (Todo) request.getAttribute("todo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
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

        function confirmGoBack() {
        	var confirmBack = confirm("The changes will be discarded. Do you want to proceed with the return?");
        	if (confirmBack) {
                // 確認した場合は一覧画面に戻る
                history.back();
            }
        }
</script>
</head>
<body>
<form action="update-servlet" method="post" onsubmit="return validateForm()">
	<% int status = todo.getStatus(); %>
	<label>Task : </label>
	<input type="text" id="inputTitle" name="<%=Parameters.TITLE %>" value="<%=todo.getTitle() %>"><br>
	<label>Due Date : </label>
	<input type="date" id= "inputDate" name="<%=Parameters.DUEDATE %>" value="<%=todo.getDuedate() %>"><br>
	<label>Done : </label>
	<input type="checkbox" name="<%=Parameters.STATUS %>" value="on" <%= (status == 1) ? "checked" : "" %>>
	<br>
	<input type="hidden" name="<%=Parameters.TODO_ID %>" value="<%=todo.getId() %>">
	<input type="submit" value="Submit">
</form>
<button onclick="confirmGoBack()">Go Back</button>
</body>
</html>