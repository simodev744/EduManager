<%@ page import="Models.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Course</title>
</head>
<body>
<h2>Edit Course</h2>
<% Course course= (Course) request.getAttribute("course"); %>
<form action="courses" method="post">
    <input type="hidden" name="action" value="update" />
    <input type="text" name="id" value="<%= course.getId()  %>">
    Title: <input type="text" name="title" value="<%=  course.getTitle() %>"  /><br />
    Description: <textarea name="description" required><%= course.getDescription() %></textarea><br />
    <input type="submit" value="Update" />
</form>
</body>
</html>
