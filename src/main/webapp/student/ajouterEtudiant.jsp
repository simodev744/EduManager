<%--
  Created by IntelliJ IDEA.
  User: Med
  Date: 2/18/2025
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

formulaire Ajouter

<form action="students" method="POST">

    <input type="hidden" name="page" value="ajouter" >

    <input type="text" name="name"> <br>

    <input type="text" name="prenom">

    <input type="text" name="email">

    <input type="text" name="datenaissance">



    <button type="submit" >Ajouter</button>

</form>


</body>
</html>
