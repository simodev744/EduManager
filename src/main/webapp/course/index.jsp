<%@ page import="Models.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course List</title>
</head>
<body>
<h2>List of Courses</h2>
<table border="1">
    <thead>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <% List<Course> courses = (List<Course>) request.getAttribute("courses");
        for (Course course : courses) { %>
    <tr>
        <td><%= course.getTitle() %></td>
        <td><%= course.getDescription() %></td>
        <td>
            <a href="courses?action=edit&id=<%= course.getId() %>">Edit</a>
            <a href="courses?action=delete&id=<%= course.getId() %>">Delete</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<a href="courses?action=add">Add a new course</a>
</body>
</html>
