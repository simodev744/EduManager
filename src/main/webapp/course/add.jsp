<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Course</title>
</head>
<body>
<h2>Add a New Course</h2>
<form action="courses" method="post">
    <input type="hidden" name="action" value="save" />
    Title: <input type="text" name="title" required /><br />
    Description: <textarea name="description" required></textarea><br />
    <input type="submit" value="Save" />
</form>
</body>
</html>
