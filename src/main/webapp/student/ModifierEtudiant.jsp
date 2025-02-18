<%--
  Created by IntelliJ IDEA.
  User: Med
  Date: 2/18/2025
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

formulaire modification


<form action="students" method="POST">

    <input type="hidden" name="page" value="modifier" >

    <input type="text" name="name"> <br>

    <input type="text" name="prenom">


    <button type="submit" >modifier</button>

</form>


</body>
</html>
